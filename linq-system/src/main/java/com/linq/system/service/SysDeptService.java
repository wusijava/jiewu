package com.linq.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linq.common.core.domain.TreeSelect;
import com.linq.common.core.domain.entity.SysDept;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysDeptService extends IService<SysDept> {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     *
     * @return 部门信息集合
     */
    List<SysDept> findDeptList(SysDept dept);

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     *
     * @return 树结构列表
     */
    List<SysDept> buildDeptTree(List<SysDept> depts);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     *
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     *
     * @return 部门信息
     */
    SysDept findDeptById(Long deptId);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     *
     * @return 选中部门列表
     */
    List<Long> findDeptListByRoleId(Long roleId);

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     *
     * @return 结果
     */
    String checkDeptNameUnique(SysDept dept);

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     *
     * @return 结果
     */
    boolean insertDept(SysDept dept);


    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     *
     * @return 子部门数
     */
    boolean findNormalChildrenDeptById(Long deptId);

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     *
     * @return 结果
     */
    boolean updateDept(SysDept dept);

    /**
     * 是否存在部门子节点
     *
     * @param deptId 部门ID
     *
     * @return 结果
     */
    boolean hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     *
     * @return 结果 true 存在 false 不存在
     */
    boolean checkDeptExistUser(Long deptId);

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     *
     * @return 结果
     */
    boolean deleteDeptById(Long deptId);
}

