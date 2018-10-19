package com.hq.financial.exception;

/**
 * @program: financial
 * @description: 抽象接口
 * @author: Mr.Huang
 * @create: 2018-10-16 14:56
 **/
public interface ServiceExceptionEnum {
    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
