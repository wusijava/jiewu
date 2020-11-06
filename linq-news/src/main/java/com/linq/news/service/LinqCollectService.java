package com.linq.news.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.news.domain.LinqCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linq.news.domain.LinqComment;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description: 
 * @Version: 1.0.0 
 */
    
public interface LinqCollectService extends IService<LinqCollect>{
    /**
     * 新增收藏
     * @param linqCollect
     * @return
     */
    boolean insertLinqCollect(LinqCollect linqCollect);

    /**
     * 分页按条件查询收藏
     * @param linqCollectPage
     * @param linqCollect
     * @return
     */
    IPage<LinqCollect> findPage(Page<LinqCollect> linqCollectPage, LinqCollect linqCollect);

    /**
     * 批量删除收藏
     *
     * @param collectionIds 需要删除的收藏ID
     *
     * @return 结果
     */
    boolean deleteLinqCollectByIds(List<Long> collectionIds);
}
