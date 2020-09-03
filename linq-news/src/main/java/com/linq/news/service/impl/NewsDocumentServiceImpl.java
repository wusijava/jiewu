package com.linq.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.common.exception.CustomException;
import com.linq.common.utils.string.StringUtils;
import com.linq.news.domain.LinqNews;
import com.linq.news.domain.LinqNewsType;
import com.linq.news.domain.NewsDocument;
import com.linq.news.mapper.NewsDocumentDao;
import com.linq.news.service.LinqNewsService;
import com.linq.news.service.LinqNewsTypeService;
import com.linq.news.service.NewsDocumentService;
import com.linq.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 林义清
 * @Date: 2020/9/2 3:56 下午
 * @Description:
 * @Version: 1.0.0
 */
@Slf4j
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
    public boolean importNews() {
        List<LinqNews> newsList = linqNewsService
                .list(new LambdaQueryWrapper<LinqNews>()
                              .eq(LinqNews::getDelFlag, UserConstants.NORMAL) // 未删除
                              .eq(LinqNews::getIsPublic, UserConstants.PUBLIC) // 公开
                              .eq(LinqNews::getStatus, UserConstants.PASSED) // 审核成功
                );
        List<NewsDocument> newsDocumentList = new ArrayList<>();
        newsList.stream().filter(Objects::nonNull).forEach(news -> {
            newsDocumentList.add(convertLinqNewsToNewsDocument(news));
        });
        Iterable<NewsDocument> iterable = newsDocumentDao.saveAll(newsDocumentList);
        Iterator<NewsDocument> iterator = iterable.iterator();
        int ret = 0;
        if (iterator.hasNext()) {
            ret++;
            iterator.next();
        }
        return ret > 0;
    }

    /**
     * 将LinqNews转换为NewsDocument
     *
     * @param news
     *
     * @return
     */
    private NewsDocument convertLinqNewsToNewsDocument(LinqNews news) {
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
        log.info("\n正在转换LinqNews------>NewsDocument---->当前时间: {}", System.currentTimeMillis());
        return newsDocument;
    }

    /**
     * 根据id删除新闻
     *
     * @param newsId 新闻id
     */
    @Override
    public void deleteByNewsId(Long newsId) {
        newsDocumentDao.deleteById(newsId);
    }

    /**
     * 查询新闻
     *
     * @param newsId 新闻ID
     *
     * @return 新闻
     */
    @Override
    public NewsDocument findNewsById(Long newsId) {
        return newsDocumentDao.findById(newsId).get();
    }

    /**
     * 新增新闻
     *
     * @param news 新闻
     *
     * @return 结果
     */
    @Override
    public void saveNews(LinqNews news) {
        NewsDocument info = newsDocumentDao.findByNewsTitle(news.getNewsTitle());
        if (StringUtils.isNotNull(info)) {
            throw new CustomException("新增新闻'" + news.getNewsTitle() + "'失败，新闻已存在");
        }
        // 解析LinqNews 构建NewsDocument
        newsDocumentDao.save(convertLinqNewsToNewsDocument(news));
    }


    /**
     * 批量删除新闻
     *
     * @param newsIds 需要删除的新闻ID
     *
     * @return 结果
     */
    @Override
    public void deleteNewsByIds(List<Long> newsIds) {
        if (CollectionUtils.isNotEmpty(newsIds)) {
            List<NewsDocument> newsDocumentList = new ArrayList<>();
            newsIds.stream().filter(Objects::nonNull)
                    .forEach(newsId -> {
                        NewsDocument newsDocument = new NewsDocument();
                        newsDocument.setNewsId(newsId);
                        newsDocumentList.add(newsDocument);
                    });
            newsDocumentDao.deleteAll(newsDocumentList);
        }
    }

    /**
     * 分页根据关键字搜索
     */
    @Override
    public Page<NewsDocument> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return newsDocumentDao.findAllByNewsTitleLikeOrNewsContentLikeOrNewsAttrLikeOrNewsSourceAuthorLikeOrNewsSourceTagsLikeOrCreateByLike(keyword, keyword, keyword, keyword, keyword, keyword, pageable);
    }
}
