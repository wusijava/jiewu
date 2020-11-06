package com.linq.web.controller.news;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.controller.BaseController;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.news.domain.LinqCollect;
import com.linq.news.service.LinqCollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "新闻收藏管理接口")
@RestController
@RequestMapping("/news/collect")
public class LinqCollectController  extends BaseController {
    @Resource
    private LinqCollectService linqCollectService;

    /**
     * 新增新闻收藏
     */
    @ApiOperation(value = "新增新闻收藏", notes = "新增新闻收藏详情")
    @PreAuthorize("@ss.hasPermi('news:collect:add')")
    @Log(title = "新闻收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@RequestBody LinqCollect linqCollect) {
        return toResult(linqCollectService.insertLinqCollect(linqCollect));
    }

    /**
     * 查询新闻收藏列表
     */
    @ApiOperation(value = "条件分页查询新闻收藏列表", notes = "条件分页查询新闻收藏列表详情")
    @PreAuthorize("@ss.hasPermi('news:collect:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<LinqCollect>> list(@PathVariable("page") int page, @PathVariable("size") int size, LinqCollect linqCollect) {
        IPage<LinqCollect> iPage = linqCollectService.findPage(new Page<LinqCollect>(page, size), linqCollect);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 删除新闻收藏
     */
    @ApiOperation(value = "删除新闻收藏", notes = "删除新闻收藏详情")
    @PreAuthorize("@ss.hasPermi('news:collect:remove')")
    @Log(title = "新闻收藏", businessType = BusinessType.DELETE)
    @DeleteMapping("/{collectionIds}")
    public Result<String> remove(@PathVariable("collectionIds") List<Long> collectionIds) {
        return toResult(linqCollectService.deleteLinqCollectByIds(collectionIds));
    }


}
