package com.linq.common.core.controller;

import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.string.StringUtils;

/**
 * @Author: 林义清
 * @Date: 2020/8/26 10:50 上午
 * @Description: web层通用数据处理
 * @Version: 1.0.0
 */
public class BaseController {
    /**
     * 响应返回结果
     *
     * @param flag 操作成功 true 否则 false
     *
     * @return 操作结果
     */
    protected <T> Result<T> toResult(boolean flag) {
        return flag ? ResultUtils.success() : ResultUtils.error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }
}
