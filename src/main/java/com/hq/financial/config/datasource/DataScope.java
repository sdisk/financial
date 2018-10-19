package com.hq.financial.config.datasource;

import java.util.List;

/**
 * @program: financial
 * @description: 数据范围
 * @author: Mr.Huang
 * @create: 2018-10-15 16:25
 **/
public class DataScope {

    //限制范围的字段名
    private String scopeName = "limitId";
    //限制方位的数据字段
    private List<Integer> limitIds;

    public DataScope() {

    }

    public DataScope(String scopeName, List<Integer> limitIds) {
        this.scopeName = scopeName;
        this.limitIds = limitIds;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public List<Integer> getLimitIds() {
        return limitIds;
    }

    public void setLimitIds(List<Integer> limitIds) {
        this.limitIds = limitIds;
    }
}
