package com.linq.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.common.utils.spring.SpringUtils;
import com.linq.news.domain.LinqNews;
import com.linq.news.domain.LinqNewsType;
import com.linq.news.domain.NewsDocument;
import com.linq.news.mapper.NewsDocumentDao;
import com.linq.news.service.LinqNewsService;
import com.linq.news.service.LinqNewsTypeService;
import com.linq.news.service.NewsDocumentService;
import com.linq.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 林义清
 * @Date: 2020/9/2 3:56 下午
 * @Description:
 * @Version: 1.0.0
 */
@Service
public class NewsDocumentServiceImpl implements NewsDocumentService {
    @Autowired
    private NewsDocumentDao newsDocumentDao;
    @Autowired
    private LinqNewsService linqNewsService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private LinqNewsTypeService newsTypeService;

    /**
     * 导入新闻数据
     */
    @Override
    public void importNews() {
        List<LinqNews> newsList = linqNewsService
                .list(new LambdaQueryWrapper<LinqNews>()
                              .eq(LinqNews::getDelFlag, UserConstants.NORMAL) // 未删除
                              .eq(LinqNews::getIsPublic, UserConstants.PUBLIC) // 公开
                              .eq(LinqNews::getStatus, UserConstants.PASSED) // 审核成功
                );
        List<NewsDocument> newsDocumentList = new ArrayList<>();
        newsList.stream().filter(Objects::nonNull).forEach(news -> {
            // 创建 NewsDocument
            NewsDocument newsDocument = new NewsDocument();
            newsDocument
                    .setNewsId(news.getNewsId()) // 新闻id
                    .setAuthor(userService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserId, news.getUserId())// 作者
                                                          .select(SysUser::getUsername, SysUser::getAvatar, SysUser::getPhone, SysUser::getEmail, SysUser::getSex, SysUser::getNickName)))
                    .setNewsType(newsTypeService.getOne(new LambdaQueryWrapper<LinqNewsType>().eq(LinqNewsType::getNewsTypeId, news.getNewsTypeId()) // 新闻类别
                                                                .select(LinqNewsType::getNewsTypeId, LinqNewsType::getNewsTypeName)))
                    .setNewsTitle(news.getNewsTitle()) // 新闻标题
                    .setNewsContent(news.getNewsContent()); // 新闻内容
            switch (news.getNewsAttr()) { // 新闻属性
                case "0":
                    newsDocument.setNewsAttr(UserConstants.HEADLINE_AREA_NEWS);
                    break;
                case "1":
                    newsDocument.setNewsAttr(UserConstants.SLIDE_SHOW_NEWS);
                    break;
                case "2":
                    newsDocument.setNewsAttr(UserConstants.HOT_SPOT_NEWS);
                    break;
            }
            newsDocument
                    .setNewsImage(news.getNewsImage())// 新闻封面
                    .setThumbs(news.getThumbs()) // 新闻点赞数
                    .setVisits(news.getVisits()) // 新闻浏览量
                    .setComments(news.getComments()) // 新闻评论数
                    .setNewsSource(news.getNewsSource()) // 新闻来源
                    .setNewsSourceAuthor(news.getNewsSourceAuthor()) // 新闻博客原创作者(博客使用)
                    .setNewsSourceTags(news.getNewsSourceTags())// 新闻博客分类标签
                    .setCreateBy(news.getCreateBy()) // 创建者
                    .setCreateTime(news.getCreateTime()) // 创建时间
            ;
            newsDocumentList.add(newsDocument);
        });
        newsDocumentDao.saveAll(newsDocumentList);
    }
}
