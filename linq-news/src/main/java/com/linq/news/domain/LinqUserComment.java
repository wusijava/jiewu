package com.linq.news.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description: 
 * @Version: 1.0.0 
 */
    
/**
    * 用户评论中间表
    */
@Data
@TableName(value = "linq_user_comment")
public class LinqUserComment implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 评论id
     */
    @TableField(value = "comment_id")
    private Long commentId;

    private static final long serialVersionUID = 1L;
}