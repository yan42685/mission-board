package com.small.missionboard.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional
    @Test
    void set() {

        String key = "testKey";
        String value = "testValue";
        RedisUtils.set(key, value);
        Assertions.assertEquals(RedisUtils.get(key), value);
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
    