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
    * 用户和角色关联表
    */
@Data
@TableName(value = "sys_user_role")
public class SysUserRole implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    private static final long serialVersionUID = 1L;
}