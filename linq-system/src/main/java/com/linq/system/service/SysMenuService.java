package com.linq.system.service;

import com.linq.common.core.domain.TreeSelect;
import com.linq.common.core.domain.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linq.system.domain.vo.RouterVo;

import java.util.List;
import java.util.Set;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysMenuService extends IService<SysMenu> {
    /**
     * 根据用户ID查询菜单权限
     *
     * @param userId 用户ID
     *
     * @return 菜单权限列表
     */
    Set<String> findMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     *
     * @return 菜单列表
     */
    List<SysMenu> findMenuTreeByUserId(Long userId);

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     *
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<SysMenu> menus);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu   菜单信息
     * @param userId 用户ID
     *
     * @return 菜单列表
     */
    List<SysMenu> findMenuList(SysMenu menu, Long userId);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     *
     * @return 菜单信息
     */
    SysMenu findMenuById(Long menuId);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     *
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     *
     * @return 菜单列表
     */
    List<SysMenu> findMenuListByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     *
     * @return 选中菜单列表
     */
    List<Long> findMenuListByRoleId(Long roleId);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     *
     * @return 结果
     */
    String checkMenuNameUnique(SysMenu menu);

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     *
     * @return 结果
     */
    boolean insertMenu(SysMenu menu);

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     *
     * @return 结果
     */
    boolean updateMenu(SysMenu menu);

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     *
     * @return 结果 true 存在 false 不存在
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     * 查询菜单是否存在角色
     *
     * @param menuId 菜单ID
     *
     * @return 结果 true 存在 false 不存在
     */
    boolean checkMenuExistRole(Long menuId);

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     *
     * @return 结果
     */
    boolean deleteMenuById(Long menuId);
}
