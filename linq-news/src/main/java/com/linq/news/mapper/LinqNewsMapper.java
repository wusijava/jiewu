package com.linq.news.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.news.domain.LinqNews;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/29 12:36 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface LinqNewsMapper extends BaseMapper<LinqNews> {
    /**
     * 查询新闻列表
     *
     * @param news 新闻
     *
     * @return 新闻集合
     */
    IPage<LinqNews> findPageAll(Page<LinqNews> linqNewsPage, @Param("news") LinqNews news);

    /**
     * 根据用户id查询新闻列表
     *
     * @param news   新闻
     * @param userId 用户id
     *
     * @return 新闻集合
     */
    IPage<LinqNews> findPageByUserId(Page<LinqNews> linqNewsPage, @Param("news") LinqNews news, @Param("userId") Long userId);
}