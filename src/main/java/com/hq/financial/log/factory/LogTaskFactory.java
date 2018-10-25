package com.hq.financial.log.factory;

import com.hq.financial.base.db.Db;
import com.hq.financial.log.LogManager;
import com.hq.financial.util.ToolUtil;
import com.hq.financial.web.dao.SysLogMapper;
import com.hq.financial.web.entity.SysLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * @program: financial
 * @description: task
 * @author: Mr.Huang
 * @create: 2018-10-23 10:01
 **/
public class LogTaskFactory {
    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
    private static SysLogMapper mapper = Db.getMapper(SysLogMapper.class);

    public static TimerTask bussinessLog(String userName,String title,String url,String params){
        return new TimerTask() {
            @Override
            public void run() {
              SysLog sysLog =  LogFactory.creatLog(userName, title, url, params);
             try{
                 mapper.insert(sysLog);
             } catch (Exception e){
                 logger.error("创建业务日志异常!", e);
             }
            }
        };
    }
    public static TimerTask exceptionLog(final String userName, final Exception exception){
        return new TimerTask() {
            @Override
            public void run() {
                String msg = ToolUtil.getExceptionMsg(exception);
                SysLog sysLog =  LogFactory.creatLog(userName, msg, null, null);
                try{
                    mapper.insert(sysLog);
                } catch (Exception e){
                    logger.error("创建异常日志异常!", e);
                }
            }
        };
    }

}
