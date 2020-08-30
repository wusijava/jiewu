package com.linq.news.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.news.domain.LinqComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface LinqCommentService extends IService<LinqComment> {
    /**
     * 条件分页查询新闻评论列表
     *
     * @param linqComment 新闻评论
     *
     * @return 新闻评论集合
     */
    IPage<LinqComment> findPage(Page<LinqComment> linqCommentPage, LinqComment linqComment);

    /**
     * 查询新闻评论
     *
     * @param commentId 新闻评论ID
     *
     * @return 新闻评论
     */
    LinqComment findLinqCommentById(Long commentId);

    /**
     * 新增新闻评论
     *
     * @param linqComment 新闻评论
     *
     * @return 结果
     */
    boolean insertLinqComment(LinqComment linqComment);

    /**
     * 修改新闻评论
     *
     * @param linqComment 新闻评论
     *
     * @return 结果
     */
    boolean updateLinqComment(LinqComment linqComment);

    /**
     * 批量删除新闻评论
     *
     * @param commentIds 需要删除的新闻评论ID
     *
     * @return 结果
     */
    boolean deleteLinqCommentByIds(List<Long> commentIds);
}
