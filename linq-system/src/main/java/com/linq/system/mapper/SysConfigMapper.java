package com.linq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.system.domain.SysConfig;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 9:06 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysConfigMapper extends BaseMapper<SysConfig> {
    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     *
     * @return 参数配置集合
     */
    IPage<SysConfig> findPage(Page<SysConfig> sysConfigPage, @Param("config") SysConfig config);
}