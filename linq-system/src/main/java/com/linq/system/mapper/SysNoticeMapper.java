package com.linq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.system.domain.SysNotice;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 11:23 上午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysNoticeMapper extends BaseMapper<SysNotice> {
    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     *
     * @return 公告集合
     */
    IPage<SysNotice> findPage(Page<SysNotice> page, @Param("notice") SysNotice notice);
}