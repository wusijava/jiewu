package com.linq.news.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.news.domain.LinqCollect;
import com.linq.news.mapper.LinqCollectMapper;
import com.linq.news.service.LinqCollectService;
/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description: 
 * @Version: 1.0.0 
 */
    
@Service
public class LinqCollectServiceImpl extends ServiceImpl<LinqCollectMapper, LinqCollect> implements LinqCollectService{

    /**
     * 新增收藏
     * @param linqCollect
     * @return
     */
    @Override
    public boolean insertLinqCollect(LinqCollect linqCollect) {
        return saveOrUpdate(linqCollect);
    }

    /**
     * 查询收藏
     * @param linqCollectPage
     * @param linqCollect
     * @return
     */
    @Override
    public IPage<LinqCollect> findPage(Page<LinqCollect> linqCollectPage, LinqCollect linqCollect) {
        return baseMapper.findPage(linqCollectPage,linqCollect);
    }

    /**
     * 删除收藏
     * @param collectionIds 需要删除的收藏ID
     *
     * @return
     */
    @Override
    public boolean deleteLinqCollectByIds(List<Long> collectionIds) {
        return removeByIds(collectionIds);
    }
}
