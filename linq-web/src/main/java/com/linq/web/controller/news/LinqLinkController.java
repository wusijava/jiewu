package com.linq.web.controller.news;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.controller.BaseController;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.news.domain.LinqLink;
import com.linq.news.service.LinqLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/29 9:54 上午
 * @Description: 友情链接
 * @Version: 1.0.0
 */
@Api(tags = "友情链接管理接口")
@RestController
@RequestMapping("/news/link")
public class LinqLinkController extends BaseController {
    @Autowired
    private LinqLinkService linkService;

    /**
     * 查询友情链接列表
     */
    @ApiOperation(value = "条件分页查询友情链接列表", notes = "条件分页查询友情链接列表详情")
    @PreAuthorize("@ss.hasPermi('news:link:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<LinqLink>> list(@PathVariable("page") int page, @PathVariable("size") int size, LinqLink link) {
        System.err.println("LinqLink查询条件->" + link);
        IPage<LinqLink> iPage = linkService.findPage(new Page<LinqLink>(page, size), link);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }


    /**
     * 获取友情链接详细信息
     */
    @ApiOperation(value = "获取友情链接详细信息", notes = "获取友情链接详细信息详情")
    @PreAuthorize("@ss.hasPermi('news:link:query')")
    @GetMapping(value = "/{linqId}")
    public Result<List<LinqLink>> getInfo(@PathVariable("linqId") Long linqId) {
        return ResultUtils.success(linkService.findLinqLinkById(linqId));
    }

    /**
     * 新增友情链接
     */
    @ApiOperation(value = "新增友情链接", notes = "新增友情链接详情")
    @PreAuthorize("@ss.hasPermi('news:link:add')")
    @Log(title = "友情链接", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@RequestBody LinqLink linqLink) {
        return toResult(linkService.insertLinqLink(linqLink));
    }

    /**
     * 修改友情链接
     */
    @ApiOperation(value = "修改友情链接", notes = "修改友情链接详情")
    @PreAuthorize("@ss.hasPermi('news:link:edit')")
    @Log(title = "友情链接", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@RequestBody LinqLink linqLink) {
        return toResult(linkService.updateLinqLink(linqLink));
    }

    /**
     * 删除友情链接
     */
    @ApiOperation(value = "删除友情链接", notes = "删除友情链接详情")
    @PreAuthorize("@ss.hasPermi('news:link:remove')")
    @Log(title = "友情链接", businessType = BusinessType.DELETE)
    @DeleteMapping("/{linqIds}")
    public Result<String> remove(@PathVariable List<Long> linqIds) {
        return toResult(linkService.deleteLinqLinkByIds(linqIds));
    }
}
