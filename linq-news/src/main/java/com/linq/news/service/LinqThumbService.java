package com.linq.news.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linq.news.domain.LinqThumb;

import java.util.List;

public interface LinqThumbService  extends IService<LinqThumb> {
    /**
     * 新增点赞
     * @param linqThumb
     * @return
     */
    boolean insertLinqThumb(LinqThumb linqThumb);

    /**
     * 分页按条件查询新增点赞
     * @param linqThumbPage
     * @param linqThumb
     * @return
     */
    IPage<LinqThumb> findPage(Page<LinqThumb> linqThumbPage, LinqThumb linqThumb);

    /**
     * 批量删除新增点赞
     *
     * @param thumbIds 需要删除的新增点赞ID
     *
     * @return 结果
     */
    boolean deleteLinqThumbByIds(List<Long> thumbIds);

}
