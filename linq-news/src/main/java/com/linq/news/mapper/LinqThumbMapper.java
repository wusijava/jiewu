package com.linq.news.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.news.domain.LinqThumb;
import org.apache.ibatis.annotations.Param;

public interface LinqThumbMapper  extends BaseMapper<LinqThumb> {
    /**
     * 条件分页查询新闻收藏
     * @param linqThumbPage
     * @param linqThumb
     * @return
     */
    IPage<LinqThumb> findPage(Page<LinqThumb> linqThumbPage, @Param("linqThumb") LinqThumb linqThumb);
}
