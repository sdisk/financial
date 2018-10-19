package com.hq.financial.base.tips;

/**
 * @program: financial
 * @description: 前台的错误提示
 * @author: Mr.Huang
 * @create: 2018-10-16 15:36
 **/
public class ErrorTip extends Tip {

    public ErrorTip(int code,String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
