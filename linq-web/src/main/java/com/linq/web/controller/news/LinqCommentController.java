package com.linq.web.controller.news;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.controller.BaseController;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.news.domain.LinqComment;
import com.linq.news.domain.LinqLink;
import com.linq.news.domain.LinqNews;
import com.linq.news.service.LinqCollectService;
import com.linq.news.service.LinqCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/30 7:46 下午
 * @Description: 新闻评论管理
 * @Version: 1.0.0
 */
@Api(tags = "新闻评论管理接口")
@RestController
@RequestMapping("/news/comment")
public class LinqCommentController extends BaseController {
    @Autowired
    private LinqCommentService commentService;

    /**
     * 查询新闻评论列表
     */
    @ApiOperation(value = "条件分页查询新闻评论列表", notes = "条件分页查询新闻评论列表详情")
    @PreAuthorize("@ss.hasPermi('news:comment:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<LinqComment>> list(@PathVariable("page") int page, @PathVariable("size") int size, LinqComment linqComment) {
        IPage<LinqComment> iPage = commentService.findPage(new Page<LinqComment>(page, size), linqComment);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 获取新闻评论详细信息
     */
    @ApiOperation(value = "获取新闻评论详细信息", notes = "获取新闻评论详细信息详情")
    @PreAuthorize("@ss.hasPermi('news:comment:query')")
    @GetMapping(value = "/{commentId}")
    public Result<LinqComment> getInfo(@PathVariable("commentId") Long commentId) {
        return ResultUtils.success(commentService.findLinqCommentById(commentId));
    }

    /**
     * 新增新闻评论
     */
    @ApiOperation(value = "新增新闻评论", notes = "新增新闻评论详情")
    @PreAuthorize("@ss.hasPermi('news:comment:add')")
    @Log(title = "新闻评论", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@RequestBody LinqComment linqComment) {
        return toResult(commentService.insertLinqComment(linqComment));
    }

    /**
     * 修改新闻评论
     */
    @ApiOperation(value = "修改新闻评论", notes = "修改新闻评论详情")
    @PreAuthorize("@ss.hasPermi('news:comment:edit')")
    @Log(title = "新闻评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@RequestBody LinqComment linqComment) {
        return toResult(commentService.updateLinqComment(linqComment));
    }

    /**
     * 删除新闻评论
     */
    @PreAuthorize("@ss.hasPermi('news:comment:remove')")
    @Log(title = "新闻评论", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commentIds}")
    public Result<String> remove(@PathVariable List<Long> commentIds) {
        return toResult(commentService.deleteLinqCommentByIds(commentIds));
    }
}
