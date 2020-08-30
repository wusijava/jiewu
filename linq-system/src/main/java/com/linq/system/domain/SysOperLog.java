package com.linq.system.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

/**
 * 操作日志记录
 */
@Data
@TableName(value = "sys_oper_log")
public class SysOperLog implements Serializable {
    /**
     * 日志主键
     */
    @TableId(value = "oper_id", type = IdType.AUTO)
    private Long operId;

    /**
     * 模块标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField(value = "business_type")
    private Integer businessType;

    /**
     * 方法名称
     */
    @TableField(value = "`method`")
    private String method;

    /**
     * 请求方式
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField(value = "operator_type")
    private Integer operatorType;

    /**
     * 操作人员
     */
    @TableField(value = "oper_name")
    private String operName;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 请求URL
     */
    @TableField(value = "oper_url")
    private String operUrl;

    /**
     * 主机地址
     */
    @TableField(value = "oper_ip")
    private String operIp;

    /**
     * 操作地点
     */
    @TableField(value = "oper_location")
    private String operLocation;

    /**
     * 请求参数
     */
    @TableField(value = "oper_param")
    private String operParam;

    /**
     * 返回参数
     */
    @TableField(value = "json_result")
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 错误消息
     */
    @TableField(value = "error_msg")
    private String errorMsg;

    /**
     * 操作时间
     */
    @TableField(value = "oper_time")
    private Date operTime;

    /////////////////////////////////// 附加  ///////////////////////////////////

    /**
     * 业务类型数组
     */
    @TableField(exist = false)
    private List<Long> businessTypes;
    /**
     * 开始时间
     */
    @TableField(exist = false)
    @JsonIgnore
    private String beginTime;

    /**
     * 结束时间
     */
    @TableField(exist = false)
    @JsonIgnore
    private String endTime;

    /////////////////////////////////// 附加  ///////////////////////////////////

    private static final long serialVersionUID = 1L;
}