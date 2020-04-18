package com.small.missionboard.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.missionboard.bean.dto.TaskCreateInfo;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.common.SeparatedStringBuilder;
import com.small.missionboard.enums.ExceptionEnum;
import com.small.missionboard.enums.TaskStatusEnum;
import com.small.missionboard.mapper.TaskMapper;
import com.small.missionboard.service.TaskService;
import com.small.missionboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserService userService;

    /**
     * 同一时间可以接受的任务数量
     */
    private static final Integer CURRENT_ACCEPTED_TASKS_MAX = 5;


    @Override
    public TaskInfo create(TaskCreateInfo createInfo) {
        // 把创建新建任务的信息填到UserInfo里并补充一些信息
        TaskInfo taskInfo = new TaskInfo();
        BeanUtil.copyProperties(createInfo, taskInfo);
        User currentUser = userService.getCurrentUser();
        taskInfo.setSenderId(currentUser.getId().toString());
        taskInfo.setStatus(TaskStatusEnum.DELIVERED.getValue());

        Task newTask = new Task();
        BeanUtil.copyProperties(taskInfo, newTask);
        taskMapper.insert(newTask);
        return taskInfo;
    }

    @Override
    public void accept(String taskId, String receiverNotes) {
        User currentUser = userService.getCurrentUser();
        // 不能同时接受过多任务
        if (userService.currentTasksAcceptedCount() >= CURRENT_ACCEPTED_TASKS_MAX) {
            throw new KnownException(ExceptionEnum.CURRENT_ACCEPTED_TASKS_OVERFLOW);
        }

        Task task = taskMapper.selectById(taskId);
        String currentStatus = new SeparatedStringBuilder(task.getStatus())
                // 从公共任务列表消失
                .remove(TaskStatusEnum.DELIVERED)
                // 如果任务不是快速接受模式，就进入接受待确认状态, 否则直接进入进行中状态
                .addIfNot(TaskStatusEnum.ACCEPTED, task.getQuickAccept())
                .addIf(TaskStatusEnum.ONGOING, task.getQuickAccept())
                .build();
        task.setStatus(currentStatus);
        task.setReceiverNotes(receiverNotes);

        task.setReceiverId(currentUser.getId().toString());
        taskMapper.updateById(task);
    }

    @Override
    public void agreeAcceptance(String taskId, String accepterId) {
        Task task = taskMapper.selectById(taskId);
        String currentStatus = new SeparatedStringBuilder(task.getStatus())
                .clearAllAndAdd(TaskStatusEnum.ONGOING)
                .build();
    }

    @Override
    public void submit(String taskId) {
    }

    @Override
    public void confirmSubmit(String taskId) {
    }

    @Override
    public List<String> getStatusList(String taskId) {
        String statusString = taskMapper.selectById(taskId).getStatus();
        return new ArrayList<>(Arrays.asList(statusString.split(SeparatedStringBuilder.SEPARATOR)));
    }

    @Override
    public boolean hasStatus(String taskId, String status) {
        String statusString = taskMapper.selectById(taskId).getStatus();
        return statusString.contains(status);
    }

}
