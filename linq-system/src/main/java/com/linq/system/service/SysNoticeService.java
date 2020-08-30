package com.linq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.system.domain.SysNotice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 11:23 上午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysNoticeService extends IService<SysNotice> {

    /**
     * 获取公告列表
     *
     * @param page   分页参数
     * @param notice 条件查询对象，notice
     *
     * @return IPage<SysUser>
     */
    IPage<SysNotice> findPage(Page<SysNotice> page, SysNotice notice);


    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     *
     * @return 公告信息
     */
    SysNotice findNoticeById(Integer noticeId);


    /**
     * 新增公告
     *
     * @param notice 公告信息
     *
     * @return 结果
     */
    boolean insertNotice(SysNotice notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     *
     * @return 结果
     */
    boolean updateNotice(SysNotice notice);

    /**
     * 删除公告对象
     *
     * @param noticeId 公告ID
     *
     * @return 结果
     */
    boolean deleteNoticeById(Integer noticeId);

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     *
     * @return 结果
     */
    boolean deleteNoticeByIds(List<Integer> noticeIds);
}
