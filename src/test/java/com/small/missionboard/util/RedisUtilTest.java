package com.small.missionboard.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisUtilTest {
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
        Assertions.assertNotNull(RedisUtil.get("姓名"));
    }

    @Test
    void set() {
        Assertions.assertTrue(RedisUtil.set("姓名", "张三"));
        RedisUtil.set("姓名", "张三");
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
    