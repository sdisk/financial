package com.hq.financial.common.enums;

/**
 * @program: financial
 * @description: 排序
 * @author: Mr.Huang
 * @create: 2018-10-19 16:55
 **/
public enum Order{
    ASC("asc"),DESC("desc");
    private String des;

    Order(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
