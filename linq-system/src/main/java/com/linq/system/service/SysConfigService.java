package com.linq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.domain.entity.SysDictData;
import com.linq.system.domain.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 9:06 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysConfigService extends IService<SysConfig> {
    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     *
     * @return 参数配置集合
     */
    IPage<SysConfig> findPage(Page<SysConfig> sysConfigPage, SysConfig config);

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     *
     * @return 参数配置信息
     */
    SysConfig findConfigById(Integer configId);


    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     *
     * @return 参数键值
     */
    String findConfigByKey(String configKey);

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     *
     * @return 结果
     */
    boolean insertConfig(SysConfig config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     *
     * @return 结果
     */
    boolean updateConfig(SysConfig config);

    /**
     * 批量删除参数信息
     *
     * @param configIds 需要删除的参数ID
     *
     * @return 结果
     */
    boolean deleteConfigByIds(List<Integer> configIds);

    /**
     * 清空缓存数据
     */
    void clearCache();

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数信息
     *
     * @return 结果
     */
    String checkConfigKeyUnique(SysConfig config);
}
