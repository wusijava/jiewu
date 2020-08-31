package com.linq.framework.security.config;


import com.linq.framework.security.config.bean.LoginProperties;
import com.linq.framework.security.config.bean.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 林义清
 * @Date: 2020/8/23 5:01 下午
 * @Description: 配置文件转换Pojo类的统一配置类
 * @Version: 1.0.0
 */
@Configuration
public class BeanConfig {
    /**
     * 登录认证相关配置
     */
    @Bean
    @ConfigurationProperties(prefix = "login", ignoreUnknownFields = true)
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }

    /**
     * jwt相关信息配置
     */
    @Bean
    @ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = true)
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

}
