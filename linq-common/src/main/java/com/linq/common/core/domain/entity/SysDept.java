package com.linq.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.linq.common.core.domain.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 7:26 下午
 * @Description:
 * @Version: 1.0.0
 */

/**
 * 部门表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dept")
public class SysDept extends BaseEntity implements Serializable {
    /**
     * 部门id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    /**
     * 父部门id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 祖级列表
     */
    @TableField(value = "ancestors")
    private String ancestors;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 负责人
     */
    @TableField(value = "leader")
    private String leader;

    /**
     * 联系电话
     */
    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    @TableField(value = "email")
    private String email;

    /**
     * 部门状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    @TableField(value = "del_flag")
    private String delFlag;

    /////////////////////////////////// 附加  ///////////////////////////////////

    /** 父部门名称 */
    @TableField(exist = false)
    private String parentName;

    /** 子部门 */
    @TableField(exist = false)
    private List<SysDept> children = new ArrayList<SysDept>();


    /////////////////////////////////// 附加  ///////////////////////////////////

}