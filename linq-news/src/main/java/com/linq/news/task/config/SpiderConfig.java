package com.linq.news.task.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 林义清
 * @Date: 2020/8/31 5:57 下午
 * @Description:
 * @Version: 1.0.0
 */
@Configuration
public class SpiderConfig {
    /**
     * 爬虫地址相关配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spider", ignoreInvalidFields = true)
    public SpiderProperties spiderProperties() {
        return new SpiderProperties();
    }
}
