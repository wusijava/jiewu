package com.linq.news.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description:
 * @Version: 1.0.0
 */

/**
 * 新闻收藏表
 */
@Data
@TableName(value = "linq_collect")
public class LinqCollect implements Serializable {
    /**
     * 收藏id
     */
    @TableId(value = "collection_id", type = IdType.AUTO)
    private Long collectionId;

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
     * 收藏时间
     */
    @TableField(value = "collect_time")
    private Date collectTime;

    private static final long serialVersionUID = 1L;
}