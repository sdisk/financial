package com.hq.financial.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hq.financial.config.datasource.DruidProperties;

/**
 * @program: financial
 * @description: 默认配置
 * @author: Mr.Huang
 * @create: 2018-10-16 14:29
 **/
@Configuration
public class DefaultProperties {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidProperties druidProperties(){
        return new DruidProperties();
    }
}
