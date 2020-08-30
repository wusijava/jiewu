package com.linq.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 10:37 上午
 * @Description: 
 * @Version: 1.0.0 
 */
    
/**
    * 角色和部门关联表
    */
@Data
@TableName(value = "sys_role_dept")
public class SysRoleDept  implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 部门ID
     */
    @TableField(value = "dept_id")
    private Long deptId;

    private static final long serialVersionUID = 1L;
}