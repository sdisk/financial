package com.hq.financial.base.tips;

/**
 * @program: financial
 * @description: 返回前台消息
 * @author: Mr.Huang
 * @create: 2018-10-16 15:36
 **/
public abstract class Tip {
    protected int code;
    protected  String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
