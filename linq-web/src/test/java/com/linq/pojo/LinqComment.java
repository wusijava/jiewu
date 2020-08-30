package com.linq.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 4:39 下午
 * @Description: 新闻评论
 * @Version: 1.0.0
 */
@Data
public class LinqComment {
    private Long commentId;// 新闻评论id
    private Long newsId; // 新闻id
    private String commentContent;// 新闻评论内容
    private Long thumbs; // 点赞数

    private String delFlag;// 删除标志（0代表存在 2代表删除）

    private String createBy;// 创建者
    private Date createTime;// 创建时间
    private String updateBy;//更新者
    private Date updateTime;// 更新时间
    private String remark;// 备注
}
