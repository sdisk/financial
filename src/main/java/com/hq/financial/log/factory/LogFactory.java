package com.hq.financial.log.factory;

import com.hq.financial.web.entity.SysLog;

import java.util.Date;

/**
 * @program: financial
 * @description: log
 * @author: Mr.Huang
 * @create: 2018-10-23 09:48
 **/
public class LogFactory {
    /**
     * 创建操作日志
     */
    public static SysLog creatLog(String userName,String title,String url,String params){
        SysLog sysLog = new SysLog();
        sysLog.setUserName(userName);
        sysLog.setTitle(title);
        sysLog.setUrl(url);
        sysLog.setParams(params);
        sysLog.setCreateTime(new Date());
        return sysLog;
    }
}
