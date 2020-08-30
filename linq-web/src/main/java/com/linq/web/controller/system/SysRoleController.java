package com.linq.web.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.controller.BaseController;
import com.linq.common.core.domain.entity.SysRole;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/26 9:06 下午
 * @Description: 角色信息
 * @Version: 1.0.0
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService roleService;

    @ApiOperation(value = "条件分页获取角色列表", notes = "条件分页获取角色列表详情")
    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<SysRole>> list(@PathVariable("page") int page, @PathVariable("size") int size, SysRole role) {
        System.err.println("SysUser查询条件->" + role);
        IPage<SysRole> iPage = roleService.findPage(new Page<SysRole>(page, size), role);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 根据角色编号获取详细信息
     */
    @ApiOperation(value = "根据角色编号获取详细信息", notes = "根据角色编号获取详细信息详情")
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public Result<SysRole> getInfo(@PathVariable("roleId") Long roleId) {
        return ResultUtils.success(roleService.findRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @ApiOperation(value = "新增角色", notes = "新增角色详情")
    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@Validated @RequestBody SysRole role) {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return ResultUtils.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return ResultUtils.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        // role.setCreateBy(SecurityUtils.getUsername());
        return toResult(roleService.insertRole(role));

    }

    /**
     * 修改保存角色
     */
    @ApiOperation(value = "修改保存角色", notes = "修改保存角色详情")
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@Validated @RequestBody SysRole role) {
        // 不允许操作超级管理员角色
        roleService.checkRoleAllowed(role);
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return ResultUtils.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return ResultUtils.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        // role.setUpdateBy(SecurityUtils.getUsername());
        return toResult(roleService.updateRole(role));
    }

    /**
     * 修改保存数据权限
     */
    @ApiOperation(value = "修改保存数据权限", notes = "修改保存数据权限详情")
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/data/scope")
    public Result<String> dataScope(@RequestBody SysRole role) {
        // 不允许操作超级管理员角色
        roleService.checkRoleAllowed(role);
        return toResult(roleService.authDataScope(role));
    }

    /**
     * 状态修改
     */
    @ApiOperation(value = "状态修改", notes = "状态修改详情")
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/change/status")
    public Result<String> changeStatus(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        // role.setUpdateBy(SecurityUtils.getUsername());
        return toResult(roleService.updateRoleStatus(role));
    }

    /**
     * 删除角色
     */
    @ApiOperation(value = "删除角色", notes = "删除角色详情")
    @PreAuthorize("@ss.hasPermi('system:role:remove')")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{roleIds}")
    public Result<String> remove(@PathVariable("roleIds") List<Long> roleIds) {
        return toResult(roleService.deleteRoleByIds(roleIds));
    }

}
