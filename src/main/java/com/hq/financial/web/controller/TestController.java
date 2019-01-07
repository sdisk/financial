package com.hq.financial.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: financial
 * @description:
 * @author: Mr.Huang
 * @create: 2019-01-07 15:44
 **/
@Controller
@RequestMapping("/")
@Slf4j
public class TestController extends BaseController{

    @RequestMapping(value = "getSessionId")
    @ResponseBody
    public String getSessionId(){
        Object obj = super.getSession().getAttribute("springboot");
        HttpServletRequest request = super.getHttpServletRequest();
        if (null == obj ){
            obj =  "spring boot有端口"+ request.getLocalPort()+ "生成";
            super.getSession().setAttribute("springboot", obj);
        }
        return "端口="+ request.getLocalPort()+" sessionId=" + super.getSession().getId()+"<br/>"+obj;
    }

    @RequestMapping("/putSession")
    public @ResponseBody String putSession(){
        HttpSession session = super.getSession();
        log.info(session.getClass().getName());
        log.info(session.getId());
        String name = "gonghui";
        session.setAttribute("user", name);
        return "hey,"+ name;
    }
}
