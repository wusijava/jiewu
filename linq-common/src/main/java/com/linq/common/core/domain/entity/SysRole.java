package com.linq.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.List;

import com.linq.common.core.domain.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

/**
 * 角色信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class SysRole extends BaseEntity implements Serializable {
    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @NotBlank(message = "权限字符不能为空")
    @Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
    @TableField(value = "role_key")
    private String roleKey;

    /**
     * 显示顺序
     */
    @TableField(value = "role_sort")
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @TableField(value = "data_scope")
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
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

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    @TableField(exist = false)
    private boolean flag = false;

    /**
     * 菜单组
     */
    @TableField(exist = false)
    private List<Long> menuIds;

    /**
     * 部门组（数据权限）
     */
    @TableField(exist = false)
    private List<Long> deptIds;

    public boolean isAdmin() {
        return isAdmin(this.roleId);
    }

    // 1 是系统管理员
    public static boolean isAdmin(Long roleId) {
        return roleId != null && 1L == roleId;
    }

    public SysRole(Long roleId) {
        this.roleId = roleId;
    }

    /////////////////////////////////// 附加  ///////////////////////////////////
    private static final long serialVersionUID = 1L;
}