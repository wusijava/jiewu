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
import com.linq.common.core.domain.entity.SysUser;
import com.linq.common.utils.SecurityUtils;
import com.linq.common.utils.string.StringUtils;
import com.linq.system.service.SysRoleService;
import com.linq.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:17 下午
 * @Description: 用户信息
 * @Version: 1.0.0
 */
@Api(tags = "用户管理接口")
@RequestMapping("/system/user")
@RestController
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;

    /**
     * 条件分页获取用户列表
     *
     * @return 结果集合
     */
    @ApiOperation(value = "条件分页获取用户列表",notes = "条件分页获取用户列表详情")
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<SysUser>> list(@PathVariable("page") int page, @PathVariable("size") int size, SysUser user) {
        System.err.println("SysUser查询条件->" + user);
        IPage<SysUser> iPage = userService.findPage(new Page<SysUser>(page, size), user);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 根据用户编号获取详细信息
     */
    @ApiOperation(value = "根据用户编号获取详细信息",notes = "根据用户编号获取详细信息详情")
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = {"/", "/{userId}"})
    public Result<HashMap<String, Object>> getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        System.err.println("需要修改的用户id->" + userId);
        List<SysRole> roles = roleService.findRoleAll();
        if (StringUtils.isNotNull(userId)) {
            return ResultUtils.success(new HashMap<String, Object>() {
                {
                    put("userInfo", userService.findByUserId(userId));
                    put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
                    put("roleIds", roleService.findRoleListByUserId(userId));
                }
            });
        } else {
            return ResultUtils.success(new HashMap<String, Object>() {
                {
                    put("roles", roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
                }
            });
        }

    }


    /**
     * 新增用户
     */
    @ApiOperation(value = "新增用户",notes = "新增用户详情")
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@Validated @RequestBody SysUser user) {
        System.err.println("用户对象->" + user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUsernameUnique(user.getUsername()))) {
            return ResultUtils.error("新增用户'" + user.getUsername() + "'失败，登录账号已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return ResultUtils.error("新增用户'" + user.getUsername() + "'失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return ResultUtils.error("新增用户'" + user.getUsername() + "'失败，邮箱账号已存在");
        }
        // user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toResult(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @ApiOperation(value = "修改用户",notes = "修改用户详情")
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@Validated @RequestBody SysUser user) {
        System.err.println("用户对象->" + user);
        // 校验用户是否允许操作 不允许操作超级管理员用户
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return ResultUtils.error("修改用户'" + user.getUsername() + "'失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return ResultUtils.error("修改用户'" + user.getUsername() + "'失败，邮箱账号已存在");
        }
        // user.setUpdateBy(SecurityUtils.getUsername());
        return toResult(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户",notes = "删除用户详情")
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public Result<String> remove(@PathVariable("ids") List<Long> userIds) {
        return toResult(userService.deleteUserByIds(userIds));
    }


    /**
     * 重置密码
     */
    @ApiOperation(value = "重置密码",notes = "重置密码详情")
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/reset/pwd")
    public Result<String> resetPwd(@RequestBody SysUser user) {
        System.err.println("用户对象->" + user);
        // 校验用户是否允许操作 不允许操作超级管理员用户
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        // user.setUpdateBy(SecurityUtils.getUsername());
        return toResult(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @ApiOperation(value = "状态修改",notes = "状态修改详情")
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/change/status")
    public Result<String> changeStatus(@RequestBody SysUser user) {
        System.err.println("用户对象->" + user);
        // 校验用户是否允许操作 不允许操作超级管理员用户
        userService.checkUserAllowed(user);
        // user.setUpdateBy(SecurityUtils.getUsername());
        return toResult(userService.updateUserStatus(user));
    }


}
