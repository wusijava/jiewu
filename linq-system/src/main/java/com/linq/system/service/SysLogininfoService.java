package com.linq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.system.domain.SysLogininfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 8:43 上午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysLogininfoService extends IService<SysLogininfo> {
    /**
     * 查询系统登录日志集合
     *
     * @param logininfo 访问日志对象
     *
     * @return 登录记录集合
     */
    IPage<SysLogininfo> findPage(Page<SysLogininfo> sysLogininforPage, SysLogininfo logininfo);

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     *
     * @return
     */
    boolean deleteLogininforByIds(List<Long> infoIds);

    /**
     * 清空系统登录日志
     */
    void cleanLogininfor();
}
