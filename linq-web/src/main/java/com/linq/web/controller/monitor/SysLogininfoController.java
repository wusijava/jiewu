package com.linq.web.controller.monitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.controller.BaseController;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.system.domain.SysLogininfo;
import com.linq.system.service.SysLogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 5:11 下午
 * @Description: 系统访问记录
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/monitor/logininfo")
public class SysLogininfoController extends BaseController {
    @Autowired
    private SysLogininfoService logininfoService;

    /**
     * 获取日志列表
     *
     * @return 结果集合
     */
    @PreAuthorize("@ss.hasPermi('monitor:logininfo:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<Object> list(@PathVariable("page") int page, @PathVariable("size") int size, SysLogininfo logininfor) {
        System.err.println("SysLogininfor查询条件->" + logininfor);
        IPage<SysLogininfo> iPage = logininfoService.findPage(new Page<SysLogininfo>(page, size), logininfor);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfo:remove')")
    @Log(title = "登陆日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public Result<Object> remove(@PathVariable("infoIds") List<Long> infoIds) {
        return toResult(logininfoService.deleteLogininforByIds(infoIds));
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfo:remove')")
    @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public Result<Object> clean() {
        logininfoService.cleanLogininfor();
        return ResultUtils.success();
    }
}
