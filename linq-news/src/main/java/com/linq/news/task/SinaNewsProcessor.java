package com.linq.news.task;

import com.linq.common.exception.CustomException;
import com.linq.common.utils.string.StringUtils;
import com.linq.news.domain.LinqNews;
import com.linq.news.task.config.SpiderProperties;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 林义清
 * @Date: 2020/8/31 5:19 下午
 * @Description: 爬取新闻页面处理器页面
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class SinaNewsProcessor implements PageProcessor {
    @Autowired
    private SpiderProperties spiderProperties;
    @Autowired
    private SinaPipeline sinaPipeline;
    /**
     * 解析页面
     */
    @Override
    public void process(Page page) {
        try {
            LinqNews news = new LinqNews();
            Html html = page.getHtml();
            // 拿到新浪nba专栏所有a标签请求连接并且发送请求 获取到的详情页连接
            List<String> urls = page.getHtml().$("div.news-list-b").links().regex(".*shtml$").all();
            page.addTargetRequests(urls);
            // 获取所有详情页列表---- 文本内容
            List<Selectable> selectableList = page.getHtml().$(".main-content.w1240").nodes();
            // log.info("从详情页面获取数据列表->{}", selectableList);
            // 遍历详情页列表 解析出每一个信息 存到  LinqNews
            selectableList.stream().filter(Objects::nonNull)
                    .forEach(selectable -> {
                        log.info("正在使用的详情页链接->{}", selectable.links().toString());
                        // 设置作者id 管理员 id = 1
                        news.setUserId(1L);
                        // 设置新闻来源
                        news.setNewsSource(selectable.links().toString());
                        // 设置新闻标题 selectable.$(".main-title").toString()
                        news.setNewsTitle(Jsoup.parse(html.$(".main-title").toString()).text());
                        // 体育新闻类型 id=4
                        news.setNewsTypeId(4L);
                        // 设置新闻内容
                        news.setNewsContent(selectable.$("#artibody").toString());
                        // 设置新闻封面
                        Selectable src = selectable.$(".img_wrapper > img", "src");
                        log.info("src->{}", src);
                        if (StringUtils.isNotNull(src)) {
                            news.setNewsImage(src.toString());
                        }
                        // 设置新闻属性 热点区新闻 2
                        news.setNewsAttr("2");
                    });

            // log.info("爬取下来的newsList->{}", newsList);
            // 把结果保存起来
            page.putField("news", news);
        } catch (Exception e) {
            throw new CustomException("新浪爬取新闻解析错误");
        }

    }

    @Override
    public Site getSite() {
        return Site.me()
                .setCharset("utf8") //设置编码
                .setSleepTime(20 * 1000) // 间隔时间
                .setTimeOut(10 * 1000) //设置超时时间
                .setRetrySleepTime(3000) //设置重试的间隔时间
                .setRetryTimes(3); //设置重试的次数;
    }

    // 执行爬虫
    //initialDelay当任务启动后，等等多久执行方法
    //fixedDelay每隔多久执行方法
    @Scheduled(initialDelay = 1000, fixedDelay = 100 * 1000)
    public void runSpider() {
        log.info("正在进行爬取中........");
        Spider.create(new SinaNewsProcessor())
                .addUrl(spiderProperties.getPeNewsUrl())
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(4)
                .addPipeline(sinaPipeline)
                .run();
    }
}
