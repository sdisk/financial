package com.hq.financial.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @program: financial
 * @description: spring管理bean Spring的ApplicationContext的持有者，可以用静态的方法获取spring中的bean
 * @author: Mr.Huang
 * @create: 2018-10-22 09:31
 **/
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }
    public ApplicationContext getApplicationContext(){
        assertApplicationContext();
        return applicationContext;
    }
    public static <T> T getBean(String beanName){
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }
    public static <T> T getBean(Class <T> requireType){
        assertApplicationContext();
        return  applicationContext.getBean(requireType);
    }
    private static void assertApplicationContext(){
        if (null == SpringContextHolder.applicationContext){
            throw new RuntimeException("applicaitonContext属性为null,请检查是否注入了SpringContextHolder!");
        }
    }
}
