package com.linq.news.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.news.mapper.LinqLinkMapper;
import com.linq.news.domain.LinqLink;
import com.linq.news.service.LinqLinkService;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description:
 * @Version: 1.0.0
 */

@Service
public class LinqLinkServiceImpl extends ServiceImpl<LinqLinkMapper, LinqLink> implements LinqLinkService {
    /**
     * 查询友情链接列表
     *
     * @param link 友情链接
     *
     * @return 友情链接集合
     */
    @Override
    public IPage<LinqLink> findPage(Page<LinqLink> linqLinkPage, LinqLink link) {
        return baseMapper.findPage(linqLinkPage, link);
    }

    /**
     * 查询友情链接
     *
     * @param linqId 友情链接ID
     *
     * @return 友情链接
     */
    @Override
    public LinqLink findLinqLinkById(Long linqId) {
        return getById(linqId);
    }

    /**
     * 新增友情链接
     *
     * @param linqLink 友情链接
     *
     * @return 结果
     */
    @Override
    public boolean insertLinqLink(LinqLink linqLink) {
        return saveOrUpdate(linqLink);
    }

    /**
     * 修改友情链接
     *
     * @param linqLink 友情链接
     *
     * @return 结果
     */
    @Override
    public boolean updateLinqLink(LinqLink linqLink) {
        return saveOrUpdate(linqLink);
    }

    /**
     * 批量删除友情链接
     *
     * @param linqIds 需要删除的友情链接ID
     *
     * @return 结果
     */

    @Override
    public boolean deleteLinqLinkByIds(List<Long> linqIds) {
        return removeByIds(linqIds);
    }
}
