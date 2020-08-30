package com.linq.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 4:51 下午
 * @Description: 友情链接
 * @Version: 1.0.0
 */
@Data
public class LinqLink {
    private Long linqId; //友情链接id
    private String linkName;// 友情链接名称
    private String linkUrl;// 友情链接地址
    private String email; // 联系人邮件
    private Integer orderNum; // 链接显示顺序

    private String createBy;// 创建者
    private Date createTime;// 创建时间
    private String updateBy;//更新者
    private Date updateTime;// 更新时间
    private String remark;// 备注
}
