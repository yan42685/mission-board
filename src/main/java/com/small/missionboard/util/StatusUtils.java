package com.small.missionboard.util;

import com.small.missionboard.enums.StatusEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatusUtils {
    /**
     * 状态字符串分隔符
     */
    private static final String SEPARATOR = ",";

    /**
     * 判断状态是否存在
     */
    public static Boolean hasStatus(String statusString, StatusEnum target) {
        List<String> statusList = new ArrayList<>(Arrays.asList(statusString.split(SEPARATOR)));
        return statusList.contains(target.getValue());
    }

    /**
     * 添加状态
     */
    public static String addStatus(String statusString, StatusEnum target) {
        List<String> statusList = new ArrayList<>(Arrays.asList(statusString.split(SEPARATOR)));
        if (!statusList.contains(target.getValue())) {
            statusList.add(target.getValue());
        }
        return StringUtils.join(statusList);
    }

    /**
     * 移除状态
     */
    public static String removeStatus(String statusString, StatusEnum target) {
        List<String> statusList = new ArrayList<>(Arrays.asList(statusString.split(SEPARATOR)));
        statusList.remove(target.getValue());
        return StringUtils.join(statusList);
    }

    /**
     * 清除全部状态并添加状态
     */
    public static String clearAllAndAdd(StatusEnum target) {
        return target.getValue();
    }
}
