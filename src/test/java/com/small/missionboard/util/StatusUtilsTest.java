package com.small.missionboard.util;

import com.small.missionboard.enums.StatusEnum;
import com.small.missionboard.enums.TaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class StatusUtilsTest {
    @Test
    public void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Getter
    @AllArgsConstructor
    enum TestStateEnum implements StatusEnum {
        EIGHT(8),
        THIRTY_TWO(32);
        private int value;
    }

    @Test
    void setStatus() {
        int a = 5;
        Assertions.assertEquals(StatusUtils.setStatus(a, TestStateEnum.EIGHT), a + 8);
    }

    @Test
    void clearStatus() {
        int a = 42;
        Assertions.assertEquals(StatusUtils.clearStatus(a, TestStateEnum.EIGHT), a - 8);
    }

    @Test
    void hasStatus() {
        int a = 37;
        int b = 7;
        Assertions.assertTrue(StatusUtils.hasStatus(a, TestStateEnum.THIRTY_TWO));
        Assertions.assertFalse(StatusUtils.hasStatus(b, TestStateEnum.EIGHT));
    }
}
    