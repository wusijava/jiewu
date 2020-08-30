package com.linq.news.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.news.domain.LinqLink;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface LinqLinkMapper extends BaseMapper<LinqLink> {
    /**
     * 查询友情链接列表
     *
     * @param link 友情链接
     *
     * @return 友情链接集合
     */
    IPage<LinqLink> findPage(Page<LinqLink> linqLinkPage, @Param("link") LinqLink link);
}