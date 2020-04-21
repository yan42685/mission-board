package com.small.missionboard.util;

import cn.hutool.core.bean.BeanUtil;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.SeparatedStringBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 转换entity与dto
 */
public class ConvertUtils {
    public static TaskInfo task2TaskInfo(Task task) {
        TaskInfo taskInfo = new TaskInfo();
        BeanUtil.copyProperties(task, taskInfo);
        List<String> statusList = new ArrayList<>(Arrays.asList(task.getStatus().split(SeparatedStringBuilder.SEPARATOR)));
        List<String> receiverIdList = new ArrayList<>(Arrays.asList(task.getReceiverId().split(SeparatedStringBuilder.SEPARATOR)));
        taskInfo.setStatusList(statusList)
                .setReceiverIdList(receiverIdList);

        return taskInfo;
    }
}
