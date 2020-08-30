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
 * @Date: 2020/8/28 8:11 下午
 * @Description:
 * @Version: 1.0.0
 */

/**
 * 新闻类别表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "linq_news_type")
public class LinqNewsType extends BaseEntity implements Serializable {
    /**
     * 新闻类别id
     */
    @TableId(value = "news_type_id", type = IdType.AUTO)
    private Long newsTypeId;

    /**
     * 新闻类别名称
     */
    @NotBlank(message = "新闻类别名称不能为空")
    @Size(min = 0, max = 30, message = "新闻类别名称长度不能超过30个字符")
    @TableField(value = "news_type_name")
    private String newsTypeName;

    /**
     * 新闻类别状态（0正常 1关闭）
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    @TableField(value = "del_flag")
    private String delFlag;
}