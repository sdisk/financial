package com.hq.financial.common.factory;

import com.baomidou.mybatisplus.plugins.Page;
import com.hq.financial.common.enums.Order;
import com.hq.financial.util.HttpKit;
import com.hq.financial.util.ToolUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: financial
 * @description: 常量工厂
 * @author: Mr.Huang
 * @create: 2018-10-19 16:44
 **/
public class PageFactory<T>{
    public Page<T> defaultPage(){
       HttpServletRequest request =  HttpKit.getRequest();
       int limit = Integer.valueOf(request.getParameter("limit"));
       int offset = Integer.valueOf(request.getParameter("offset"));
       String sort = request.getParameter("sort");
       String order = request.getParameter("order");
       if (ToolUtil.isEmpty(sort)){
            Page<T> page = new Page<>((offset/ limit+ 1), limit);
            page.setOpenSort(false);
            return page;
       } else{
           Page<T> page = new Page<>((offset/ limit+ 1), limit);
           if (Order.ASC.equals(order)){
                page.setAsc(true);
           } else {
                page.setAsc(false);
           }
           return page;
       }
    }
}
