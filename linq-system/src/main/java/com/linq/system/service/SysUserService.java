package com.linq.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.domain.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysUserService extends IService<SysUser> {
    /**
     * 获取用户列表
     *
     * @param page 分页参数
     * @param user 条件查询对象，user
     *
     * @return IPage<SysUser>
     */
    IPage<SysUser> findPage(Page<SysUser> page, SysUser user);

    /**
     * 校验用户名称是否唯一
     *
     * @param username 用户名称
     *
     * @return 结果
     */
    String checkUsernameUnique(String username);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    String checkEmailUnique(SysUser user);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    boolean insertUser(SysUser user);

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    void checkUserAllowed(SysUser user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    boolean updateUser(SysUser user);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     *
     * @return 结果
     */
    boolean deleteUserByIds(List<Long> userIds);

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    boolean resetPwd(SysUser user);

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    boolean updateUserStatus(SysUser user);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     *
     * @return 用户对象信息
     */
    SysUser findByUserId(Long userId);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param username 用户名
     *
     * @return 结果
     */
    String findUserRoleGroup(String username);

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    boolean updateUserProfile(SysUser user);

    /**
     * 重置用户密码
     *
     * @param username        用户名
     * @param encryptPassword 密码
     *
     * @return 结果
     */
    boolean resetUserPwd(String username, String encryptPassword);

    /**
     * 修改用户头像
     *
     * @param username 用户名
     * @param avatar   头像地址
     *
     * @return 结果
     */
    boolean updateUserAvatar(String username, String avatar);
}
