package com.linq.news.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.news.domain.LinqNews;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Author: 林义清
 * @Date: 2020/8/29 12:36 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface LinqNewsService extends IService<LinqNews> {
    /**
     * 根据用户id查询新闻列表
     *
     * @param news   新闻
     * @param userId 用户id
     *
     * @return 新闻集合
     */
    IPage<LinqNews> findPageByUserId(Page<LinqNews> linqNewsPage, LinqNews news, Long userId);

    /**
     * 查询全部新闻列表
     *
     * @param news 新闻
     *
     * @return 新闻集合
     */
    IPage<LinqNews> findPageAll(Page<LinqNews> linqNewsPage, LinqNews news);

    /**
     * 查询新闻
     *
     * @param newsId 新闻ID
     *
     * @return 新闻
     */
    LinqNews findLinqNewsById(Long newsId);

    /**
     * 新增新闻
     *
     * @param linqNews 新闻
     *
     * @return 结果
     */
    boolean insertLinqNews(LinqNews linqNews);

    /**
     * 修改新闻
     *
     * @param linqNews 新闻
     *
     * @return 结果
     */
    boolean updateLinqNews(LinqNews linqNews);

    /**
     * 批量删除新闻
     *
     * @param newsIds 需要删除的新闻ID
     *
     * @return 结果
     */
    boolean deleteLinqNewsByIds(List<Long> newsIds);

    /**
     * 校验新闻标题名称是否唯一
     *
     * @param news 新闻
     *
     * @return 结果
     */
    String checkNewsTitleUnique(LinqNews news);

    /**
     * 公开私有修改
     *
     * @param news 新闻
     *
     * @return 结果
     */
    boolean changeIsPublic(LinqNews news);

    /**
     * 审核新闻修改
     *
     * @param news 新闻
     *
     * @return 结果
     */
    boolean changeStatus(LinqNews news);

    /**
     * 变更新闻浏览数、评论数、点赞数，收藏数
     * @param params
     * @return
     */
    boolean changeCountById(Map params);
}
