package com.linq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.domain.entity.SysDept;
import com.linq.common.core.domain.entity.SysDictData;
import com.linq.common.core.domain.entity.SysDictType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 9:06 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysDictTypeService extends IService<SysDictType> {
    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     *
     * @return 字典数据集合信息
     */
    List<SysDictData> findDictDataByType(String dictType);

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     *
     * @return 字典类型集合信息
     */
    IPage<SysDictType> findPage(Page<SysDictType> sysDictTypePage, SysDictType dictType);

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     *
     * @return 字典类型
     */
    SysDictType findDictTypeById(Long dictId);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     *
     * @return 结果
     */
    String checkDictTypeUnique(SysDictType dictType);

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     *
     * @return 结果
     */
    boolean insertDictType(SysDictType dictType);

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     *
     * @return 结果
     */
    boolean updateDictType(SysDictType dictType);

    /**
     * 批量删除字典信息
     *
     * @param dictIds 需要删除的字典ID
     *
     * @return 结果
     */
    boolean deleteDictTypeByIds(List<Long> dictIds);

    /**
     * 清空缓存数据
     */
    void clearCache();

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    List<SysDictType> findDictTypeAll();

}
