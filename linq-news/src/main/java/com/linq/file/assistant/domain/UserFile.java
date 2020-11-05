package com.linq.file.assistant.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linq.common.core.domain.model.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: vue-news
 * @description:
 * @author: Mr.Wang
 * @create: 2020-11-05 17:32
 **/
@Data
@TableName(value = "user_file")
public class UserFile extends BaseEntity implements Serializable {

    @TableId(value = "file_id", type = IdType.AUTO)
    private long fileId;//   bigint(20) not null comment '文件id',

    @NotBlank(message = "上传人不能为空")
    @TableField(value = "user_id")
    private long userId;//   bigint(20) comment '用户ID',

    @NotBlank(message = "文件类型不能为空")
    @TableField(value = "file_type_id")
    private long fileTypeId;//   bigint(20) comment '文件类型id',

    @NotBlank(message = "文件名称不能为空")
    @TableField(value = "file_name")
    private String fileName;//   varchar(255) comment '文件名称',

    @NotBlank(message = "文件链接不能为空")
    @TableField(value = "file_url")
    private String fileUrl;//   varchar(255) comment '文件地址',

    @TableField(value = "file_size")
    private long fileSize;//   varchar(255) comment '文件大小（单位 字节）',

    @TableField(value = "upload_time")
    private Date uploadTime;//   datetime comment '上传时间',

    @TableField(value = "parent_file_id")
    private long parentFileId;//   bigint(20) comment '父文件id',

    @TableField(value = "status")
    private String status;//   char(1) comment '文件状态（0-已读，1-未读 默认为1）',

    @TableField(value = "source")
    private String source;//   char(1) comment '来源（0-PC传向手机，1-手机传向PC）',

    @TableField(exist = false)
    private FileType fileType;

}
