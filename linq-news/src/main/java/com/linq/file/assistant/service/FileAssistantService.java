package com.linq.file.assistant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linq.file.assistant.domain.UserFile;

import java.util.List;

public interface FileAssistantService extends IService<UserFile> {

    /**
     * 查询用户全部文档列表
     *
     * @param file 文档
     * @return 文档集合
     */
    IPage<UserFile> findPageAll(Page<UserFile> userFilePage, UserFile file);

    /**
     * 查询文档信息
     *
     * @param fileId 文档ID
     * @return 文档
     */
    UserFile findFileById(Long fileId);

    /**
     * 新增文档
     *
     * @param userFile 文档
     * @return 结果
     */
    boolean insertFile(UserFile userFile);

    /**
     * 修改文档
     *
     * @param userFile 文档
     * @return 结果
     */
    boolean updateFile(UserFile userFile);

    /**
     * 批量删除文档
     *
     * @param fileIds 需要删除的文档ID
     * @return 结果
     */
    boolean deleteFileByIds(List<Long> fileIds);


    /**
     * 文档状态修改
     *
     * @param fileId 文档
     * @return 结果
     */
    boolean changeStatus(Long fileId,String statusCode);
}
