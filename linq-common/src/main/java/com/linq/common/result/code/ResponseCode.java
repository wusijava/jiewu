package com.linq.common.result.code;


import lombok.AllArgsConstructor;

/**
 * @Author: 林义清
 * @Date: 2020/8/22 2:02 下午
 * @Description: 状态枚举类
 * @Version: 1.0.0
 */
@AllArgsConstructor
public enum ResponseCode implements ResponseCodeInterface {
    /**
     * 成功             OK                 200
     * 失败             ERROR              500
     */
    OK(200, "操作成功"),
    ERROR(500, "操作失败"),
    ;
    private final int code;

    private final String msg;


    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public int getCode() {
        return code;
    }
}
