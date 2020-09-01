package com.linq.news.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.common.utils.SecurityUtils;
import com.linq.common.utils.string.StringUtils;
import com.linq.news.domain.LinqNewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.news.domain.LinqNews;
import com.linq.news.mapper.LinqNewsMapper;
import com.linq.news.service.LinqNewsService;

/**
 * @Author: 林义清
 * @Date: 2020/8/29 12:36 下午
 * @Description:
 * @Version: 1.0.0
 */

@Service
public class LinqNewsServiceImpl extends ServiceImpl<LinqNewsMapper, LinqNews> implements LinqNewsService {
    /**
     * 根据用户id查询新闻列表
     *
     * @param news   新闻
     * @param userId 用户id
     *
     * @return 新闻集合
     */
    @Override
    public IPage<LinqNews> findPageByUserId(Page<LinqNews> linqNewsPage, LinqNews news, Long userId) {
        return baseMapper.findPageByUserId(linqNewsPage, news, userId);
    }

    /**
     * 查询新闻列表
     *
     * @param news 新闻
     *
     * @return 新闻集合
     */
    @Override
    public IPage<LinqNews> findPageAll(Page<LinqNews> linqNewsPage, LinqNews news) {
        return baseMapper.findPageAll(linqNewsPage, news);
    }

    /**
     * 查询新闻
     *
     * @param newsId 新闻ID
     *
     * @return 新闻
     */
    @Override
    public LinqNews findLinqNewsById(Long newsId) {
        return getById(newsId);
    }

    /**
     * 新增新闻
     *
     * @param linqNews 新闻
     *
     * @return 结果
     */
    @Override
    public boolean insertLinqNews(LinqNews linqNews) {
        // 爬虫获取
        if (SysUser.isAdmin(linqNews.getUserId())) {
            // 设置创建人
            linqNews.setCreateBy(UserConstants.SYS_ADMIN);
        } else {
            // 设置创建人
            linqNews.setCreateBy(SecurityUtils.getLoginUser().getUser().getUsername());
            // 设置作者id
            linqNews.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        // 设置创建时间 和 更新时间
        linqNews.setCreateTime(new Date());
        linqNews.setUpdateTime(new Date());
        return saveOrUpdate(linqNews);
    }

    /**
     * 修改新闻
     *
     * @param linqNews 新闻
     *
     * @return 结果
     */
    @Override
    public boolean updateLinqNews(LinqNews linqNews) {
        // 更新时间
        linqNews.setUpdateTime(new Date());
        // 修改人
        linqNews.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUsername());
        return saveOrUpdate(linqNews);
    }

    /**
     * 批量删除新闻
     *
     * @param newsIds 需要删除的新闻ID
     *
     * @return 结果
     */
    @Override
    public boolean deleteLinqNewsByIds(List<Long> newsIds) {
        return removeByIds(newsIds);
    }

    /**
     * 校验新闻标题名称是否唯一
     *
     * @param news 新闻
     *
     * @return 结果
     */
    @Override
    public String checkNewsTitleUnique(LinqNews news) {
        // 先识别新闻id是否为空
        Long newsId = StringUtils.isNull(news.getNewsId()) ? -1L : news.getNewsId();
        // 根据新闻名查询
        LinqNews info = getOne(new LambdaQueryWrapper<LinqNews>().eq(LinqNews::getNewsTitle, news.getNewsTitle()));
        if (StringUtils.isNotNull(info) && !Objects.equals(info.getNewsId(), newsId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 公开私有修改
     *
     * @param news 新闻
     *
     * @return 结果
     */
    @Override
    public boolean changeIsPublic(LinqNews news) {
        // 更新时间
        news.setUpdateTime(new Date());
        // 修改人
        news.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUsername());
        return saveOrUpdate(news);
    }

    /**
     * 审核新闻修改
     *
     * @param news 新闻
     *
     * @return 结果
     */
    @Override
    public boolean changeStatus(LinqNews news) {
        return saveOrUpdate(news);
    }
}
