package com.linq.web;

import com.alibaba.fastjson.JSON;
import com.linq.common.constant.Constants;
import com.linq.common.core.domain.TreeSelect;
import com.linq.common.core.domain.entity.SysDept;
import com.linq.common.core.redis.RedisService;
import com.linq.common.utils.string.StringUtils;
import com.linq.framework.web.domain.Server;

import com.linq.news.mapper.NewsDocumentDao;
import com.linq.news.service.NewsDocumentService;
import com.linq.news.task.config.target.CsdnNewsProperties;
import com.linq.news.task.config.target.EntertainmentNewsProperties;
import com.linq.news.task.config.target.PeNewsProperties;
import com.linq.news.task.processor.CsdnNewsProcessor;
import com.linq.news.task.processor.SinaEntertainmentNewsProcessor;
import com.linq.news.task.processor.SinaPeNewsProcessor;
import com.linq.system.service.SysDeptService;
import com.linq.system.service.SysRoleService;
import com.linq.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 林义清
 * @Date: 2020/8/25 4:55 下午
 * @Description:
 * @Version: 1.0.0
 */
@SpringBootTest
public class TestDemos {
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysDeptService deptService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private NewsDocumentDao newsDocumentDao;
    @Autowired
    private NewsDocumentService newsDocumentService;
    @Test
    public void testSet() {
        Object cacheObject = redisService.getCacheObject(Constants.LOGIN_TOKEN_KEY + "6c637b64-a130-40dd-ab67-75d7ab0840ab");
        System.out.println(cacheObject);
    }

    @Test
    public void testString() {
        System.out.println(StringUtils.capitalize("asaaadasd"));
    }

    @Test
    public void testToDeptTree() {
        List<TreeSelect> treeSelects = deptService.buildDeptTreeSelect(deptService.findDeptList(new SysDept()));
        System.err.println(JSON.toJSON(treeSelects));
    }

    @Test
    public void testCheckUsernameUnique() {
        System.err.println(userService.checkUsernameUnique("ry"));
    }

    @Test
    public void testfindRoleAll() {
        System.err.println(roleService.findRoleAll());
    }

    @Test
    public void testfindServer() {
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(server);
    }

    @Test
    public void testSinaPeNewsSpider() {
        Spider.create(new SinaPeNewsProcessor())
                .addUrl(PeNewsProperties.url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(4)
                .run();
    }

    @Test
    public void testCsdnNewsSpider() {
        Spider.create(new CsdnNewsProcessor())
                //.setDownloader(httpClientDownloader) //设置代理
                .addUrl(CsdnNewsProperties.getInitUrlList().toArray(new String[0])) // 爬取地址
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(10)
                .run();
    }

    @Test
    public void testEntertainmentSpider() {
        Spider.create(new SinaEntertainmentNewsProcessor())
                //.setDownloader(httpClientDownloader) //设置代理
                .addUrl(EntertainmentNewsProperties.url) // 爬取地址
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(4)
                .run();
    }

    @Test
    public void testElasticsearchDeleteAll() {
        newsDocumentDao.deleteAll();
    }

    @Test
    public void testElasticsearchImportALL() {
        newsDocumentService.importNews();
    }
}
