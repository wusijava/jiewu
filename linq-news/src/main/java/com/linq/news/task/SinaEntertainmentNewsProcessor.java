package com.linq.news.task;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Author: 林义清
 * @Date: 2020/9/1 9:41 上午
 * @Description: 爬取新浪娱乐新闻
 * @Version: 1.0.0
 */
@Component
public class SinaEntertainmentNewsProcessor implements PageProcessor {
    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return null;
    }
}
