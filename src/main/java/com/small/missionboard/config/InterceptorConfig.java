package com.small.missionboard.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 */
@SpringBootConfiguration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 暂时关闭登录校验
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**");
    }
}
