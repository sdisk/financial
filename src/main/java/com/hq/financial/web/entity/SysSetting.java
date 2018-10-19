package com.hq.financial.web.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 系统设置表
 * </p>
 *
 * @author qiang.H
 * @since 2018-10-18
 */
@TableName("sys_setting")
public class SysSetting extends Model<SysSetting> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String Id;
    /**
     * KEY
     */
    private String sysKey;
    /**
     * 名称
     */
    private String sysName;
    /**
     * 值
     */
    private String sysValue;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 说明
     */
    private String sysDesc;


    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getSysKey() {
        return sysKey;
    }

    public void setSysKey(String sysKey) {
        this.sysKey = sysKey;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysValue() {
        return sysValue;
    }

    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSysDesc() {
        return sysDesc;
    }

    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "SysSetting{" +
        "Id=" + Id +
        ", sysKey=" + sysKey +
        ", sysName=" + sysName +
        ", sysValue=" + sysValue +
        ", sort=" + sort +
        ", sysDesc=" + sysDesc +
        "}";
    }
}
