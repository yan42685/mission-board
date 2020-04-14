package com.small.missionboard.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisUtilsTest {
    @Test
    public void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }


    @Test
    void expire() {
    }

    @Test
    void getExpire() {
    }

    @Test
    void hasKey() {
    }

    @Test
    void del() {
    }

    @Test
    void get() {
        Assertions.assertNotNull(RedisUtils.get("姓名"));
    }

    @Test
    void set() {
        Assertions.assertTrue(RedisUtils.set("姓名", "张三"));
        RedisUtils.set("姓名", "张三");
    }

    @Test
    void testSet() {
    }

    @Test
    void incr() {
    }

    @Test
    void decr() {
    }
}
    