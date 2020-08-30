package com.linq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.domain.entity.SysDictData;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 9:06 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysDictDataMapper extends BaseMapper<SysDictData> {
    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     *
     * @return 字典数据集合信息
     */
    IPage<SysDictData> findPage(Page<SysDictData> sysDictDataPage, @Param("dictData") SysDictData dictData);

    /**
     * 同步修改字典类型
     *
     * @param oldDictType 旧字典类型
     * @param newDictType 新旧字典类型
     *
     * @return 结果
     */
    void updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);
}