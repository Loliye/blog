package com.mikufans.bloginterface.common.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {
    public static String getIpAddrByRequest(HttpServletRequest request)
    {
        String ip=request.getHeader("x-forwarded-for");
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip))
            ip=request.getHeader("Proxy-Client-Ip");
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip))
            ip=request.getHeader("WL-Proxy-Client-Ip");
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip))
            ip=request.getRemoteAddr();
        return ip;

    }
}
