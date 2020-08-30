package com.linq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.system.domain.SysOperLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysOperLogService extends IService<SysOperLog> {

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     *
     * @return 操作日志集合
     */
    IPage<SysOperLog> findPage(Page<SysOperLog> sysOperLogPage, SysOperLog operLog);

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     *
     * @return 结果
     */
    boolean deleteOperLogByIds(List<Long> operIds);

    /**
     * 清空操作日志
     */
    void cleanOperLog();
}
