package com.linq.pojo;

import lombok.Data;
import org.checkerframework.checker.units.qual.C;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 5:00 下午
 * @Description: 用户评论中间表
 * @Version: 1.0.0
 */
@Data
public class LinqUserComment {
    private Long id; // 主键id
    private Long userId;// 用户id
    private Long commentId; // 评论id
}
