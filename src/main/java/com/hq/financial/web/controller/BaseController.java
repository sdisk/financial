package com.hq.financial.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hq.financial.base.BaseControllerWarpper;
import com.hq.financial.base.tips.SuccessTip;
import com.hq.financial.common.PageInfoBT;
import com.hq.financial.util.FileUtil;
import com.hq.financial.util.HttpKit;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * @program: financial
 * @description: base
 * @author: Mr.Huang
 * @create: 2018-10-16 15:41
 **/
public class BaseController {

    protected static String SUCCESS  = "SUCCESS";
    protected static String ERROR  = "ERROR";

    protected static String REDIRECT  = "redirect";
    protected static String FORWARD  = "forward";

    protected static SuccessTip SUCCESS_TIP = new SuccessTip();

    protected HttpServletRequest getHttpServletRequest(){
        return HttpKit.getRequest();
    }
    protected HttpServletResponse getHttpServletResponse(){
        return HttpKit.getResponse();
    }
    protected HttpSession getSession(){
        return HttpKit.getRequest().getSession();
    }
    protected HttpSession getSession(boolean flag){
        return HttpKit.getRequest().getSession(flag);
    }
    protected String getPara(String name){
        return HttpKit.getRequest().getParameter(name);
    }
    protected void setAttr(String name, Object value){
        HttpKit.getRequest().setAttribute(name, value);
    }
    protected Integer getSystemInvokeCount(){
        return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
    }
    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
    protected <T>PageInfoBT packForBT(Page<T> page){
        return new PageInfoBT<T>(page);
    }
    /**
     * 包装一个list，让list增加额外属性
     */
    protected Object wrapObject(BaseControllerWarpper wrapper){
        return wrapper.wrap();
    }
    /**
     * 删除cookie
     */
    protected void deleteCookieByName(String cookieName) {
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                this.getHttpServletResponse().addCookie(temp);
            }
        }
    }

    /**
     * 删除所有cookie
     */
    protected void deleteAllCookie() {
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        for (Cookie cookie : cookies) {
            Cookie temp = new Cookie(cookie.getName(), "");
            temp.setMaxAge(0);
            this.getHttpServletResponse().addCookie(temp);
        }
    }
    /**
     * 返回前台文件流
     */
    protected ResponseEntity<byte[]> readerFile(String fileName,String filePath){
        byte [] bytes = FileUtil.toByteArray(fileName);
        return readerFile(fileName, bytes);
    }
    protected ResponseEntity<byte[]> readerFile(String fileName,byte[] fileBytes){
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers , HttpStatus.CREATED);
    }

}
