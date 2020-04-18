package com.small.missionboard.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.missionboard.bean.dto.TaskCreateInfo;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.SeparatedStringBuilder;
import com.small.missionboard.enums.TaskStatusEnum;
import com.small.missionboard.mapper.TaskMapper;
import com.small.missionboard.service.TaskService;
import com.small.missionboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserService userService;

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
        Task task = taskMapper.selectById(taskId);
        String currentStatus = new SeparatedStringBuilder(task.getStatus())
                // 从公共任务列表消失
                .remove(TaskStatusEnum.DELIVERED)
                .add(TaskStatusEnum.ACCEPTED)
                .build();
        task.setStatus(currentStatus);
        task.setReceiverNotes(receiverNotes);

        User currentUser = userService.getCurrentUser();
        task.setReceiverId(currentUser.getId().toString());
        taskMapper.updateById(task);
    }

    @Override
    public void agreeAcceptance(String accepterId) {
    }

    @Override
    public void submit(String taskId) {
    }

    @Override
    public void confirmSubmit(String taskId) {
    }
}
