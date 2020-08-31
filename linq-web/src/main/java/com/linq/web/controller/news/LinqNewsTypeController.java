package com.linq.web.controller.news;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.controller.BaseController;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.news.domain.LinqNewsType;
import com.linq.news.service.LinqNewsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:22 下午
 * @Description:
 * @Version: 1.0.0
 */
@Api(tags = "新闻类型管理接口")
@RestController
@RequestMapping("/news/type")
public class LinqNewsTypeController extends BaseController {
    @Autowired
    private LinqNewsTypeService newsTypeService;

    /**
     * 获取所有新闻类别下拉列表
     */
    @ApiOperation(value = "获取所有新闻类别下拉列表", notes = "获取所有新闻类别下拉列表详情")
    @GetMapping("/list")
    public Result<List<LinqNewsType>> optionSelect() {
        return ResultUtils.success(newsTypeService.list(new LambdaQueryWrapper<LinqNewsType>()
                                                                .select(LinqNewsType::getNewsTypeId, LinqNewsType::getNewsTypeName, LinqNewsType::getStatus)
                                                                .eq(LinqNewsType::getDelFlag, UserConstants.NORMAL)
        ));
    }

    /**
     * 条件分页新闻类型列表
     *
     * @return 结果集合
     */
    @ApiOperation(value = "条件分页新闻类型列表", notes = "条件分页新闻类型列表详情")
    @PreAuthorize("@ss.hasPermi('news:type:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<LinqNewsType>> list(@PathVariable("page") int page, @PathVariable("size") int size, LinqNewsType newsType) {
        System.err.println("LinqNewsType查询条件->" + newsType);
        IPage<LinqNewsType> iPage = newsTypeService.findPage(new Page<LinqNewsType>(page, size), newsType);
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 获取新闻类型详细信息
     */
    @ApiOperation(value = "获取新闻类型详细信息", notes = "获取新闻类型详细信息详情")
    @PreAuthorize("@ss.hasPermi('news:type:query')")
    @GetMapping(value = "/{newsTypeId}")
    public Result<LinqNewsType> getInfo(@PathVariable("newsTypeId") Long newsTypeId) {
        return ResultUtils.success(newsTypeService.findNewsTypeById(newsTypeId));
    }

    /**
     * 新增新闻类型
     */
    @ApiOperation(value = "新增新闻类型", notes = "新增新闻类型详情")
    @PreAuthorize("@ss.hasPermi('news:type:add')")
    @Log(title = "新闻类型", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@RequestBody LinqNewsType newsType) {
        if (UserConstants.NOT_UNIQUE.equals(newsTypeService.checkNewsTypeNameUnique(newsType))) {
            return ResultUtils.error("新增新闻类型'" + newsType.getNewsTypeName() + "'失败，新闻类型名称已存在");
        }
        return toResult(newsTypeService.insertNewsType(newsType));
    }

    /**
     * 修改新闻类型
     */
    @ApiOperation(value = "修改新闻类型", notes = "修改新闻类型详情")
    @PreAuthorize("@ss.hasPermi('news:type:edit')")
    @Log(title = "新闻类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@RequestBody LinqNewsType newsType) {
        if (UserConstants.NOT_UNIQUE.equals(newsTypeService.checkNewsTypeNameUnique(newsType))) {
            return ResultUtils.error("修改新闻类型'" + newsType.getNewsTypeName() + "'失败，新闻类型名称已存在");
        }
        return toResult(newsTypeService.updateNewsType(newsType));
    }

    /**
     * 删除新闻类型
     */
    @ApiOperation(value = "删除新闻类型", notes = "删除新闻类型详情")
    @PreAuthorize("@ss.hasPermi('news:type:remove')")
    @Log(title = "新闻类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{newsTypeIds}")
    public Result<String> remove(@PathVariable List<Long> newsTypeIds) {
        return toResult(newsTypeService.deleteNewsTypeByIds(newsTypeIds));
    }
}
