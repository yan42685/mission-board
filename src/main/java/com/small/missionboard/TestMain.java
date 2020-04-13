package com.small.missionboard;

import com.small.missionboard.enums.TaskStatusEnum;
import com.small.missionboard.util.StatusUtils;

public class TestMain {
    public static void main(String[] args) {
        int y = 4;
        System.out.println(y);
        test(y);
        System.out.println(y);
        y = StatusUtils.setStatus(y, TaskStatusEnum.TIMEOUT_NOT_CONFIRMED);
        System.out.println(y);


    }

    private static void test(Integer x) {
        x = x + 5;
    }
}
