package com.hq.financial.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: financial
 * @description: 全局控制器
 * @author: Mr.Huang
 * @create: 2018-10-17 13:38
 **/
@Controller
@RequestMapping("/global")
public class GlobalController {

    /**
     * 跳转到404页面
     */
    @RequestMapping("/error")
    public String pageError(){
        return "/404";
    }
    /**
     * 跳转到session超时页面
     */
    @RequestMapping(path = "/sessionError")
    public String errorPageInfo(Model model) {
        model.addAttribute("tips", "session超时");
        return "/login";
    }

}
