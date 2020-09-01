package com.linq.news.task.config.target;

import lombok.Data;


/**
 * @Author: 林义清
 * @Date: 2020/9/1 10:53 上午
 * @Description:
 * @Version: 1.0.0
 */
@Data
public class CsdnNewsProperties {
    // 请求地址
    public static final String urlPrefix = "https://blog.csdn.net/nav/";
    public static final String urlSuffix = "java,python,web,arch,5g,db,game,mobile,ops,sec,engineering,iot,fund,avi,other";
    // 详情页目标链接地址css选择器
    public static final String targetUrlCssSelector = ".clearfix";
    // 获取所有详情页列表css选择器
    public static final String detailSelectCssSelector = ".baidu_pl";
    // 标题css选择器
    public static final String newsTitleCssSelector = "#articleContentId";
    // 类型id
    public static final Long newsTypeId = 18L;
    // 文章内容css选择器
    public static final String newsContentCssSelector = "#article_content";
    // 文章封面css选择器
    public static final String newsImageCssSelector = ".avatar_pic";
    // key值
    public static final String fieldKey = "csdnNews";
}
