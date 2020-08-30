package com.linq.news.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.news.domain.LinqNewsType;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:11 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface LinqNewsTypeMapper extends BaseMapper<LinqNewsType> {
    /**
     * 条件分页新闻类型列表
     *
     * @param linqNewsTypePage 分页插件
     * @param newsType         菜单类型
     *
     * @return 结果
     */
    IPage<LinqNewsType> findPage(Page<LinqNewsType> linqNewsTypePage, @Param("newsType") LinqNewsType newsType);
}