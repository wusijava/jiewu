package com.linq.news.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻点赞表
 */
@Data
@TableName(value = "linq_thumb")
public class LinqThumb implements Serializable {
    /**
     * 点赞id
     */
    @TableId(value = "thumb_id", type = IdType.AUTO)
    private Long thumbId;

    /**
     * 新闻id
     */
    @TableField(value = "news_id")
    private Long newsId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 点赞时间
     */
    @TableField(value = "thumb_time")
    private Date thumbTime;

    private static final long serialVersionUID = 1L;

    /////////////////////////////////// 附加  ///////////////////////////////////
    @TableField(exist = false)
    private LinqNews news;
}
