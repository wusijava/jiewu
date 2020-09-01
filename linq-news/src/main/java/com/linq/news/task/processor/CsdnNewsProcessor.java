package com.linq.news.task.processor;

import com.linq.common.exception.CustomException;
import com.linq.common.utils.string.StringUtils;
import com.linq.news.domain.LinqNews;
import com.linq.news.task.config.target.CsdnNewsProperties;
import com.linq.news.task.pipeline.NewsPipeline;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

import java.util.List;
import java.util.Objects;

/**
 * @Author: 林义清
 * @Date: 2020/9/1 9:42 上午
 * @Description: 爬取csdn博客
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class CsdnNewsProcessor implements PageProcessor {
    @Autowired
    private NewsPipeline pipeline;

    @Override
    public void process(Page page) {
        try {
            LinqNews news = new LinqNews();
            Html html = page.getHtml();
            // 拿到csdn专栏所有a标签请求连接并且发送请求 获取到的详情页连接
            List<String> urls = page.getHtml().$(CsdnNewsProperties.targetUrlCssSelector).links().all();
            page.addTargetRequests(urls);
            // 获取所有详情页列表---- 文本内容
            List<Selectable> selectableList = page.getHtml().$(CsdnNewsProperties.detailSelectCssSelector).nodes();
            // 遍历详情页列表 解析出每一个信息 存到  LinqNews
            selectableList.stream().filter(Objects::nonNull)
                    .forEach(selectable -> {
                        log.info("正在使用的详情页链接->{}", selectable.links().toString());
                        // 设置作者id 管理员 id = 1
                        news.setUserId(1L);
                        // 设置新闻来源
                        news.setNewsSource(selectable.links().toString());
                        // 设置新闻标题 selectable.$(".main-title").toString()
                        news.setNewsTitle(Jsoup.parse(html.$(CsdnNewsProperties.newsTitleCssSelector).toString()).text());
                        // 设置原创作者
                        news.setNewsSourceAuthor(Jsoup.parse(html.$(CsdnNewsProperties.newsSourceAuthorSelector).toString()).text());
                        // 点赞数
                        news.setThumbs(Long.valueOf(Jsoup.parse(html.$(CsdnNewsProperties.thumbsSelector).toString()).text()));
                        // 浏览量
                        news.setVisits(Long.valueOf(Jsoup.parse(html.$(CsdnNewsProperties.visitsSelector).toString()).text()));
                        // 评论数
                        news.setVisits(Long.valueOf(Jsoup.parse(html.$(CsdnNewsProperties.visitsSelector).toString()).text()));
                        // 收藏数
                        news.setCollects(Long.valueOf(Jsoup.parse(html.$(CsdnNewsProperties.cllectsSelector).toString()).text()));
                        // 技术博客周刊 id=18
                        news.setNewsTypeId(CsdnNewsProperties.newsTypeId);
                        // 设置新闻内容
                        news.setNewsContent(selectable.$(CsdnNewsProperties.newsContentCssSelector).toString());
                        // 设置新闻封面
                        Selectable src = selectable.$(CsdnNewsProperties.newsImageCssSelector, "src");
                        log.info("src->>>>>>>>{}", src);
                        if (StringUtils.isNotEmpty(src.toString())) {
                            news.setNewsImage(src.toString());
                        }
                        // 设置新闻属性 热点区新闻 2
                        news.setNewsAttr("2");
                    });
            // log.info("爬取下来的newsList->{}", newsList);
            // 把结果保存起来
            page.putField(CsdnNewsProperties.fieldKey, news);
        } catch (Exception e) {
            throw new CustomException("爬取新闻解析错误");
        }
    }

    @Override
    public Site getSite() {
        return Site.me()
                .setCharset("utf8") //设置编码
                .setSleepTime(20 * 1000) // 间隔时间
                .setTimeOut(10 * 1000) //设置超时时间
                .setRetrySleepTime(3000) //设置重试的间隔时间
                .setRetryTimes(3); //设置重试的次数;;
    }


    // 执行爬虫
    //initialDelay当任务启动后，等等多久执行方法
    //fixedDelay每隔多久执行方法
    @Scheduled(cron = "0 0/20 15,16,17 * * ?")
    public void runSpiderProcess() {
        log.info("正在进行爬取中........");
        // 配置代理模式
        //        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
        //                new Proxy("124.93.201.59", 42672),
        //                new Proxy("222.90.110.194", 8080),
        //                new Proxy("120.236.130.132", 8060)
        //        ));

        Spider.create(new CsdnNewsProcessor())
                //.setDownloader(httpClientDownloader) //设置代理
                .addUrl(CsdnNewsProperties.getInitUrlList().toArray(new String[0])) // 爬取地址
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(10)
                .addPipeline(pipeline)
                .run();
    }
}
