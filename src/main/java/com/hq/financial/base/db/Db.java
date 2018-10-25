package com.hq.financial.base.db;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hq.financial.util.SpringContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: financial
 * @description:
 * @author: Mr.Huang
 * @create: 2018-10-23 10:03
 **/
public class Db <T> {
    /**
     * 对每个db类，提供一个mapper接口
     */
    private Class<T> clazz;

    private BaseMapper<?> baseMapper;

    /**
     * 私有构造方法
      */
    private Db(Class clazz){
        this.clazz = clazz;
        this.baseMapper = (BaseMapper<?>) SpringContextHolder.getBean(clazz);
    }
    /**
     * 创建包含指定mapper的Db工具类
     */
    public static <T> Db<T> creat(Class<T> clazz){
        return new Db<T>(clazz);
    }
    /**
     * 获取一个mapper的快捷方法
     */
    public BaseMapper<?> getMapper(){
        return this.baseMapper;
    }
    /**
     * 获取一个mapper的快捷方法
     */
    public static <T> T getMapper(Class<T> clazz){
        return SpringContextHolder.getBean(clazz);
    }
    /**
     *通过一个条件获取一个记录(会返回null)
     */
    public <E> E selectOneByCon(String condition, Object value){
        List<?> lists = selectOneByConList(condition, value);
        if (lists != null && lists.size()> 0){
            return (E) lists.get(0);
        } else {
            return null;
        }
    }

    /**
     * 通过一个条件获取一堆记录(会返回null)
     */
    public <E> List<E>  selectOneByConList(String condition, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(condition, value);
        List<E> results  = (List<E>) this.baseMapper.selectByMap(map);
        if (results == null || results.size() == 0){
            return null;
        }else{
            return results;
        }
    }


}
