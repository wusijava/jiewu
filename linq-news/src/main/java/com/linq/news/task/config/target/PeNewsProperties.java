package com.linq.news.task.config.target;

import lombok.Data;

/**
 * @Author: 林义清
 * @Date: 2020/9/1 11:04 上午
 * @Description:
 * @Version: 1.0.0
 */
@Data
public class PeNewsProperties {
    // 请求地址
    public final static String url = "http://sports.sina.com.cn/nba/";
    // 详情页目标链接地址css选择器
    public final static String targetUrlCssSelector = "div.news-list-b";
    // 获取所有详情页列表css选择器
    public final static String detailSelectCssSelector = ".main-content.w1240";
    // 标题css选择器
    public final static String newsTitleCssSelector = ".main-title";
    // 类型id
    public final static Long newsTypeId = 4L;
    // 文章内容css选择器
    public final static String newsContentCssSelector = "#artibody";
    // 文章封面css选择器
    public final static String newsImageCssSelector = ".img_wrapper > img";
    // key值
    public final static String fieldKey = "peNews";
}
