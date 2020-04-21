package com.small.missionboard.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取request及request域的内容
 */
public class RequestUtils {
    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    /**
     * 获取token
     */
    public static String getToken() {
        return getRequest().getParameter("token");
    }
}
