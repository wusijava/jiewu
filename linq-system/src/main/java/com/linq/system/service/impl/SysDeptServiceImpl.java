package com.linq.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.domain.TreeSelect;
import com.linq.common.core.domain.entity.SysDept;
import com.linq.common.exception.CustomException;
import com.linq.common.utils.string.StringUtils;
import com.linq.system.mapper.SysDeptMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.system.service.SysDeptService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: 林义清
 * @Date: 2020/8/24 8:08 下午
 * @Description:
 * @Version: 1.0.0
 */

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     *
     * @return 部门信息集合
     */
    @Override
    public List<SysDept> findDeptList(SysDept dept) {
        return baseMapper.findDeptList(dept);
    }


    /**
     * 根据父节点的ID获取所有子节点
     * 递归调用
     *
     * @param list     列表
     * @param parentId 传入的父节点ID
     *
     * @return String
     */
    public List<SysDept> toTree(List<SysDept> list, Long parentId) {
        // 查询所有的pid的子类
        List<SysDept> children = list.stream().filter(item -> item.getParentId().equals(parentId)).collect(Collectors.toList());
        // 查询所有的非pid的子类
        List<SysDept> others = list.stream().filter(item -> !item.getParentId().equals(parentId)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(children)) {
            children.forEach(child -> {
                if (CollectionUtils.isNotEmpty(others)) {
                    toTree(others, child.getDeptId()).forEach(item -> child.getChildren().add(item));
                }
            });
        }
        return children;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     *
     * @return 树结构列表
     */
    @Override
    public List<SysDept> buildDeptTree(List<SysDept> depts) {
        return toTree(depts, 0L);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     *
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts) {
        List<SysDept> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     *
     * @return 部门信息
     */
    @Override
    public SysDept findDeptById(Long deptId) {
        return getById(deptId);
    }

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     *
     * @return 选中部门列表
     */
    @Override
    public List<Long> findDeptListByRoleId(Long roleId) {
        return baseMapper.findDeptListByRoleId(roleId);
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     *
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(SysDept dept) {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        SysDept info = baseMapper.selectOne(new LambdaQueryWrapper<SysDept>().eq(SysDept::getDeptName, dept.getDeptName()).eq(SysDept::getParentId, dept.getParentId()));
        if (StringUtils.isNotNull(info) && !Objects.equals(info.getDeptId(), deptId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     *
     * @return 结果
     */
    @Override
    public boolean insertDept(SysDept dept) {
        SysDept info = findDeptById(dept.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus())) {
            throw new CustomException("部门停用，不允许新增");
        }
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        return saveOrUpdate(dept);
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     *
     * @return 子部门数
     */
    @Override
    public boolean findNormalChildrenDeptById(Long deptId) {
        return baseMapper.findNormalChildrenDeptById(deptId) > 0;
    }

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     *
     * @return 结果
     */
    @Override
    public boolean updateDept(SysDept dept) {
        // 新父节点
        SysDept newParentDept = getById(dept.getParentId());
        SysDept oldDept = getById(dept.getDeptId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept)) {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        boolean flag = saveOrUpdate(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getStatus())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
        return flag;
    }

    /**
     * 是否存在部门子节点
     *
     * @param deptId 部门ID
     *
     * @return 结果
     */
    @Override
    public boolean hasChildByDeptId(Long deptId) {
        return baseMapper.selectCount(new LambdaQueryWrapper<SysDept>()
                                              .eq(SysDept::getDelFlag, UserConstants.DEPT_NORMAL)
                                              .eq(SysDept::getParentId, deptId)
        ) > 0;
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     *
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        return baseMapper.checkDeptExistUser(deptId) > 0;
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     *
     * @return 结果
     */
    @Override
    public boolean deleteDeptById(Long deptId) {
        return removeById(deptId);
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(SysDept dept) {
        // String updateBy = dept.getUpdateBy();
        dept = getById(dept.getDeptId());
        // dept.setUpdateBy(updateBy);
        baseMapper.updateDeptStatus(dept);
    }

    /**
     * 修改子元素关系
     *
     * @param deptId       被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    private void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {
        List<SysDept> children = baseMapper.findChildrenDeptById(deptId);
        for (SysDept child : children) {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0) {
            baseMapper.updateDeptChildren(children);
        }
    }

}

