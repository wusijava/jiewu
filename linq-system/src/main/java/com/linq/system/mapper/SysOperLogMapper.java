package com.linq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.system.domain.SysOperLog;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     *
     * @return 操作日志集合
     */
    IPage<SysOperLog> findPage(Page<SysOperLog> sysOperLogPage, @Param("operLog") SysOperLog operLog);

    /**
     * 清空操作日志
     */
    void cleanOperLog();

}