package com.linq.common.result;

import com.linq.common.result.code.ResponseCode;
import com.linq.common.result.code.ResponseCodeInterface;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 林义清
 * @Date: 2020/8/22 11:38 上午
 * @Description: 响应实体Bean
 * @Version: 1.0.0
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 是否成功
     */
    private boolean flag;
    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public Result(boolean flag, int code, Object data) {
        this.flag = flag;
        this.code = code;
        this.data = (T) data;
        this.msg = null;
    }


    public Result(boolean flag, int code, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result(boolean flag, int code, String msg, Object data) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.data = (T) data;
    }

    public Result() {
        this.flag = true;
        this.code = ResponseCode.OK.getCode();
        this.msg = ResponseCode.OK.getMsg();
        this.data = null;
    }

    public Result(Object data) {
        this.flag = true;
        this.data = (T)data;
        this.code = ResponseCode.OK.getCode();
        this.msg = ResponseCode.OK.getMsg();
    }

    public Result(String msg) {
        this.flag = true;
        this.code = ResponseCode.OK.getCode();
        this.msg = msg;
        this.data = null;
    }

    public Result(int code, String msg, Object data) {
        this.flag = true;
        this.code = code;
        this.msg = msg;
        this.data = (T)data;
    }

    public Result(int code, String msg) {
        this.flag = true;
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result(boolean flag, ResponseCodeInterface responseCodeInterface) {
        this.flag = flag;
        this.data = null;
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
    }

    public Result(boolean flag, ResponseCodeInterface responseCodeInterface, Object data) {
        this.flag = flag;
        this.data = (T) data;
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
    }
}
