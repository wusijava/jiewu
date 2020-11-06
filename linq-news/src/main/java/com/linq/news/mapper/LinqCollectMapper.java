package com.linq.news.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.news.domain.LinqCollect;
import com.linq.news.domain.LinqComment;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description: 
 * @Version: 1.0.0 
 */
    
public interface LinqCollectMapper extends BaseMapper<LinqCollect> {
    /**
     * 条件分页查询新闻收藏
     * @param linqCollectPage
     * @param linqCollect
     * @return
     */
    IPage<LinqCollect> findPage(Page<LinqCollect> linqCollectPage, @Param("linqCollect") LinqCollect linqCollect);
}