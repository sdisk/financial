package com.hq.financial.base.aop;

import com.hq.financial.base.tips.ErrorTip;
import com.hq.financial.exception.HuiException;
import com.hq.financial.exception.HuiExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: financial
 * @description: 全局拦截器
 * @author: Mr.Huang
 * @create: 2018-10-17 13:34
 **/
public class BaseControllerExceptionHandler {

    private static final Logger  LOGGER = LoggerFactory.getLogger(BaseControllerExceptionHandler.class);

    @ExceptionHandler(HuiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFound(HuiException e){
        LOGGER.error("业务异常：", e);
        return new ErrorTip(e.getCode(), e.getMessage());
    }
    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(RuntimeException e) {
        LOGGER.error("运行时异常:", e);
        return new ErrorTip(HuiExceptionEnum.SERVER_ERROR.getCode(), HuiExceptionEnum.SERVER_ERROR.getMessage());
    }
}
