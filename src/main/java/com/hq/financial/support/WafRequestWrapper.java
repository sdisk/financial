package com.hq.financial.support;

import com.hq.financial.util.WafKit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: financial
 * @description: Request请求过滤包装
 * @author: Mr.Huang
 * @create: 2018-10-17 09:46
 **/
public class WafRequestWrapper extends HttpServletRequestWrapper {

    private boolean filterXSS = true;
    private boolean filterSQL = true;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public WafRequestWrapper(HttpServletRequest request,boolean filterXSS, boolean filterSQL) {

        super(request);
        this.filterXSS = filterXSS;
        this.filterSQL = filterSQL;
    }
    public WafRequestWrapper(HttpServletRequest request){
        this(request,true,true);
    }

    @Override
    public String [] getParameterValues(String paramter){
        String [] values = super.getParameterValues(paramter);
        if (null == values){
            return null;
        }
        int count = values.length;
        String [] encodeValues = new String[count];
        for (int i = 0; i < count ; i++) {
            encodeValues[i] = filterParamString(values[i]);
        }
        return encodeValues;
    }

    @Override
    public Map getParameterMap(){
        Map<String, String[]> map = super.getParameterMap();
        Map<String, String[]> result = new HashMap<>();
        for (Map.Entry<String, String[]> entry :map.entrySet()){
            result.put(entry.getKey(), filterEntryString(entry.getValue()));
        }
        return result;
    }


    /**
     * @Description参数过滤
     * @param name
     * 				过滤内容
     * @return
     */
    public String getParameter(String paramter){
        return filterParamString(super.getParameter(paramter));
    }
    /**
     * @Description 请求头过滤
     * @param name
     * 				过滤内容
     * @return
     */
    public String getHeader(String header){
        return filterParamString(super.getHeader(header));
    }

    @Override
    public Cookie[] getCookies(){
        Cookie [] cookies = super.getCookies();
        if( null != cookies){
            for (int i = 0, size = cookies.length; i <  size; i++) {
                Cookie cookie = cookies[i];
                cookie.setValue(filterParamString(cookie.getValue()));
            }
        }
        return cookies;
    }
    protected String filterParamString(String raw){
        if(null == raw){
            return null;
        }
        String tempStr = raw;
        if (this.filterXSS){
            tempStr = WafKit.stripXSS(raw);
        }
        if(this.filterSQL){
            tempStr = WafKit.stripSqlInjection(raw);
        }
        return tempStr;
    }
    protected String[] filterEntryString(String[] rawValue) {
        for ( int i = 0 ; i < rawValue.length ; i++ ) {
            rawValue[i] = filterParamString(rawValue[i]);
        }
        return rawValue;
    }
}
