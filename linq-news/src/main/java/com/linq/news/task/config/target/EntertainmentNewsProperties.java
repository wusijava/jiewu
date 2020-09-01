package com.linq.news.task.config.target;

import lombok.Data;

/**
 * @Author: 林义清
 * @Date: 2020/9/1 10:53 上午
 * @Description:
 * @Version: 1.0.0
 */
@Data
public class EntertainmentNewsProperties {
    // 请求地址
    public static final String url = "https://ent.sina.com.cn/";
    // 详情页目标链接地址css选择器
    public static final String targetUrlCssSelector = ".seo_data_list";
    // 获取所有详情页列表css选择器
    public static final String detailSelectCssSelector = ".main-content.w1240";
    // 标题 css选择器
    public static final String newsTitleCssSelector = ".main-title";
    // 类型id
    public static final Long newsTypeId = 16L;
    // 文章内容css选择器
    public static final String newsContentCssSelector = ".article-content-left";
    // 文章封面css选择器
    public static final String newsImageCssSelector = ".img_wrapper > img";
    // key值
    public static final String fieldKey = "entertainmentNews";
}
