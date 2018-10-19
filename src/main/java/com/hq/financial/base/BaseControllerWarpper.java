package com.hq.financial.base;

import java.util.List;
import java.util.Map;

/**
 * @program: financial
 * @description: wrap
 * @author: Mr.Huang
 * @create: 2018-10-17 11:36
 **/
public abstract class BaseControllerWarpper {
    public Object obj = null;

    public BaseControllerWarpper(Object obj){
        this.obj = obj;
    }
    public Object wrap(){
        if (this.obj instanceof List){
            List<Map<String, Object>> list = (List<Map<String, Object>>) this.obj;
            for (Map<String, Object> map : list) {
                warpTheMap(map);
            }
            return list;
        } else if (this.obj instanceof  Map){
            Map<String, Object> map = (Map<String, Object>) this.obj;
            warpTheMap(map);
            return map;
        } else {
            return this.obj;
        }
    }
    protected abstract void warpTheMap(Map<String, Object> map);
}
