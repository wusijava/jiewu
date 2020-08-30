package com.linq.news.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.news.domain.LinqLink;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface LinqLinkService extends IService<LinqLink> {

    /**
     * 查询友情链接列表
     *
     * @param link 友情链接
     *
     * @return 友情链接集合
     */
    IPage<LinqLink> findPage(Page<LinqLink> linqLinkPage, LinqLink link);

    /**
     * 查询友情链接
     *
     * @param linqId 友情链接ID
     *
     * @return 友情链接
     */
    LinqLink findLinqLinkById(Long linqId);

    /**
     * 新增友情链接
     *
     * @param linqLink 友情链接
     *
     * @return 结果
     */
    boolean insertLinqLink(LinqLink linqLink);

    /**
     * 修改友情链接
     *
     * @param linqLink 友情链接
     *
     * @return 结果
     */
    boolean updateLinqLink(LinqLink linqLink);

    /**
     * 批量删除友情链接
     *
     * @param linqIds 需要删除的友情链接ID
     *
     * @return 结果
     */
    boolean deleteLinqLinkByIds(List<Long> linqIds);
}
