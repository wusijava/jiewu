package com.linq.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 3:56 下午
 * @Description: 新闻表
 * @Version: 1.0.0
 */
@Data
public class LinqNews {
    private Long newsId; // 新闻id
    private Long newsTypeId; // 新闻类别id
    private Long userId;  // 用户id(作者)
    private String newsTitle; // 新闻标题
    private String newsContent;// 新闻内容
    private String newsAttr; // 新闻属性 1.头条区新闻 2.幻灯片区新闻 3.热点区新闻
    private String newsImage; // 新闻封面

    private Integer orderNum; // 新闻显示顺序
    private String isPublic; // 新闻是否公开（0.公开 1.私有）

    private Long thumbs;// 新闻点赞数
    private Long visits; // 新闻浏览量
    private Long comments;// 新闻评论数
    private Long collects; // 新闻收藏数

    private String status;// 审核状态（0.审核中 1.审核成功 2.审核失败）

    private String createBy;// 创建者
    private Date createTime;// 创建时间
    private String updateBy;//更新者
    private Date updateTime;// 更新时间
    private String remark;// 备注
}
