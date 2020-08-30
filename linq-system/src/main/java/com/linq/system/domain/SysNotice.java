package com.linq.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linq.common.core.domain.model.BaseEntity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 11:23 上午
 * @Description:
 * @Version: 1.0.0
 */

/**
 * 通知公告表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_notice")
public class SysNotice extends BaseEntity implements Serializable {
    /**
     * 公告ID
     */
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    /**
     * 公告标题
     */
    @NotBlank(message = "公告标题不能为空")
    @TableField(value = "notice_title")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @TableField(value = "notice_type")
    private String noticeType;

    /**
     * 公告内容
     */
    @NotBlank(message = "公告内容不能为空")
    @TableField(value = "notice_content")
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    @TableField(value = "`status`")
    private String status;

    private static final long serialVersionUID = 1L;
}