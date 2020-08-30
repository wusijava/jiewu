package com.linq.news.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.linq.common.core.domain.model.BaseEntity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author: 林义清
 * @Date: 2020/8/29 12:36 下午
 * @Description:
 * @Version: 1.0.0
 */

/**
 * 新闻表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "linq_news")
public class LinqNews extends BaseEntity implements Serializable {
    /**
     * 新闻id
     */
    @TableId(value = "news_id", type = IdType.AUTO)
    private Long newsId;
    /**
     * 作者id
     */
    @TableField(value = "user_id")
    private Long userId;
    /**
     * 新闻类别id
     */
    @TableField(value = "news_type_id")
    private Long newsTypeId;

    /**
     * 新闻标题
     */
    @NotBlank(message = "新闻标题不能为空")
    @Size(min = 0, max = 30, message = "新闻标题长度不能超过30个字符")
    @TableField(value = "news_title")
    private String newsTitle;

    /**
     * 新闻内容
     */
    @NotBlank(message = "新闻内容不能为空")
    @TableField(value = "news_content")
    private String newsContent;

    /**
     * 新闻属性 0.头条区新闻 1.幻灯片区新闻 2.热点区新闻
     */
    @TableField(value = "news_attr")
    private String newsAttr;

    /**
     * 新闻封面
     */
    @TableField(value = "news_image")
    private String newsImage;

    /**
     * 新闻显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 新闻是否公开（0.公开 1.私有）
     */
    @TableField(value = "is_public")
    private String isPublic;

    /**
     * 新闻点赞数
     */
    @TableField(value = "thumbs")
    private Long thumbs;

    /**
     * 新闻浏览量
     */
    @TableField(value = "visits")
    private Long visits;

    /**
     * 新闻评论数
     */
    @TableField(value = "comments")
    private Long comments;

    /**
     * 新闻收藏数
     */
    @TableField(value = "collects")
    private Long collects;

    /**
     * 审核状态（0.审核中 1.审核成功 2.审核失败）
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    @TableField(value = "del_flag")
    private String delFlag;


    /////////////////////////////////// 附加  ///////////////////////////////////

    @TableField(exist = false)
    private LinqNewsType newsType;

    /////////////////////////////////// 附加  ///////////////////////////////////
}