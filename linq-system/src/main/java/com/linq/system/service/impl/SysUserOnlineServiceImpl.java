package com.linq.system.service.impl;

import com.linq.common.core.domain.LoginUser;
import com.linq.common.core.domain.entity.SysDept;
import com.linq.common.utils.spring.SpringUtils;
import com.linq.common.utils.string.StringUtils;
import com.linq.system.domain.SysUserOnline;
import com.linq.system.service.SysDeptService;
import com.linq.system.service.SysUserOnlineService;
import org.springframework.stereotype.Service;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 7:53 下午
 * @Description:
 * @Version: 1.0.0
 */
@Service
public class SysUserOnlineServiceImpl implements SysUserOnlineService {

    /**
     * 通过登录地址查询信息
     *
     * @param ipaddr 登录地址
     * @param user   用户信息
     *
     * @return 在线用户信息
     */
    @Override
    public SysUserOnline findOnlineByIpaddr(String ipaddr, LoginUser user) {
        if (StringUtils.equals(ipaddr, user.getIpaddr())) {
            return setLoginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * 通过用户名称查询信息
     *
     * @param userName 用户名称
     * @param user     用户信息
     *
     * @return 在线用户信息
     */
    @Override
    public SysUserOnline findOnlineByUserName(String userName, LoginUser user) {
        if (StringUtils.equals(userName, user.getUsername())) {
            return setLoginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * 通过登录地址/用户名称查询信息
     *
     * @param ipaddr   登录地址
     * @param userName 用户名称
     * @param user     用户信息
     *
     * @return 在线用户信息
     */
    @Override
    public SysUserOnline findOnlineByInfo(String ipaddr, String userName, LoginUser user) {
        if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername())) {
            return setLoginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * 设置在线用户信息
     *
     * @param user 用户信息
     *
     * @return 在线用户
     */
    @Override
    public SysUserOnline setLoginUserToUserOnline(LoginUser user) {
        if (StringUtils.isNull(user) && StringUtils.isNull(user.getUser())) {
            return null;
        }
        SysUserOnline sysUserOnline = new SysUserOnline();
        sysUserOnline
                .setTokenId(user.getToken())
                .setUsername(user.getUsername())
                .setIpaddr(user.getIpaddr())
                .setLoginLocation(user.getLoginLocation())
                .setBrowser(user.getBrowser())
                .setOs(user.getOs())
                .setLoginTime(user.getLoginTime());
        if (StringUtils.isNotNull(user.getUser().getDeptId())) {
            SysDept dept = SpringUtils.getBean(SysDeptService.class).findDeptById(user.getUser().getDeptId());
            sysUserOnline.setDeptName(dept.getDeptName());
        }
        return sysUserOnline;
    }

}
