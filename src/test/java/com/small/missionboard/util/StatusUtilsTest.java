package com.small.missionboard.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatusUtilsTest {
    @Test
    public void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }


}
    