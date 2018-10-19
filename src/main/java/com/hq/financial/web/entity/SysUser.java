package com.hq.financial.web.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: financial
 * @description: 用户表
 * @author: Mr.Huang
 * @create: 2018-10-17 16:51
 **/
@TableName("h_sys_user")
public class SysUser extends Model<SysUser>{
    private static final long serialVersionUID = 1L;

    public static final int _0 = 0;
    public static final int _1 = 1;

    @TableId(type = IdType.UUID)
    private String id;

    private String userName;

    private String password;

    //1 启用  -1 禁用
    private Integer userState;

    private Date createTime;

    private String userImg;

    private String userDesc;

    private String deptId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
