package com.linq.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.domain.entity.SysDictType;
import com.linq.common.utils.DictUtils;
import com.linq.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.common.core.domain.entity.SysDictData;
import com.linq.system.mapper.SysDictDataMapper;
import com.linq.system.service.SysDictDataService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 9:06 下午
 * @Description:
 * @Version: 1.0.0
 */

@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {


    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     *
     * @return 字典数据集合信息
     */
    @Override
    public IPage<SysDictData> findPage(Page<SysDictData> sysDictDataPage, SysDictData dictData) {
        return baseMapper.findPage(sysDictDataPage, dictData);
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     *
     * @return 字典数据
     */
    @Override
    public SysDictData findDictDataById(Long dictCode) {
        return getById(dictCode);
    }

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     *
     * @return 结果
     */
    @Override
    public boolean insertDictData(SysDictData dictData) {
        boolean flag = saveOrUpdate(dictData);
        if (flag) {
            // 清空缓存
            DictUtils.clearDictCache();
        }
        return flag;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     *
     * @return 结果
     */

    @Override
    public boolean updateDictData(SysDictData dictData) {
        boolean flag = saveOrUpdate(dictData);
        if (flag) {
            // 清空缓存
            DictUtils.clearDictCache();
        }
        return flag;
    }

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     *
     * @return 结果
     */
    @Override
    public boolean deleteDictDataByIds(List<Long> dictCodes) {
        boolean flag = removeByIds(dictCodes);
        if (flag) {
            // 清空缓存
            DictUtils.clearDictCache();
        }
        return flag;
    }
}
