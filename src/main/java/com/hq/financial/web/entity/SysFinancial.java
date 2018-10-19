package com.hq.financial.web.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author qiang.H
 * @since 2018-10-18
 */
@TableName("sys_financial")
public class SysFinancial extends Model<SysFinancial> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    /**
     * 1 支出 2 收入
     */
    private String type;
    /**
     * 交易类型（1：现金 2：花呗 3：储蓄卡  4：信用卡）
     */
    private String tradeType;
    private Double money;
    private String userId;
    private Date tradeDate;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysFinancial{" +
        "id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", tradeType=" + tradeType +
        ", money=" + money +
        ", userId=" + userId +
        ", tradeDate=" + tradeDate +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", modifyUser=" + modifyUser +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
