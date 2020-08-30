package com.linq.web.controller.monitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.controller.BaseController;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.system.domain.SysOperLog;
import com.linq.system.service.SysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 4:22 下午
 * @Description: 操作日志记录
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends BaseController {
    @Autowired
    private SysOperLogService operLogService;

    /**
     * 获取日志列表
     *
     * @return 结果集合
     */
    @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<Object> list(@PathVariable("page") int page, @PathVariable("size") int size, SysOperLog operLog) {
        System.err.println("SysOperLog查询条件->" + operLog);
        IPage<SysOperLog> iPage = operLogService.findPage(new Page<SysOperLog>(page, size), operLog);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping("/{operIds}")
    public Result<String> remove(@PathVariable("operIds") List<Long> operIds) {
        return toResult(operLogService.deleteOperLogByIds(operIds));
    }

    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping("/clean")
    public Result<Object> clean() {
        operLogService.cleanOperLog();
        return ResultUtils.success();
    }
}
