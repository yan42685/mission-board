package com.small.missionboard.util;

import com.small.missionboard.enums.TaskStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class StatusUtilsTest {
    @Test
    public void checkTestEnvironment() {
        assert true;  // 应该总是返回true, 排除测试环境的问题
    }


    @Test
    void setStatus() {
        int a = 5;
        Assertions.assertEquals(TaskStatusEnum.TO_BE_CONFIRMED.getValue(), 8);
        Assertions.assertEquals(StatusUtils.setStatus(a, TaskStatusEnum.TO_BE_CONFIRMED), a + 8);
    }

    @Test
    void clearStatus() {
        int a = 42;
        Assertions.assertEquals(TaskStatusEnum.TO_BE_CONFIRMED.getValue(), 8);
        Assertions.assertEquals(StatusUtils.clearStatus(a, TaskStatusEnum.TO_BE_CONFIRMED), a - 8);
    }

    @Test
    void hasStatus() {
        int a = 37;
        int b = 7;
        Assertions.assertEquals(TaskStatusEnum.TIMEOUT_NOT_CONFIRMED.getValue(), 32);
        Assertions.assertEquals(TaskStatusEnum.TO_BE_CONFIRMED.getValue(), 8);
        Assertions.assertTrue(StatusUtils.hasStatus(a, TaskStatusEnum.TIMEOUT_NOT_CONFIRMED));
        Assertions.assertFalse(StatusUtils.hasStatus(b, TaskStatusEnum.TO_BE_CONFIRMED));
    }
}
    