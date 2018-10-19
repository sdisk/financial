package com.hq.financial.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: financial
 * @description: 自定义Authentication对象
 * @author: Mr.Huang
 * @create: 2018-10-19 10:28
 **/
@Data
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -1239280073883708444L;

    private Integer id; //主键id
    private String account; //账号
    private String userName; //姓名
    private Integer deptId;  //部门id
    private String deptName; //部门名
    private List<Integer> roleList; // 角色id
    private List<String> roleNames; //角色名集
}
