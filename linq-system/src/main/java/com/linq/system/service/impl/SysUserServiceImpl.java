package com.linq.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.domain.entity.SysRole;
import com.linq.common.exception.CustomException;
import com.linq.common.utils.string.StringUtils;
import com.linq.common.utils.uuid.IdWorker;
import com.linq.system.domain.SysUserRole;
import com.linq.system.mapper.SysRoleMapper;
import com.linq.system.mapper.SysUserRoleMapper;
import com.linq.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.system.mapper.SysUserMapper;
import com.linq.system.service.SysUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserRoleService userRoleService;
    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 获取用户列表
     *
     * @param page 分页参数
     * @param user 条件查询对象，user
     *
     * @return IPage<SysUser>
     */
    @Override
    public IPage<SysUser> findPage(Page<SysUser> page, SysUser user) {
        return baseMapper.findPage(page, user);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param username 用户名称
     *
     * @return 结果
     */
    @Override
    public String checkUsernameUnique(String username) {
        // 查询该用户使用的人数 如果>0就已经使用过了
        int count = baseMapper.selectCount(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        // 存在:1 不存在:0
        return (count > 0) ? UserConstants.NOT_UNIQUE : UserConstants.UNIQUE;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        // select user_id,phone from sys_user where phone = #{phone}
        SysUser info = baseMapper.selectOne(
                new LambdaQueryWrapper<SysUser>()
                        .select(SysUser::getUserId, SysUser::getPhone)
                        .eq(SysUser::getPhone, user.getPhone()));
        // 存在:1 不存在:0 数据库里面用户id 不和 目前的userId一致
        if (StringUtils.isNotNull(info) && !Objects.equals(info.getUserId(), userId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        // select user_id,phone from sys_user where phone = #{phone}
        SysUser info = baseMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                                                    .select(SysUser::getUserId, SysUser::getEmail)
                                                    .eq(SysUser::getEmail, user.getEmail()));
        // 存在:1 不存在:0  数据库里面用户id 不和 目前的userId一致
        if (StringUtils.isNotNull(info) && !Objects.equals(info.getUserId(), userId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    @Transactional
    @Override
    public boolean insertUser(SysUser user) {
        // 新增用户信息
        boolean flag = saveOrUpdate(user);
        // 新增用户与角色管理
        insertUserRole(user);
        return flag;
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        List<Long> roleIds = user.getRoleIds();
        if (StringUtils.isNotNull(roleIds)) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<>();
            roleIds.forEach(id -> {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(id);
                list.add(ur);
            });
            if (list.size() > 0) {
                // 批量新增用户角色信息
                userRoleService.saveOrUpdateBatch(list);
            }
        }

    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(SysUser user) {
        // 判断是否是管理员 不允许操作超级管理员用户
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin()) {
            throw new CustomException("不允许操作超级管理员用户");
        }
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    @Transactional
    @Override
    public boolean updateUser(SysUser user) {
        Long userId = user.getUserId();
        // 删除用户与角色关联
        userRoleService.remove(
                new LambdaQueryWrapper<SysUserRole>()
                        .eq(SysUserRole::getUserId, userId)
        );
        // 新增用户与角色管理
        insertUserRole(user);
        return saveOrUpdate(user);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     *
     * @return 结果
     */
    @Override
    public boolean deleteUserByIds(List<Long> userIds) {
        // 循环判断 该用户是否是超级管理员 如果不是才可以进行 删除
        userIds.forEach(uid -> checkUserAllowed(new SysUser(uid)));
        // 批量逻辑删除
        return removeByIds(userIds);
    }

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    @Override
    public boolean resetPwd(SysUser user) {
        return saveOrUpdate(user);
    }

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    @Override
    public boolean updateUserStatus(SysUser user) {
        return saveOrUpdate(user);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     *
     * @return 用户对象信息
     */
    @Override
    public SysUser findByUserId(Long userId) {
        return baseMapper.findByUserId(userId);
    }

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param username 用户名
     *
     * @return 结果
     */
    @Override
    public String findUserRoleGroup(String username) {
        List<SysRole> list = roleMapper.findRolesByUsername(username);
        StringBuilder idString = new StringBuilder();
        for (SysRole role : list) { // 拼接角色名称 admin,common,....
            idString.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idString.toString())) {
            return idString.substring(0, idString.length() - 1);
        }
        return idString.toString();
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     *
     * @return 结果
     */
    @Override
    public boolean updateUserProfile(SysUser user) {
        return saveOrUpdate(user);
    }

    /**
     * 重置用户密码
     *
     * @param username        用户名
     * @param encryptPassword 密码
     *
     * @return 结果
     */
    @Override
    public boolean resetUserPwd(String username, String encryptPassword) {
        // 先查询 再更新
        SysUser user = baseMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                                                    .eq(SysUser::getUsername, username)
        );
        user.setPassword(encryptPassword);
        return saveOrUpdate(user);
    }

    /**
     * 修改用户头像
     *
     * @param username 用户名
     * @param avatar   头像地址
     *
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(String username, String avatar) {
        // 先查询 再更新
        SysUser user = baseMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                                                    .eq(SysUser::getUsername, username)
        );
        user.setAvatar(avatar);
        return saveOrUpdate(user);
    }
}
