package com.linq.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.linq.common.core.domain.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 9:06 下午
 * @Description: 
 * @Version: 1.0.0 
 */
    
/**
    * 字典类型表
    */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "sys_dict_type")
public class SysDictType extends BaseEntity implements Serializable {
    /**
     * 字典主键
     */
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
    @TableField(value = "dict_name")
    private String dictName;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
    @TableField(value = "dict_type")
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private String status;
}