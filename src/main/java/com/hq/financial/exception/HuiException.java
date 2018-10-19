package com.hq.financial.exception;

/**
 * @program: financial
 * @description: 运行异常
 * @author: Mr.Huang
 * @create: 2018-10-16 14:54
 **/
public class HuiException extends RuntimeException {
    private Integer code;
    private String message;

    public HuiException(ServiceExceptionEnum serviceExceptionEnum ) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
