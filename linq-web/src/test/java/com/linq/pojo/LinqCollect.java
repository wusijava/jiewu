package com.linq.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 4:41 下午
 * @Description: 新闻收藏
 * @Version: 1.0.0
 */
@Data
public class LinqCollect {
    private Long collectionId;    // 收藏id
    private Long newsId; // 新闻id
    private Long userId; // 用户id
    private Date collectTime;// 收藏时间
}
