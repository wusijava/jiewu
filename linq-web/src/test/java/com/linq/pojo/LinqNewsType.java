package com.linq.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 4:14 下午
 * @Description: 新闻类别
 * @Version: 1.0.0
 */
@Data
public class LinqNewsType {
    private Long newsTypeId;// 新闻类别id
    private String newsTypeName;// 新闻类别名称

    private String status; // 新闻类别状态（0正常 1关闭）

    private String delFlag;// 删除标志（0代表存在 2代表删除）

    private String createBy;//创建者
    private Date createTime;// 创建时间
    private String updateBy;//更新者
    private Date updateTime;// 更新时间
    private String remark;// 备注
}
