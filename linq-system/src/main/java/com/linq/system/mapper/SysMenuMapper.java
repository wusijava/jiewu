package com.linq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linq.common.core.domain.entity.SysMenu;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户ID查询菜单权限
     *
     * @param userId 用户ID
     *
     * @return 菜单权限列表
     */
    List<String> findMenuPermsByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    List<SysMenu> findMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     *
     * @return 菜单列表
     */
    List<SysMenu> findMenuTreeByUserId(@Param("userId") Long userId);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     *
     * @return 菜单列表
     */
    List<SysMenu> findMenuList(@Param("menu") SysMenu menu);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     *
     * @return 菜单列表
     */
    List<SysMenu> findMenuListByUserId(@Param("menu") SysMenu menu);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     *
     * @return 选中菜单列表
     */
    List<Long> findMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     *
     * @return 结果
     */
    SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);
}