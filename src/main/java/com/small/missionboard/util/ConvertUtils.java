package com.small.missionboard.util;

import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.SeparatedStringBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * bean 之间的相互转换
 */
public class ConvertUtils {
    public static TaskInfo task2TaskInfo(Task task) {
        TaskInfo taskInfo = new TaskInfo();
        BeanUtils.copyProperties(task, taskInfo);
        List<String> statusList = new ArrayList<>(Arrays.asList(task.getStatus().split(SeparatedStringBuilder.SEPARATOR)));

        String receiverIds = task.getReceiverId();
        List<String> receiverIdList = new ArrayList<>();
        if (receiverIds != null) {
            List<String> idList = Arrays.asList(receiverIds.split(SeparatedStringBuilder.SEPARATOR));
            receiverIdList.addAll(idList);
        }
        taskInfo.setStatusList(statusList)
                .setReceiverIdList(receiverIdList);

        return taskInfo;
    }

    public static List<TaskInfo> task2TaskInfo(List<Task> taskList) {
        List<TaskInfo> taskInfoList = new ArrayList<>(taskList.size());
        taskList.forEach(task -> taskInfoList.add(task2TaskInfo(task)));
        return taskInfoList;
    }
}
