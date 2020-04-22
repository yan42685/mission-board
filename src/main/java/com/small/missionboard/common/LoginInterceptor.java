package com.small.missionboard.common;

import com.small.missionboard.enums.ExceptionEnum;
import com.small.missionboard.util.RedisUtils;
import com.small.missionboard.util.ServletUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对指定的请求 URL 进行登录验证
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //  如果Redis中没有找到token, 并且调用的不是login或register方法 , 就抛出未登录异常
        String url = request.getRequestURI();
        boolean isLoginOrRegister = url.contains("login") || url.contains("register");
        String token = ServletUtils.getToken();
        if (!RedisUtils.hasKey(token) && !isLoginOrRegister) {
            throw new KnownException(ExceptionEnum.NOT_LOGIN);
        }

        //放行
        return true;
    }

}
