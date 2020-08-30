package com.linq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 只要将非 limit 的sql 语句写在 对应的 id="selectPage"里面（SysRoleMapper.xml），不需要自动去分页，而mybaits-plus会自动实现分页
     * 但是，它实现分页，你需要第一个参数会传入Page，而其他参数你需要使用@Param(xx)要取别名
     * 最终查询到的数据会被封装到IPage实现里面
     */

    /**
     * 获取用户列表
     *
     * @param page 分页参数
     * @param user 条件查询对象，user
     *
     * @return IPage<SysUser>
     */
    IPage<SysUser> findPage(Page<SysUser> page, @Param("user") SysUser user);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     *
     * @return 用户对象信息
     */
    SysUser findByUserId(@Param("userId") Long userId);

}