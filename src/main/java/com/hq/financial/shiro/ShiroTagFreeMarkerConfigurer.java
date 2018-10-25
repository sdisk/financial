package com.hq.financial.shiro;

import com.hq.financial.shiro.tag.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * @program: financial
 * @description: shiroTag
 * @author: Mr.Huang
 * @create: 2018-10-22 11:22
 **/
public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer {

    @Override
    public void afterPropertiesSet() throws IOException,TemplateException{
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}
