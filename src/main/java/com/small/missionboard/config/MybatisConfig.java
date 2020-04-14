package com.small.missionboard.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
@MapperScan(basePackages = "com.small.missionboard.mapper")
public class MybatisConfig {

}
