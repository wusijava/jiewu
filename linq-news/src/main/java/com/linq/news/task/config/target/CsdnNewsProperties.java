package com.linq.news.task.config.target;

import com.linq.common.utils.string.StringUtils;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: 林义清
 * @Date: 2020/9/1 10:53 上午
 * @Description: Csdn爬取配置 注意详细页面css选择范围尽量大点 否则找不到
 * @Version: 1.0.0
 */
@Data
public class CsdnNewsProperties {
    // 请求地址前缀
    public static final String urlPrefix = "https://blog.csdn.net/nav/";
    // 请求地址后缀 有规律
    public static final String urlSuffix = "java,python,web,arch,5g,db,game,mobile,ops,sec,engineering,iot,fund,avi,other";
    // 详情页目标链接地址css选择器
    public static final String targetUrlCssSelector = ".clearfix";
    // 获取所有详情页列表css选择器
    //    public static final String detailSelectCssSelector = ".baidu_pl";
    public static final String detailSelectCssSelector = "div.main_father.clearfix.d-flex.justify-content-center";
    // 标题css选择器
    public static final String newsTitleCssSelector = "#articleContentId";
    // 原创作者选择器
    public static final String newsSourceAuthorCssSelector = "#uid > span.name";
    // 点赞数选择器
    //    public static final String thumbsSelector = "#asideProfile > div:nth-child(4) > dl:nth-child(3) > dt > span";
    public static final String thumbsCssSelector = "#asideProfile > div:nth-child(4) > dl:nth-child(5)";
    // 浏览量选择器
    //    public static final String visitsSelector = "#asideProfile > div:nth-child(2) > dl:nth-child(4) > dt > span";
    public static final String visitsCssSelector = "#asideProfile > div:nth-child(2) > dl:nth-child(1)";
    // 评论数选择器
    //    public static final String commentsSelector = "#asideProfile > div:nth-child(4) > dl:nth-child(4) > dt > span";
    public static final String commentsCssSelector = "#asideProfile > div:nth-child(4) > dl:nth-child(4)";
    // 收藏数选择器
    //    public static final String cllectsSelector = "#asideProfile > div:nth-child(4) > dl:nth-child(5) > dt > span";
    public static final String cllectsCssSelector = "#asideProfile > div:nth-child(4) > dl:nth-child(5)";
    // 类型id
    public static final Long newsTypeId = 18L;
    // 博客文章标签
    public static final String newsSourceTagsCssSelector = "#mainBox > main > div.blog-content-box > div > div > div.article-info-box > div.blog-tags-box > div.tags-box.artic-tag-box";
    // 文章内容css选择器
    public static final String newsContentCssSelector = "#article_content";
    // 文章封面css选择器
    public static final String newsImageCssSelector = "img.avatar_pic";
    // key值
    public static final String fieldKey = "csdnNews";


    /**
     * 拼接初始化的URL
     */
    public static List<String> getInitUrlList() {
        List<String> initCrawlerUrlList = null;
        // 前缀
        if (StringUtils.isNotEmpty(urlPrefix)) {
            // 后缀逗号取出
            String[] initCrawlerUrlArray = urlSuffix.split(",");
            if (initCrawlerUrlArray.length > 0) {
                for (int i = 0; i < initCrawlerUrlArray.length; i++) {
                    String initUrl = initCrawlerUrlArray[i];
                    if (StringUtils.isNotEmpty(initUrl)) {
                        if (!initUrl.toLowerCase().startsWith("http")) {
                            initUrl = urlPrefix + initUrl;
                            initCrawlerUrlArray[i] = initUrl;
                        }
                    }
                }
            }
            initCrawlerUrlList = Arrays.stream(initCrawlerUrlArray).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        }
        return initCrawlerUrlList;
    }
}
