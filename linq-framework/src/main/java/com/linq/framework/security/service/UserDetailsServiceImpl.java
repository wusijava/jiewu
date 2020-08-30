package com.linq.framework.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.linq.common.exception.BaseException;
import com.linq.common.lang.enums.UserStatus;
import com.linq.common.utils.string.StringUtils;
import com.linq.common.core.domain.LoginUser;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 8:56 上午
 * @Description: 用户验证处理: 自定义CustomerUserDetailsService实现UserDetailService, 并实现loadUserByUsername方法 ，
 * loadUserByUsername方法主要作用是查询用户是否存在和设置用户权限信息
 * @Version: 1.0.0
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.查询当前登录用户 判断是否存在
        //      如果存在就判断当前用户是否被删除
        //      如果存在并且没有被删除就判断当前用户是否被禁用
        SysUser user = userService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
        return createLoginUser(user);
    }

    /**
     * 创建UserDetails
     *
     * @param user 登录用户
     *
     * @return UserDetails
     */
    public UserDetails createLoginUser(SysUser user) {
        return new LoginUser(permissionService.getMenuPermission(user), user);
    }
}
