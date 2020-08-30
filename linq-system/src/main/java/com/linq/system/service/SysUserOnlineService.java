package com.linq.system.service;

import com.linq.common.core.domain.LoginUser;
import com.linq.system.domain.SysUserOnline;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 7:53 下午
 * @Description:
 * @Version: 1.0.0
 */
public interface SysUserOnlineService {

    /**
     * 通过登录地址查询信息
     *
     * @param ipaddr 登录地址
     * @param user 用户信息
     * @return 在线用户信息
     */
    public SysUserOnline findOnlineByIpaddr(String ipaddr, LoginUser user);


    /**
     * 通过用户名称查询信息
     *
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    public SysUserOnline findOnlineByUserName(String userName, LoginUser user);

    /**
     * 通过登录地址/用户名称查询信息
     *
     * @param ipaddr 登录地址
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线用户信息
     */
    public SysUserOnline findOnlineByInfo(String ipaddr, String userName, LoginUser user);

    /**
     * 设置在线用户信息
     *
     * @param user 用户信息
     * @return 在线用户
     */
    public SysUserOnline setLoginUserToUserOnline(LoginUser user);
}
