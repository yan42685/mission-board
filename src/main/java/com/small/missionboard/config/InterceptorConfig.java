package com.small.missionboard.config;

import com.small.missionboard.common.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override

    public void addInterceptors(InterceptorRegistry registry) {
        //TODO 开启拦截器
//        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/**");
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

}
