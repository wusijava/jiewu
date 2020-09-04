package com.linq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.domain.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     *
     * @return 角色列表
     */
    List<SysRole> findRolePermsByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     *
     * @return 选中角色ID列表
     */
    List<Long> findRoleListByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询角色
     *
     * @param username 用户名
     *
     * @return 角色列表
     */
    List<SysRole> findRolesByUsername(@Param("username") String username);

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     *
     * @return 角色数据集合信息
     */
    IPage<SysRole> findPage(Page<SysRole> sysRolePage, @Param("role") SysRole role);


}