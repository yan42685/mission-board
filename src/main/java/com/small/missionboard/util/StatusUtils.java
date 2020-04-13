package com.small.missionboard.util;

import com.small.missionboard.enums.StatusEnum;

public class StatusUtils {
    public static Integer setStatus(Integer currentStatus, StatusEnum status) {
        return currentStatus | status.getValue();
    }

    public static Integer clearStatus(Integer currentStatus, StatusEnum status) {
        return currentStatus & (~status.getValue());
    }

    public static boolean hasStatus(Integer currentStatus, StatusEnum status) {
        int maskValue = currentStatus & status.getValue();
        return (maskValue & status.getValue()) != 0;
    }

}
