package com.hq.financial.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: financial
 * @description: login
 * @author: Mr.Huang
 * @create: 2018-10-18 16:16
 **/
@Controller
public class LoginController {

    @RequestMapping(value = {"", "/", "/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
    //@PostMapping(value = "/login")

}
