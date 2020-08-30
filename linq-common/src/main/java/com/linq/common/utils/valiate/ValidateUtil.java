package com.linq.common.utils.valiate;

import cn.hutool.core.util.ObjectUtil;
import com.linq.common.exception.BaseException;

/**
 * @Author: 林义清
 * @Date: 2020/8/22 8:46 下午
 * @Description: 验证工具
 * @Version: 1.0.0
 */
public class ValidateUtil {
    /**
     * 验证对象属性是否为空
     *
     * @param obj       实体对象
     * @param entity    实体名称
     * @param parameter 实体字段
     * @param value     字段值
     */
    public static void isNull(Object obj, String entity, String parameter, Object value) {
        if (ObjectUtil.isNull(obj)) {
            String msg = entity + " 不存在: " + parameter + " is " + value;
            throw new BaseException(msg);
        }
    }
}
