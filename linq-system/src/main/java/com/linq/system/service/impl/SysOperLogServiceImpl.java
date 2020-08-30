package com.linq.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.system.mapper.SysOperLogMapper;
import com.linq.system.domain.SysOperLog;
import com.linq.system.service.SysOperLogService;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {
    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     *
     * @return 操作日志集合
     */
    @Override
    public IPage<SysOperLog> findPage(Page<SysOperLog> sysOperLogPage, SysOperLog operLog) {
        return baseMapper.findPage(sysOperLogPage, operLog);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     *
     * @return 结果
     */
    @Override
    public boolean deleteOperLogByIds(List<Long> operIds) {
        return removeByIds(operIds);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        baseMapper.cleanOperLog();
    }
}
