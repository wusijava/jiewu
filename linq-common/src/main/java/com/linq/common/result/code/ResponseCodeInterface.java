package com.linq.common.result.code;

/**
 * @Author: 林义清
 * @Date: 2020/8/22 2:00 下午
 * @Description: code接口
 * @Version: 1.0.0
 */
public interface ResponseCodeInterface {


    /**
     * 获取 响应消息
     *
     * @return String
     */
    String getMsg();

    /**
     * 获取响应码
     *
     * @return int
     */
    int getCode();
}
