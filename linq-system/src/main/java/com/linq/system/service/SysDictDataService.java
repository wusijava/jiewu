package com.linq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.domain.entity.SysDictData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 9:06 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysDictDataService extends IService<SysDictData> {
    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     *
     * @return 字典数据集合信息
     */
    IPage<SysDictData> findPage(Page<SysDictData> sysDictDataPage, SysDictData dictData);

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     *
     * @return 字典数据
     */
    SysDictData findDictDataById(Long dictCode);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     *
     * @return 结果
     */
    boolean insertDictData(SysDictData dictData);

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     *
     * @return 结果
     */
    boolean updateDictData(SysDictData dictData);

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     *
     * @return 结果
     */
    boolean deleteDictDataByIds(List<Long> dictCodes);
}
