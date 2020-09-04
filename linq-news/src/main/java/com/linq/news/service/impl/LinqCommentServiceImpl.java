package com.linq.news.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.news.mapper.LinqCommentMapper;
import com.linq.news.domain.LinqComment;
import com.linq.news.service.LinqCommentService;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description:
 * @Version: 1.0.0
 */

@Service
public class LinqCommentServiceImpl extends ServiceImpl<LinqCommentMapper, LinqComment> implements LinqCommentService {
    /**
     * 条件分页查询新闻评论列表
     *
     * @param linqComment 新闻评论
     *
     * @return 新闻评论集合
     */
    @Override
    public IPage<LinqComment> findPage(Page<LinqComment> linqCommentPage, LinqComment linqComment) {
         return baseMapper.findPage(linqCommentPage,linqComment);
    }

    /**
     * 查询新闻评论
     *
     * @param commentId 新闻评论ID
     *
     * @return 新闻评论
     */
    @Override
    public LinqComment findLinqCommentById(Long commentId) {
        return getById(commentId);
    }

    /**
     * 新增新闻评论
     *
     * @param linqComment 新闻评论
     *
     * @return 结果
     */
    @Override
    public boolean insertLinqComment(LinqComment linqComment) {
        return saveOrUpdate(linqComment);
    }

    /**
     * 修改新闻评论
     *
     * @param linqComment 新闻评论
     *
     * @return 结果
     */
    @Override
    public boolean updateLinqComment(LinqComment linqComment) {
        return saveOrUpdate(linqComment);
    }

    /**
     * 批量删除新闻评论
     *
     * @param commentIds 需要删除的新闻评论ID
     *
     * @return 结果
     */
    @Override
    public boolean deleteLinqCommentByIds(List<Long> commentIds) {
        return removeByIds(commentIds);
    }
}
