package com.linq.web.controller.news;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.common.constant.UserConstants;
import com.linq.common.core.controller.BaseController;
import com.linq.common.core.domain.LoginUser;
import com.linq.common.core.domain.entity.SysUser;
import com.linq.common.lang.annotation.Log;
import com.linq.common.lang.enums.BusinessType;
import com.linq.common.result.PageResult;
import com.linq.common.result.Result;
import com.linq.common.result.ResultUtils;
import com.linq.common.utils.ServletUtils;
import com.linq.framework.security.service.TokenService;
import com.linq.news.domain.LinqNews;
import com.linq.news.domain.LinqNewsType;
import com.linq.news.service.LinqNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 林义清
 * @Date: 2020/8/29 1:27 下午
 * @Description: 新闻管理
 * @Version: 1.0.0
 */
@Api(tags = "新闻管理接口")
@RestController
@RequestMapping("/news/news")
public class LinkNewsController extends BaseController {
    @Autowired
    private LinqNewsService newsService;
    @Autowired
    private TokenService tokenService;


    /**
     * 查询新闻列表
     */
    @ApiOperation(value = "条件分页查询新闻列表", notes = "条件分页查询新闻列表详情")
    @PreAuthorize("@ss.hasPermi('news:news:list')")
    @GetMapping("/list/{page}/{size}")
    public PageResult<List<LinqNews>> list(@PathVariable("page") int page, @PathVariable("size") int size, LinqNews news) {
        System.err.println("LinqNews查询条件->" + news);
        // 如果是超级管理员 就查询出全部
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        IPage<LinqNews> iPage = null;
        if (user.isAdmin()) {
            iPage = newsService.findPageAll(new Page<LinqNews>(page, size), news);
        } else {
            // 如果不是就根据用户id查询出来
            iPage = newsService.findPageByUserId(new Page<LinqNews>(page, size), news, user.getUserId());
        }
        return ResultUtils.success(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getRecords());
    }


    /**
     * 获取新闻详细信息
     */
    @ApiOperation(value = "获取新闻详细信息", notes = "获取新闻详细信息详情")
    @PreAuthorize("@ss.hasPermi('news:news:query')")
    @GetMapping(value = "/{newsId}")
    public Result<LinqNews> getInfo(@PathVariable("newsId") Long newsId) {
        return ResultUtils.success(newsService.findLinqNewsById(newsId));
    }

    /**
     * 新增新闻
     */
    @ApiOperation(value = "新增新闻", notes = "新增新闻详情")
    @PreAuthorize("@ss.hasPermi('news:news:add')")
    @Log(title = "新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public Result<String> add(@RequestBody LinqNews linqNews) {
        return toResult(newsService.insertLinqNews(linqNews));
    }

    /**
     * 修改新闻
     */
    @ApiOperation(value = "修改新闻", notes = "修改新闻详情")
    @PreAuthorize("@ss.hasPermi('news:news:edit')")
    @Log(title = "新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result<String> edit(@RequestBody LinqNews linqNews) {
        // 判断新闻标题是否重复
        if (UserConstants.NOT_UNIQUE.equals(newsService.checkNewsTitleUnique(linqNews))) {
            return ResultUtils.error("修改新闻'" + linqNews.getNewsTitle() + "'失败，新闻标题已存在");
        }
        return toResult(newsService.updateLinqNews(linqNews));
    }

    /**
     * 删除新闻
     */
    @ApiOperation(value = "删除新闻", notes = "删除新闻详情")
    @PreAuthorize("@ss.hasPermi('news:news:remove')")
    @Log(title = "新闻", businessType = BusinessType.DELETE)
    @DeleteMapping("/{newsIds}")
    public Result<String> remove(@PathVariable List<Long> newsIds) {
        return toResult(newsService.deleteLinqNewsByIds(newsIds));
    }


    /**
     * 公开私有修改
     */
    @ApiOperation(value = "公开私有新闻修改", notes = "公开私有新闻修改详情")
    @PreAuthorize("@ss.hasPermi('news:news:edit')")
    @Log(title = "新闻管理", businessType = BusinessType.UPDATE)
    @PutMapping("/change/isPublic")
    public Result<String> changeIsPublic(@RequestBody LinqNews news) {
        System.err.println("公开私有修改LinqNews->" + news);
        return toResult(newsService.changeIsPublic(news));
    }

    /**
     * 审核新闻修改
     */
    @ApiOperation(value = "审核新闻修改", notes = "审核新闻修改详情")
    @PreAuthorize("@ss.hasPermi('news:news:inspect')")
    @Log(title = "新闻管理", businessType = BusinessType.UPDATE)
    @PutMapping("/change/status")
    public Result<String> changeStatus(@RequestBody LinqNews news) {
        System.err.println("审核新闻修改LinqNews->" + news);
        return toResult(newsService.changeStatus(news));
    }

    /**
     * 获取所有新闻下拉列表
     */
    @ApiOperation(value = "获取所有新闻下拉列表", notes = "获取所有新闻下拉列表详情")
    @GetMapping("/list")
    public Result<List<LinqNews>> optionSelect() {
        // select news_id,news_title from linq_news where del_flag='0' and status = '1'
        return ResultUtils.success(newsService.list(new LambdaQueryWrapper<LinqNews>()
                                                            .select(LinqNews::getNewsId, LinqNews::getNewsTitle)
                                                            .eq(LinqNews::getDelFlag, UserConstants.NORMAL)
                                                            .eq(LinqNews::getStatus, UserConstants.PASSED)
        ));
    }
}
