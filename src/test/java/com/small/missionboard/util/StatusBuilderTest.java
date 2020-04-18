package com.small.missionboard.util;

import com.small.missionboard.common.SeparatedStringBuilder;
import com.small.missionboard.enums.TaskStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatusBuilderTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Test
    void testBuilder() {
        String result = new SeparatedStringBuilder("a,b,c")
                .add(TaskStatusEnum.DELIVERED)
                .add(TaskStatusEnum.ACCEPTED)
                .remove(TaskStatusEnum.ACCEPTED)
                .build();
        Assertions.assertEquals(result, "a,b,c,delivered");
    }


}
    