package com.linq.web.controller.system;

import com.linq.common.constant.Constants;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.controller.BaseController;
import com.linq.common.core.domain.LoginUser;
import com.linq.common.core.domain.TreeSelect;
import com.linq.common.core.domain.entity.SysMenu;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.ServletUtils;
import com.linq.common.utils.string.StringUtils;
import com.linq.framework.security.service.TokenService;
import com.linq.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/26 9:31 下午
 * @Description: 菜单信息
 * @Version: 1.0.0
 */
@Api(tags = "菜单权限管理")
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService menuService;
    @Autowired
    private TokenService tokenService;

    /**
     * 获取菜单列表
     */
    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表详情")
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @GetMapping("/list")
    public Result< List<SysMenu>> list(SysMenu menu) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getUser().getUserId();
        List<SysMenu> menus = menuService.findMenuList(menu, userId);
        return ResultUtils.success(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @ApiOperation(value = "根据菜单编号获取详细信息", notes = "根据菜单编号获取详细信息详情")
    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public Result<SysMenu> getInfo(@PathVariable("menuId") Long menuId) {
        return ResultUtils.success(menuService.findMenuById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/tree/select")
    public Result< List<TreeSelect> > treeselect(SysMenu menu) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getUser().getUserId();
        List<SysMenu> menus = menuService.findMenuList(menu, userId);
        return ResultUtils.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @ApiOperation(value = "加载对应角色菜单列表树", notes = "加载对应角色菜单列表树详情")
    @GetMapping(value = "/role/menu/tree/select/{roleId}")
    public Result<HashMap<String, Object>> roleMenuTreeSelect(@PathVariable("roleId") Long roleId) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysMenu> menus = menuService.findMenuListByUserId(loginUser.getUser().getUserId());
        return ResultUtils.success(new HashMap<String, Object>() {{
            put("checkedKeys", menuService.findMenuListByRoleId(roleId));
            put("menus", menuService.buildMenuTreeSelect(menus));
        }});
    }

    /**
     * 新增菜单
     */
    @ApiOperation(value = "新增菜单", notes = "新增菜单详情")
    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@Validated @RequestBody SysMenu menu) {
        // 判断菜单名称是否重复
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return ResultUtils.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame().toString()) // 是否菜单外链
                && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            return ResultUtils.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        //  menu.setCreateBy(SecurityUtils.getUsername());
        return toResult(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @ApiOperation(value = "修改菜单", notes = "修改菜单详情")
    @PreAuthorize("@ss.hasPermi('system:menu:edit')")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@Validated @RequestBody SysMenu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return ResultUtils.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame().toString()) // 是否菜单外链
                && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            return ResultUtils.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        // menu.setUpdateBy(SecurityUtils.getUsername());
        return toResult(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @ApiOperation(value = "删除菜单", notes = "删除菜单详情")
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public Result<String> remove(@PathVariable("menuId") Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return ResultUtils.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId)) {
            return ResultUtils.error("菜单已分配,不允许删除");
        }
        return toResult(menuService.deleteMenuById(menuId));
    }
}
