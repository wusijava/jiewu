package com.linq.web.controller.file.assistant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.controller.BaseController;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.fastdfs.FastDFSClient;
import com.linq.common.utils.fastdfs.FastDFSFile;
import com.linq.file.assistant.domain.UserFile;
import com.linq.file.assistant.service.FileAssistantService;
import com.linq.framework.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: vue-news
 * @description:
 * @author: Mr.Wang
 * @create: 2020-11-05 18:03
 **/

@Api(tags = "文件助手接口")
@RestController
@RequestMapping("/file/assistant")
@Slf4j
public class UserFileController extends BaseController {

    @Autowired
    private FileAssistantService fileAssistantService;

    @Autowired
    private TokenService tokenService;


    @ApiOperation(value = "单文件上传", notes = "文件上传")
    @PostMapping("/upload")
    public Result<UserFile> upload(MultipartFile file, String source,HttpServletRequest request) throws IOException {
        // 封装文件信息
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(), // 文件名 1.jpg
                file.getBytes(), // 文件字节数组
                org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename()) // 文件拓展名 jpg
        );
        // 文件上传
        String[] uploadResults = FastDFSClient.upload(fastDFSFile);
        // 拼接文件上传地址 格式： http://192.168.138.148:8080/group1/M00/00/00/wKiKlF8lLiWAafsRAAAS-kwhS7s359.png
        String url = FastDFSClient.getTrackerUrl() + "/" + uploadResults[0] + "/" + uploadResults[1];
        //构建数据库存储对象
        UserFile fileInfo = new UserFile();
        fileInfo.setFileName(file.getName());
        fileInfo.setUserId(tokenService.getLoginUser(request).getUser().getUserId());
        fileInfo.setFileSize(file.getSize());
        fileInfo.setFileUrl(url);
        fileInfo.setSource(source);
        fileInfo.setStatus("1");
        //调用服务存储信息
        fileAssistantService.insertFile(fileInfo);
        return ResultUtils.success(fileInfo);
    }


    /**
     * 通用下载请求
     *
     * @param fileId 文件名称
     */
    @ApiOperation(value = "文件下载", notes = "下载详情")
    @GetMapping("/download/{fileId}")
    public void fileDownload(@PathVariable("fileId") long fileId, HttpServletResponse response, HttpServletRequest request) {
        try {
            //根据文档ID获得文档信息
            UserFile fileInfo = fileAssistantService.getById(fileId);
            //如果文档状态为1未读，改为已读
            if ("1".equals(fileInfo.getStatus())) {
                fileAssistantService.changeStatus(fileId, "0");
            }
            //开始下载文档
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    @ApiOperation(value = "条件分页获取文档列表", notes = "条件分页获取文档列表详情")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<UserFile>> fileList(@PathVariable("page") int page, @PathVariable("size") int size, UserFile userFile,HttpServletRequest request) {
        userFile.setUserId(tokenService.getLoginUser(request).getUser().getUserId());
        IPage<UserFile> iPage = fileAssistantService.findPageAll(new Page<UserFile>(page, size), userFile);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

}
