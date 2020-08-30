package com.linq.news.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linq.common.core.domain.model.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description: 
 * @Version: 1.0.0 
 */
    
/**
    * 友情链接表
    */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "linq_link")
public class LinqLink extends BaseEntity implements Serializable {
    /**
     * 友情链接id
     */
    @TableId(value = "linq_id", type = IdType.AUTO)
    private Long linqId;

    /**
     * 友情链接名称
     */
    @NotBlank(message = "友情链接名称不能为空")
    @TableField(value = "link_name")
    private String linkName;

    /**
     * 友情链接地址
     */
    @NotBlank(message = "友情链接地址不能为空")
    @Size(min = 0, max = 30, message = "友情链接地址长度不能超过30个字符")
    @TableField(value = "link_url")
    private String linkUrl;

    /**
     * 联系人邮件
     */
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    @TableField(value = "email")
    private String email;

    /**
     * 链接显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;
}