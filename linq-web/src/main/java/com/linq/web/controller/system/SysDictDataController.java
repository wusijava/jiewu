package com.linq.web.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.core.controller.BaseController;
import com.linq.common.core.domain.entity.SysDictData;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.SecurityUtils;
import com.linq.system.service.SysDictDataService;
import com.linq.system.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/27 9:12 下午
 * @Description: 数据字典信息
 * @Version: 1.0.0
 */
@Api(tags = "数据字典数据管理")
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    @Autowired
    private SysDictDataService dictDataService;
    @Autowired
    private SysDictTypeService dictTypeService;

    /**
     * 条件分页获取字典列表
     *
     * @return 结果集合
     */
    @ApiOperation(value = "条件分页获取字典列表", notes = "条件分页获取字典列表详情")
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<SysDictData>> list(@PathVariable("page") int page, @PathVariable("size") int size, SysDictData dictData) {
        System.err.println("SysDictData查询条件->" + dictData);
        IPage<SysDictData> iPage = dictDataService.findPage(new Page<SysDictData>(page, size), dictData);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 查询字典数据详细信息
     */
    @ApiOperation(value = "查询字典数据详细信息", notes = "查询字典数据详细信息详情")
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{dictCode}")
    public Result<SysDictData> getInfo(@PathVariable("dictCode") Long dictCode) {
        return ResultUtils.success(dictDataService.findDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @ApiOperation(value = "根据字典类型查询字典数据信息", notes = "根据字典类型查询字典数据信息详情")
    @GetMapping(value = "/type/{dictType}")
    public Result<List<SysDictData>> dictType(@PathVariable String dictType) {
        return ResultUtils.success(dictTypeService.findDictDataByType(dictType));
    }

    /**
     * 新增字典类型
     */
    @ApiOperation(value = "新增字典类型", notes = "新增字典类型详情")
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@Validated @RequestBody SysDictData dict) {
        // dict.setCreateBy(SecurityUtils.getUsername());
        return toResult(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @ApiOperation(value = "修改保存字典类型", notes = "修改保存字典类型详情")
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@Validated @RequestBody SysDictData dict) {
        // dict.setUpdateBy(SecurityUtils.getUsername());
        return toResult(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @ApiOperation(value = "删除字典类型", notes = "删除字典类型详情")
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public Result<String> remove(@PathVariable List<Long> dictCodes) {
        return toResult(dictDataService.deleteDictDataByIds(dictCodes));
    }
}
