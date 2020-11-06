package com.linq.news.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.news.domain.LinqThumb;
import com.linq.news.mapper.LinqThumbMapper;
import com.linq.news.service.LinqThumbService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinqThumbServiceImpl extends ServiceImpl<LinqThumbMapper, LinqThumb> implements LinqThumbService {
    @Override
    public boolean insertLinqThumb(LinqThumb linqThumb) {
        return  saveOrUpdate(linqThumb);
    }

    @Override
    public IPage<LinqThumb> findPage(Page<LinqThumb> linqThumbPage, LinqThumb linqThumb) {
        return baseMapper.findPage(linqThumbPage,linqThumb);
    }

    @Override
    public boolean deleteLinqThumbByIds(List<Long> thumbIds) {
        return removeByIds(thumbIds);
    }
}
