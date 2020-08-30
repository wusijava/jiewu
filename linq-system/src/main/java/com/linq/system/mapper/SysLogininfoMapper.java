package com.linq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.system.domain.SysLogininfo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 8:43 上午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysLogininfoMapper extends BaseMapper<SysLogininfo> {
    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     *
     * @return 登录记录集合
     */
    IPage<SysLogininfo> findPage(Page<SysLogininfo> sysLogininforPage, @Param("logininfo") SysLogininfo logininfo);

    /**
     * 清空系统登录日志
     *
     * @return 结果
     */
    void cleanLogininfor();

}