package com.linq.news.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.news.domain.LinqComment;
import org.springframework.data.repository.query.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface LinqCommentMapper extends BaseMapper<LinqComment> {
    /**
     * 条件分页查询新闻评论列表
     *
     * @param comment 新闻评论
     *
     * @return 新闻评论集合
     */
    IPage<LinqComment> findPage(Page<LinqComment> linqCommentPage, @Param("comment") LinqComment comment);
}