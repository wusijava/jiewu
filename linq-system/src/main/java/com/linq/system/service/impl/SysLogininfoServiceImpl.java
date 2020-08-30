package com.linq.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.system.mapper.SysLogininfoMapper;
import com.linq.system.domain.SysLogininfo;
import com.linq.system.service.SysLogininfoService;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 8:43 上午
 * @Description:
 * @Version: 1.0.0
 */

@Service
public class SysLogininfoServiceImpl extends ServiceImpl<SysLogininfoMapper, SysLogininfo> implements SysLogininfoService {
    /**
     * 查询系统登录日志集合
     *
     * @param logininfo 访问日志对象
     *
     * @return 登录记录集合
     */
    @Override
    public IPage<SysLogininfo> findPage(Page<SysLogininfo> sysLogininforPage, SysLogininfo logininfo) {
        return baseMapper.findPage(sysLogininforPage, logininfo);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     *
     * @return
     */
    @Override
    public boolean deleteLogininforByIds(List<Long> infoIds) {
        return removeByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor() {
        baseMapper.cleanLogininfor();
    }
}
