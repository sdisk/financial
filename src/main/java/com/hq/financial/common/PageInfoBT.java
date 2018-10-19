package com.hq.financial.common;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * @program: financial
 * @description: 分页参数
 * @author: Mr.Huang
 * @create: 2018-10-17 11:25
 **/
public class PageInfoBT<T>  {

    // 结果集
    private List<T> raws;
    //总数
    private long total;

    public  PageInfoBT(Page<T> page) {
        this.raws = page.getRecords();
        this.total = page.getTotal();
    }

    public List<T> getRaws() {
        return raws;
    }

    public void setRaws(List<T> raws) {
        this.raws = raws;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
