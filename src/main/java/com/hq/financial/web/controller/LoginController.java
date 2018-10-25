package com.hq.financial.web.controller;

import com.google.code.kaptcha.Constants;
import com.hq.financial.exception.HuiException;
import com.hq.financial.exception.HuiExceptionEnum;
import com.hq.financial.log.LogManager;
import com.hq.financial.log.factory.LogTaskFactory;
import com.hq.financial.shiro.ShiroKit;
import com.hq.financial.shiro.ShiroUser;
import com.hq.financial.util.ToolUtil;
import com.hq.financial.web.service.ISysLogService;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.hq.financial.util.HttpKit.getIp;

/**
 * @program: financial
 * @description: login
 * @author: Mr.Huang
 * @create: 2018-10-18 16:16
 **/
@Controller
public class LoginController extends BaseController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String doLogin(){

        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        String remember = super.getPara("remember");

        //验证验证码是否正确
        String kaptcha = super.getPara("kaptcha").trim();
        String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
            throw new HuiException(HuiExceptionEnum.KAPTCHA_ERROR);
        }
        org.apache.shiro.subject.Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }

        currentUser.login(token);

        ShiroUser shiroUser = ShiroKit.getUser();
        super.getSession().setAttribute("shiroUser", shiroUser);
        super.getSession().setAttribute("username", shiroUser.getAccount());

        LogManager.me().executeLog(LogTaskFactory.bussinessLog(shiroUser.getUserName(),"用户登录", getIp(),""));

        ShiroKit.getSession().setAttribute("sessionFlag", true);

        return REDIRECT + "/";

    }

}
