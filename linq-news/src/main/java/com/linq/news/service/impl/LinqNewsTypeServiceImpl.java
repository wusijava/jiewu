package com.linq.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.constant.UserConstants;
import com.linq.common.exception.CustomException;
import com.linq.common.utils.string.StringUtils;
import com.linq.news.domain.LinqNews;
import com.linq.news.domain.LinqNewsType;
import com.linq.news.mapper.LinqNewsMapper;
import com.linq.news.mapper.LinqNewsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.linq.news.service.LinqNewsTypeService;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:10 下午
 * @Description:
 * @Version: 1.0.0
 */

@Service
public class LinqNewsTypeServiceImpl extends ServiceImpl<LinqNewsTypeMapper, LinqNewsType> implements LinqNewsTypeService {
    @Autowired
    private LinqNewsMapper newsMapper;

    /**
     * 条件分页新闻类型列表
     *
     * @param linqNewsTypePage 分页插件
     * @param newsType         菜单类型
     *
     * @return 结果
     */
    @Override
    public IPage<LinqNewsType> findPage(Page<LinqNewsType> linqNewsTypePage, LinqNewsType newsType) {
        return baseMapper.findPage(linqNewsTypePage, newsType);
    }

    /**
     * 查询新闻类型
     *
     * @param newsTypeId 新闻类型ID
     *
     * @return 新闻类型
     */
    @Override
    public LinqNewsType findNewsTypeById(Long newsTypeId) {
        return getById(newsTypeId);
    }

    /**
     * 新增新闻类型
     *
     * @param newsType 新闻类型
     *
     * @return 结果
     */
    @Override
    public boolean insertNewsType(LinqNewsType newsType) {
        return saveOrUpdate(newsType);
    }

    /**
     * 修改新闻类型
     *
     * @param newsType 新闻类型
     *
     * @return 结果
     */
    @Override
    public boolean updateNewsType(LinqNewsType newsType) {
        return saveOrUpdate(newsType);
    }

    /**
     * 删除新闻类型信息
     *
     * @param newsTypeIds 新闻类型ID
     *
     * @return 结果
     */
    @Override
    public boolean deleteNewsTypeByIds(List<Long> newsTypeIds) {
        // 判断新闻是否正在使用这个 新闻类型
        newsTypeIds.stream().filter(Objects::nonNull)
                .forEach(newsTypeId -> {
                    // 根据id查询当前新闻类型
                    LinqNewsType newsType = getById(newsTypeId);
                    if (countNewsBynewsTypeId(newsTypeId)) {
                        throw new CustomException(String.format("%1$s已分配,不能删除", newsType.getNewsTypeName()));
                    }
                });
        return removeByIds(newsTypeIds);
    }


    /**
     * 通过新闻类型ID查询新闻使用数量
     *
     * @param newsTypeId 新闻类型id
     *
     * @return 结果
     */
    private boolean countNewsBynewsTypeId(Long newsTypeId) {
        return newsMapper.selectCount(new LambdaQueryWrapper<LinqNews>().eq(LinqNews::getNewsTypeId, newsTypeId)) > 0;
    }


    /**
     * 校验新闻类型名称是否唯一
     *
     * @param newsType 新闻类型
     *
     * @return 结果
     */
    @Override
    public String checkNewsTypeNameUnique(LinqNewsType newsType) {
        Long newsTypeId = StringUtils.isNull(newsType.getNewsTypeId()) ? -1L : newsType.getNewsTypeId();
        // 根据新闻类型名查询
        LinqNewsType info = getOne(new LambdaQueryWrapper<LinqNewsType>().eq(LinqNewsType::getNewsTypeName, newsType.getNewsTypeName()));
        if (StringUtils.isNotNull(info) && !Objects.equals(info.getNewsTypeId(), newsTypeId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}

