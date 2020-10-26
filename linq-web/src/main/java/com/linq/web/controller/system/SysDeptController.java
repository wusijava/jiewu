package com.linq.web.controller.system;

import com.linq.common.constant.UserConstants;
import com.linq.common.core.controller.BaseController;
import com.linq.common.core.domain.TreeSelect;
import com.linq.common.core.domain.entity.SysDept;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.string.StringUtils;
import com.linq.framework.security.service.TokenService;
import com.linq.system.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/26 9:26 上午
 * @Description:
 * @Version: 1.0.0
 */
@Api(tags = "部门管理接口")
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {
    @Autowired
    private SysDeptService deptService;


    /**
     * 获取部门列表
     */
    @ApiOperation(value = "获取部门列表", notes = "获取部门列表详情")
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list")
    public Result<List<SysDept>> list(SysDept dept) {
        List<SysDept> depts = deptService.findDeptList(dept);
        return ResultUtils.success(depts);
    }

    /**
     * 查询部门列表（排除节点）
     */
    @ApiOperation(value = "查询部门列表(排除某个子节点)", notes = "查询部门列表(排除某个子节点)详情")
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list/exclude/{deptId}")
    public Result<List<SysDept>> excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<SysDept> depts = deptService.findDeptList(new SysDept());
        depts.removeIf(d -> d.getDeptId().intValue() == deptId || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + ""));
        return ResultUtils.success(depts);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @ApiOperation(value = "根据部门编号获取详细信息", notes = "根据部门编号获取详细信息详情")
    @PreAuthorize("@ss.hasPermi('system:dept:query')")
    @GetMapping(value = "/{deptId}")
    public Result<SysDept> getInfo(@PathVariable("deptId") Long deptId) {
        return ResultUtils.success(deptService.findDeptById(deptId));
    }

    /**
     * 获取部门下拉树列表
     */
    @ApiOperation(value = "获取部门下拉树列表", notes = "获取部门下拉树列表详情")
    @GetMapping("/tree/select")
    public Result<List<TreeSelect>> treeSelect(SysDept dept) {
        List<SysDept> depts = deptService.findDeptList(dept);
        // 构造下拉列表树
        return ResultUtils.success(deptService.buildDeptTreeSelect(depts));
    }


    /**
     * 加载对应角色部门列表树
     */
    @ApiOperation(value = "加载对应角色部门列表树", notes = "加载对应角色部门列表树详情")
    @GetMapping(value = "/role/dept/tree/select/{id}")
    public Result<HashMap<String, Object>> roleDeptTreeselect(@PathVariable("id") Long roleId) {
        List<SysDept> depts = deptService.findDeptList(new SysDept());
        return ResultUtils.success(new HashMap<String, Object>() {{
            put("checkedKeys", deptService.findDeptListByRoleId(roleId));
            put("depts", deptService.buildDeptTreeSelect(depts));
        }});
    }

    /**
     * 新增部门
     */
    @ApiOperation(value = "新增部门", notes = "新增部门详情")
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@Validated @RequestBody SysDept dept) {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return ResultUtils.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        // dept.setCreateBy(SecurityUtils.getUsername());
        return toResult(deptService.insertDept(dept));
    }


    /**
     * 修改部门
     */
    @ApiOperation(value = "修改部门", notes = "修改部门详情")
    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@Validated @RequestBody SysDept dept) {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return ResultUtils.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        } else if (dept.getParentId().equals(dept.getDeptId())) {
            return ResultUtils.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        } else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus()) && deptService.findNormalChildrenDeptById(dept.getDeptId())) {
            return ResultUtils.error("该部门包含未停用的子部门！");
        }
        // dept.setUpdateBy(SecurityUtils.getUsername());
        return toResult(deptService.updateDept(dept));
    }

    /**
     * 删除部门
     */
    @ApiOperation(value = "删除部门", notes = "删除部门详情")
    @PreAuthorize("@ss.hasPermi('system:dept:remove')")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public Result<String> remove(@PathVariable Long deptId) {
        if (deptService.hasChildByDeptId(deptId)) {
            return ResultUtils.error("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return ResultUtils.error("部门存在用户,不允许删除");
        }
        return toResult(deptService.deleteDeptById(deptId));
    }
}
