package com.linq.common.config.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.linq.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义填充公共字段 ,即没有传的字段自动填充
 *
 * @Author: 林义清
 * @Date: 2020/4/24 2:07 上午
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class MetaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("正在调用该insert填充字段方法");
        Object createTime = getFieldValByName("createTime", metaObject);
        Object createBy = getFieldValByName("createBy", metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);
        Object updateBy = getFieldValByName("updateBy", metaObject);
        if (null == createTime) {
            setFieldValByName("createTime", new Date(), metaObject);
        }
        if (null == createBy) {
            setFieldValByName("createBy", SecurityUtils.getUsername(), metaObject);
        }
        if (null == updateTime) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
        if (null == updateBy) {
            setFieldValByName("updateBy", SecurityUtils.getUsername(), metaObject);
        }
        log.info("调用该insert填充字段方法结束");
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("正在调用该update填充字段方法");
        setFieldValByName("updateTime", new Date(), metaObject);
        setFieldValByName("updateBy", SecurityUtils.getUsername(), metaObject);
        log.info("调用该update填充字段方法结束");
    }
}
