package com.linq.news.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.linq.common.core.domain.model.BaseEntity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description:
 * @Version: 1.0.0
 */

/**
 * 新闻评论表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "linq_comment")
public class LinqComment extends BaseEntity implements Serializable {
    /**
     * 新闻评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 新闻id
     */
    @TableField(value = "news_id")
    private Long newsId;

    /**
     * 新闻评论内容
     */
    @TableField(value = "comment_content")
    private String commentContent;

    /**
     * 点赞数
     */
    @TableField(value = "thumbs")
    private Long thumbs;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    @TableField(value = "del_flag")
    private String delFlag;

    /////////////////////////////////// 附加  ///////////////////////////////////
    @TableField(exist = false)
    private LinqNews news;

    /////////////////////////////////// 附加  ///////////////////////////////////
}