package com.linq.news.service;

import com.linq.news.domain.LinqNews;
import com.linq.news.domain.NewsDocument;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/9/2 3:56 下午
 * @Description:
 * @Version: 1.0.0
 */
public interface NewsDocumentService {
    /**
     * 导入新闻数据
     */
    boolean importNews();

    /**
     * 根据id删除新闻
     *
     * @param newsId 新闻id
     */
    void deleteByNewsId(Long newsId);

    /**
     * 查询新闻
     *
     * @param newsId 新闻ID
     *
     * @return 新闻
     */
    NewsDocument findNewsById(Long newsId);

    /**
     * 新增|保存新闻
     *
     * @param news 新闻
     *
     * @return 结果
     */
    void saveNews(LinqNews news);


    /**
     * 批量删除新闻
     *
     * @param newsIds 需要删除的新闻ID
     *
     * @return 结果
     */
    void deleteNewsByIds(List<Long> newsIds);

    /**
     *
     * 分页根据关键字搜索
     */
    Page<NewsDocument> search(String keyword, Integer pageNum, Integer pageSize);
}
