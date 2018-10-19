package com.hq.financial.web.controller;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @program: financial
 * @description: 错误视图页面 404
 * @author: Mr.Huang
 * @create: 2018-10-16 14:38
 **/
public class ErrorView implements View {

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/global/error").forward(request, response);
    }
}
