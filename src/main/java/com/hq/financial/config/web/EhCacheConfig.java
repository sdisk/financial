package com.hq.financial.config.web;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @program: financial
 * @description: ehcache
 * @author: Mr.Huang
 * @create: 2018-10-22 13:50
 **/
@Configuration
@EnableCaching
public class EhCacheConfig{

    @Bean
    public EhCacheCacheManager cacheCacheManager(CacheManager cacheManager){
        return new EhCacheCacheManager(cacheManager);
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return cacheManagerFactoryBean;
    }

}
