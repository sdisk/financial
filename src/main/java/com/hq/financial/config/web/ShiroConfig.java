package com.hq.financial.config.web;

import com.hq.financial.config.FinProperties;
import com.hq.financial.shiro.MyRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @program: financial
 * @description: shiro配置
 * @author: Mr.Huang
 * @create: 2018-10-22 14:51
 **/
//@Configuration
public class ShiroConfig {

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(CookieRememberMeManager rememberMeManager, CacheManager cacheManager,
                                                     SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.myRealm());
        securityManager.setCacheManager(cacheManager);
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
    /**
     * session管理器
     */
    @Bean
    @ConditionalOnProperty(prefix = "hq",name = "spring-session-open",havingValue = "false")
    public DefaultWebSessionManager defaultWebSessionManager(CacheManager cacheManager, FinProperties properties){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheManager);
        sessionManager.setSessionValidationInterval(properties.getSessionValidationInterval()* 1000);
        sessionManager.setGlobalSessionTimeout(properties.getSessionInvalidateTime()* 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(true);
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("shiroCookie");
        cookie.setHttpOnly(true);  //JavaScript脚本将无法读取到Cookie信息,反正xss攻击
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }
    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public CacheManager getCacheShiroManager(EhCacheManagerFactoryBean ehcache){
        EhCacheManager ehcacheManager = new EhCacheManager();
        ehcacheManager.setCacheManager(ehcache.getObject());
        return ehcacheManager;
    }

    /**
     * 自定义身份认证 realm
     */
    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }
    /**
     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    /**
     * 记住密码Cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
        return simpleCookie;
    }
    /**
     *Shiro的过滤器链
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //默认登录访问url
        shiroFilter.setLoginUrl("/login");
        //登录成功后跳转的url
        shiroFilter.setSuccessUrl("/");
        //没有权限跳转的url
        shiroFilter.setUnauthorizedUrl("/global/error");
        // 设置拦截器
        Map<String, String> hashMap = new LinkedHashMap<>();
        //开放的接口
        hashMap.put("/static/**", "anon");
        hashMap.put("/gunsApi/**", "anon");
        hashMap.put("/login", "anon");
        hashMap.put("/global/sessionError", "anon");
        hashMap.put("/kaptcha", "anon");
        //其余接口一律拦截
        hashMap.put("/**", "user");
        shiroFilter.setFilterChainDefinitionMap(hashMap);
        return shiroFilter;
    }
    /**
     * 在方法中 注入 securityManager,进行代理控制
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(new Object[]{securityManager});
        return bean;
    }

    /**
     * Shiro生命周期处理器:
     * 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调(例如:UserRealm)
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
