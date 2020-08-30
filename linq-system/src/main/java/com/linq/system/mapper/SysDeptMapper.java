package com.linq.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linq.common.core.domain.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 7:26 下午
 * @Description:
 * @Version: 1.0.0
 */

public interface SysDeptMapper extends BaseMapper<SysDept> {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     *
     * @return 部门信息集合
     */
    List<SysDept> findDeptList(@Param("dept") SysDept dept);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     *
     * @return 选中部门列表
     */
    List<Long> findDeptListByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     *
     * @return 子部门数
     */
    int findNormalChildrenDeptById(@Param("deptId") Long deptId);

    /**
     * 根据ID查询所有子部门
     *
     * @param deptId 部门ID
     *
     * @return 部门列表
     */
    List<SysDept> findChildrenDeptById(@Param("deptId") Long deptId);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     *
     * @return 结果
     */
    int updateDeptChildren(@Param("depts") List<SysDept> children);

    /**
     * 修改所在部门的父级部门状态
     *
     * @param dept 部门
     */
    void updateDeptStatus(@Param("dept") SysDept dept);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     *
     * @return 结果
     */
    int checkDeptExistUser(@Param("deptId") Long deptId);
}