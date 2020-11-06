package com.linq.web.controller.news;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.controller.BaseController;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.news.domain.LinqThumb;
import com.linq.news.service.LinqThumbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "新闻点赞管理接口")
@RestController
@RequestMapping("/news/thumb")
public class LinqThumbController   extends BaseController {
    @Resource
    private LinqThumbService linqThumbService;

    /**
     * 新增新闻点赞
     */
    @ApiOperation(value = "新增新闻点赞", notes = "新增新闻点赞详情")
    @PreAuthorize("@ss.hasPermi('news:thumb:add')")
    @Log(title = "新闻点赞", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@RequestBody LinqThumb linqThumb) {
        return toResult(linqThumbService.insertLinqThumb(linqThumb));
    }

    /**
     * 查询新闻点赞列表
     */
    @ApiOperation(value = "条件分页查询新闻点赞列表", notes = "条件分页查询新闻点赞列表详情")
    @PreAuthorize("@ss.hasPermi('news:thumb:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<LinqThumb>> list(@PathVariable("page") int page, @PathVariable("size") int size, LinqThumb linqThumb) {
        IPage<LinqThumb> iPage = linqThumbService.findPage(new Page<LinqThumb>(page, size), linqThumb);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 删除新闻点赞
     */
    @ApiOperation(value = "删除新闻点赞", notes = "删除新闻点赞详情")
    @PreAuthorize("@ss.hasPermi('news:thumb:remove')")
    @Log(title = "新闻点赞", businessType = BusinessType.DELETE)
    @DeleteMapping("/{thumbIds}")
    public Result<String> remove(@PathVariable("thumbIds") List<Long> thumbIds) {
        return toResult(linqThumbService.deleteLinqThumbByIds(thumbIds));
    }
}
