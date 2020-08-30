package com.linq.news.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linq.news.domain.LinqNewsType;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:10 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface LinqNewsTypeService extends IService<LinqNewsType> {
    /**
     * 条件分页新闻类型列表
     *
     * @param linqNewsTypePage 分页插件
     * @param newsType         菜单类型
     *
     * @return 结果
     */
    IPage<LinqNewsType> findPage(Page<LinqNewsType> linqNewsTypePage, LinqNewsType newsType);

    /**
     * 查询新闻类型
     *
     * @param newsTypeId 新闻类型ID
     *
     * @return 新闻类型
     */
    LinqNewsType findNewsTypeById(Long newsTypeId);

    /**
     * 新增新闻类型
     *
     * @param newsType 新闻类型
     *
     * @return 结果
     */
    boolean insertNewsType(LinqNewsType newsType);

    /**
     * 修改新闻类型
     *
     * @param newsType 新闻类型
     *
     * @return 结果
     */
    boolean updateNewsType(LinqNewsType newsType);

    /**
     * 删除新闻类型信息
     *
     * @param newsTypeIds 新闻类型ID
     *
     * @return 结果
     */
    boolean deleteNewsTypeByIds(List<Long> newsTypeIds);

    /**
     * 校验新闻类型名称是否唯一
     *
     * @param newsType 新闻类型
     *
     * @return 结果
     */
     String checkNewsTypeNameUnique(LinqNewsType newsType);
}

