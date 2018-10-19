package com.hq.financial.base.tips;

/**
 * @program: financial
 * @description: 返回前台的成功提示消息
 * @author: Mr.Huang
 * @create: 2018-10-16 15:38
 **/
public class SuccessTip extends Tip {
    public SuccessTip() {
        super.code = 200;
        super.message = "操作成功";
    }
}
