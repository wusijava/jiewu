package com.linq.web.controller.system;

import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.ServletUtils;
import com.linq.common.utils.spring.SpringUtils;
import com.linq.framework.security.service.SysLoginService;
import com.linq.framework.security.service.SysPermissionService;
import com.linq.framework.security.service.TokenService;
import com.linq.common.core.domain.LoginUser;
import com.linq.common.core.domain.entity.SysMenu;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.system.service.SysMenuService;
import com.linq.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.linq.common.core.domain.LoginBody;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 10:51 上午
 * @Description: 登录验证
 * @Version: 1.0.0
 */
@Api(tags = "登录验证管理")
@RestController
public class
SysLoginController {
    @Autowired
    private SysLoginService loginService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private SysMenuService menuService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     *
     * @return 结果
     */
    @ApiOperation(value = "用户认证登录", notes = "用户认证登录详情")
    @PostMapping("/login")
    public Result<HashMap<String, Object>> login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        return ResultUtils.success(new HashMap<String, Object>() {{
            put("token", loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                                            loginBody.getUuid()
            ));
        }});
    }

    /**
     * 获取登录用户信息
     *
     * @return 结果
     */
    @ApiOperation(value = "获取登录用户信息", notes = "获取登录用户信息详情")
    @GetMapping("/get/info")
    public Result<HashMap<String, Object>> getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 存一下最后登录ip
        user.setLoginIp(loginUser.getIpaddr());
        SpringUtils.getBean(SysUserService.class).saveOrUpdate(user);
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 菜单权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        return ResultUtils.success(new HashMap<String, Object>() {{
            put("user", user);
            put("roles", roles);
            put("permissions", permissions);
        }});
    }

    /**
     * 获取登录用户路由信息
     *
     * @return 结果
     */
    @ApiOperation(value = "获取登录用户路由信息", notes = "获取登录用户路由信息详情")
    @GetMapping("/get/routers")
    public Result<HashMap<String, Object>> getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        // 根据用户id查询拥有菜单列表
        List<SysMenu> menus = menuService.findMenuTreeByUserId(user.getUserId());
        return ResultUtils.success(menuService.buildMenus(menus));
    }
}
