package com.hq.financial.common.aspect;

import com.google.gson.Gson;
import com.hq.financial.common.anno.Log;
import com.hq.financial.web.entity.SysLog;
import com.hq.financial.web.entity.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @program: financial
 * @description: 正常业务日志记录
 * @author: Mr.Huang
 * @create: 2018-10-17 16:41
 **/
@Aspect
@Component
public class LogAdvice {

    public static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class);

    @Pointcut("@annotation(com.hq.financial.common.anno.Log)")
    public void controllerAspect(){}

    /**
     * 当方法正常返回是执行
     * @param joinPoint
     */
    @AfterReturning("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Log log = method.getAnnotation(Log.class);
        SysUser sysUser = (SysUser) request.getSession().getAttribute("login_user");
        if (null != log){
            SysLog sysLog  = new SysLog();
            sysLog.setCreateTime(new Date());
            sysLog.setTitle(log.valus());
            sysLog.setUserName((sysUser != null )? sysUser.getUserName() : "system");
            sysLog.setUrl(request.getRequestURI().toString());
            sysLog.setParams(new Gson().toJson(request.getParameterMap()));
            //SpringUtil.getBean(ISysLogService.class).insert(sysLog);
            LOGGER.debug("记录日志:" + sysLog.toString());
        }

    }
}
