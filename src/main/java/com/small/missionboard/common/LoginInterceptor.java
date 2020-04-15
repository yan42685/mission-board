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
        //  如果Redis中没有token, 并且没有调用的不是login方法(没有传来jsCode) , 就抛出未登录异常
        if (RedisUtils.get(request.getParameter("token")) == null && request.getParameter("jsCode") == null) {
            throw new KnownException(JsonWrapper.NOT_LOGIN, "用户未登录");
        }

        //放行
        return true;
    }

}
