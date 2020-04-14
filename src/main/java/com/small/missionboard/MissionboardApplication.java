package com.small.missionboard;

import com.small.missionboard.util.RedisUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MissionboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MissionboardApplication.class, args);
        System.out.println(RedisUtils.get("test1"));
    }

}
