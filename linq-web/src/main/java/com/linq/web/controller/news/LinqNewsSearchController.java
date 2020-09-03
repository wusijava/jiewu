package com.linq.web.controller.news;

import com.linq.common.core.controller.BaseController;
import com.linq.common.result.Result;
import com.linq.news.service.NewsDocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 林义清
 * @Date: 2020/9/2 4:06 下午
 * @Description: 全局检索管理
 * @Version: 1.0.0
 */
@Api(tags = "新闻全局检索管理接口")
@RestController
@RequestMapping("/news/search")
public class LinqNewsSearchController extends BaseController {
    @Autowired
    private NewsDocumentService newsService;

    /**
     * 导入新闻数据
     */
    @ApiOperation(value = "导入新闻数据", notes = "导入新闻数据详情")
    @GetMapping("/import")
    public Result<String> importNews() {
        newsService.importNews();
        return toResult(true);
    }

}
