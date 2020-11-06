package com.linq.news.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.common.exception.CustomException;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.SecurityUtils;
import com.linq.common.utils.string.StringUtils;
import com.linq.news.domain.LinqNewsType;
import com.linq.news.domain.NewsDocument;
import com.linq.news.mapper.NewsDocumentDao;
import com.linq.news.service.NewsDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.news.domain.LinqNews;
import com.linq.news.mapper.LinqNewsMapper;
import com.linq.news.service.LinqNewsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 林义清
 * @Date: 2020/8/29 12:36 下午
 * @Description:
 * @Version: 1.0.0
 */
@Slf4j
@Service
public class LinqNewsServiceImpl extends ServiceImpl<LinqNewsMapper, LinqNews> implements LinqNewsService {
    @Autowired
    private NewsDocumentService newsDocumentService;

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
        // 判断新闻标题是否重复
        if (UserConstants.NOT_UNIQUE.equals(checkNewsTitleUnique(linqNews))) {
            throw new CustomException("新增新闻'" + linqNews.getNewsTitle() + "'失败，新闻标题已存在");
        }
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
    @Transactional
    @Override
    public boolean deleteLinqNewsByIds(List<Long> newsIds) {
        /**
         * 判断全局检索中是否存在 如果存在就将全局检索中的也删除
         */
        newsIds.stream().filter(Objects::nonNull)
                .forEach(newsId -> {
                    // 查出这个新闻
                    LinqNews one = getById(newsId);
                    // 根据id查询 全局检索中是否存在该新闻 存在就删除
                    checkExistedInESAndRemove(one);
                });
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
    @Transactional
    @Override
    public boolean changeIsPublic(LinqNews news) {
        /**
         * 修复bug  java.lang.NullPointerException: null
         */
        // 查询出当前所有信息
        news = getById(news.getNewsId());
        /**
         * 查询全局检索中是否存在该新闻
         * 我们只有 公开的时候才放到全局检索中去 否则 从全局检索中删除
         * 在这些的前提前必须是 审核通过
         */
        switch (news.getIsPublic()) {
            case "0": // 公开
                if (news.getStatus().equals(UserConstants.PASSED)) {// 判断是否审核通过
                    newsDocumentService.saveNews(news);
                }
                break;
            case "1": // 私有
                // 根据id查询 全局检索中是否存在该新闻 存在就删除
                checkExistedInESAndRemove(news);
                break;
        }
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
    @Transactional
    @Override
    public boolean changeStatus(LinqNews news) {
        /**
         *   都会先查询是全局检索中是否存在这个新闻
         *   审核通过了: 就增加到全局检索 --> 公开的话才放到全局检索 不公开就放到全局检索
         *   审核中或者审核失败: 存在就进行删除
         *
         */
        switch (news.getStatus()) {
            case "1": // 审核通过 就增加到全局检索
                if (news.getIsPublic().equals(UserConstants.PUBLIC)) {
                    newsDocumentService.saveNews(news);
                }
                break;
            case "0": // 审核中或者审核失败
            case "2":
                // 根据id查询 全局检索中是否存在该新闻 存在就删除
                checkExistedInESAndRemove(news);
                break;
        }
        return saveOrUpdate(news);
    }

    /**
     * 变更新闻浏览数、评论数、点赞数，收藏数
     * @param params
     * @return
     */
    @Override
    public boolean changeCountById(Map params) {
        return baseMapper.changeCountById(params);
    }


    /**
     * 判断ES中是否存在该条新闻 存在就删除
     *
     * @param news 新闻
     */
    private void checkExistedInESAndRemove(LinqNews news) {
        log.info("判断ES中是否存在该条新闻--->{}", news);
        // 先识别新闻
        NewsDocument newsDocument = newsDocumentService.findNewsById(news.getNewsId());
        // 查到了该新闻
        if (StringUtils.isNotNull(newsDocument.getNewsId())) {
            newsDocumentService.deleteByNewsId(newsDocument.getNewsId());
        }
    }

}
