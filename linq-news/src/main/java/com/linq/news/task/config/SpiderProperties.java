package com.linq.news.task.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 林义清
 * @Date: 2020/8/31 5:37 下午
 * @Description: 爬虫配置
 * @Version: 1.0.0
 */
@Data
public class SpiderProperties {
    /**
     * 体育新闻
     */
    private String peNewsUrl = "";
    /**
     * 娱乐新闻
     */
    private String entertainmentNewsUrl = "";
}
