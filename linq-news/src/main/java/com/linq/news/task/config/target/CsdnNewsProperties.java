package com.linq.news.task.config.target;

import com.linq.common.utils.string.StringUtils;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: 林义清
 * @Date: 2020/9/1 10:53 上午
 * @Description: Csdn爬取配置
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
    public static final String detailSelectCssSelector = ".baidu_pl";
    // 标题css选择器
    public static final String newsTitleCssSelector = "#articleContentId";
    // 原创作者
    public static final String newsSourceAuthorSelector = "#uid > span.name";
    // 点赞数
    public static final String thumbsSelector = "#asideProfile > div:nth-child(4) > dl:nth-child(3) > dt > span";
    // 浏览量
    public static final String visitsSelector = "#asideProfile > div:nth-child(2) > dl:nth-child(4) > dt > span";
    // 评论数
    public static final String commentsSelector = "#asideProfile > div:nth-child(4) > dl:nth-child(4) > dt > span";
    // 收藏数
    public static final String cllectsSelector = "#asideProfile > div:nth-child(4) > dl:nth-child(5) > dt > span";
    // 类型id
    public static final Long newsTypeId = 18L;
    // 文章内容css选择器
    public static final String newsContentCssSelector = "#article_content";
    // 文章封面css选择器
    public static final String newsImageCssSelector = "#asideProfile > div.profile-intro.d-flex > div.avatar-box.d-flex.justify-content-center.flex-column > a > img.avatar_pic";
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
