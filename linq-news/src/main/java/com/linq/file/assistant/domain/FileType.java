package com.linq.file.assistant.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linq.common.core.domain.model.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @program: vue-news
 * @description:
 * @author: Mr.Wang
 * @create: 2020-11-05 17:36
 **/


@Data
@TableName(value = "file_type")
public class FileType extends BaseEntity implements Serializable {

    @TableId(value = "file_type_id", type = IdType.AUTO)
    private long fileTypeId;

    @NotBlank(message = "文件名称不能为空")
    @TableField(value = "file_type_name")
    private String fileTypeName;//  varchar(127) comment '文件类型（需参照码表）',

    @TableField(value = "file_type_icon")
    private String fileTypeIcon;//  varchar(127) comment '文件类型图标',

    @NotBlank(message = "文件后缀不能为空")
    @TableField(value = "file_type_suffix")
    private String fileTypeSuffix;//  varchar(127) comment '文件类型后缀',

}
