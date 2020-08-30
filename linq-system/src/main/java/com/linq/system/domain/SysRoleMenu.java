package com.linq.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description: 
 * @Version: 1.0.0 
 */
    
/**
    * 角色和菜单关联表
    */
@Data
@TableName(value = "sys_role_menu")
public class SysRoleMenu implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    private Long menuId;

    private static final long serialVersionUID = 1L;
}