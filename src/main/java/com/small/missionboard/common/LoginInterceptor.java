package com.small.missionboard.common;

import com.small.missionboard.bean.vo.JsonWrapper;
import com.small.missionboard.util.RedisUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对指定的请求 URL 进行登录验证
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //  如果Redis中没有token, 并且没有调用的不是login或register方法 , 就抛出未登录异常
        String url = request.getRequestURI();
        boolean isLoginOrRegister = url.contains("login") || url.contains("register");
        String token = request.getParameter("token");
        if (!RedisUtils.hasKey(token) && !isLoginOrRegister) {
            throw new KnownException(JsonWrapper.NOT_LOGIN, "用户未登录");
        }

        //放行
        return true;
    }

}
