package com.linq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.domain.entity.SysDictType;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 9:06 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     *
     * @return 字典类型集合信息
     */
    IPage<SysDictType> findPage(Page<SysDictType> sysDictTypePage, @Param("dictType") SysDictType dictType);
}