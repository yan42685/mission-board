package com.small.missionboard.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtils {
    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return  ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
    }

    /**
     * 获取token
     */
    public static String getToken() {
        return getRequest().getParameter("token");
    }
}
