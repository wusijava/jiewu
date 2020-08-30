package com.linq.web.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.controller.BaseController;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.SecurityUtils;
import com.linq.system.domain.SysNotice;
import com.linq.system.service.SysNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 11:25 上午
 * @Description: 公告 信息操作处理
 * @Version: 1.0.0
 */
@Api(tags = "公告通知管理")
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController {
    @Autowired
    private SysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @ApiOperation(value = "条件获取通知公告列表", notes = "条件获取通知公告列表详情")
    @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<SysNotice>> list(@PathVariable("page") int page, @PathVariable("size") int size, SysNotice notice) {
        System.err.println("SysNotice查询条件->" + notice);
        IPage<SysNotice> iPage = noticeService.findPage(new Page<SysNotice>(page, size), notice);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @ApiOperation(value = "根据通知公告编号获取详细信息", notes = "根据通知公告编号获取详细信息详情")
    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping("/{id}")
    public Result<SysNotice> getInfo(@PathVariable("id") Integer noticeId) {
        return ResultUtils.success(noticeService.findNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @ApiOperation(value = "新增通知公告", notes = "新增通知公告详情")
    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@Validated @RequestBody SysNotice notice) {
        notice.setCreateBy(SecurityUtils.getUsername());
        return toResult(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @ApiOperation(value = "修改通知公告", notes = "修改通知公告详情")
    @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@Validated @RequestBody SysNotice notice) {
        notice.setUpdateBy(SecurityUtils.getUsername());
        return toResult(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @ApiOperation(value = "删除通知公告", notes = "删除通知公告详情")
    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public Result<String> remove(@PathVariable("noticeIds") List<Integer> noticeIds) {
        return toResult(noticeService.deleteNoticeByIds(noticeIds));
    }
}
