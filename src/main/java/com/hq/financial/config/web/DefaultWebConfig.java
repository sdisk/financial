package com.hq.financial.config.web;

import com.hq.financial.exception.HuiException;
import com.hq.financial.exception.HuiExceptionEnum;
import com.hq.financial.util.DateUtil;
import com.hq.financial.web.controller.ErrorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @program: financial
 * @description: web
 * @author: Mr.Huang
 * @create: 2018-10-16 14:36
 **/
@Configuration
public class DefaultWebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Bean("error")
    public ErrorView error(){
        return new ErrorView();
    }
    @PostConstruct
    public void addConversionConfig(){
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
        genericConversionService.addConverter(new StringToDateConverter());
    }

    public class StringToDateConverter implements Converter<String, Date>{

        @Override
        public Date convert(String dateString) {
            String patternDate = "\\d{4}-\\d{1,2}-\\d{1,2}";
            String patternTimeMinutes = "\\d{d}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}";
            String patternTimeSeconds = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";

            boolean dateFlag = Pattern.matches(patternDate, dateString);
            boolean timeMinutesFlag = Pattern.matches(patternTimeMinutes, dateString);
            boolean timeSecondsFlag = Pattern.matches(patternTimeSeconds, dateString);
            if (dateFlag){
                return DateUtil.parseDate(dateString);
            } else if (timeMinutesFlag){
                return DateUtil.parseTimeMinutes(dateString);
            } else if (timeSecondsFlag){
                return DateUtil.parseTime(dateString);
            } else {
                throw new HuiException(HuiExceptionEnum.INVLIDE_DATE_STRING);
            }
        }
    }
}
