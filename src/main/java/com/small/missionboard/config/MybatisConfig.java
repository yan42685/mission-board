package com.small.missionboard.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@MapperScan(basePackages = "com.small.missionboard.mapper")
public class MybatisConfig {
    /**
     * mybatis-plus分页插件, 只要mapper第一个参数是MP提供的 Page<T> 类型就自动进行物理分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
