package com.small.missionboard.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.missionboard.bean.dto.TaskCreateInfo;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.TaskInfo;
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
        User currentUser = userService.getCurrentUser();
        // 把创建新建任务的信息填到UserInfo里并补充一些信息
        TaskInfo taskInfo = new TaskInfo();
        BeanUtil.copyProperties(createInfo, taskInfo);
        taskInfo.setSenderId(currentUser.getId());
        taskInfo.setState(TaskStatusEnum.DELIVERED.getValue());

        Task newTask = new Task();
        BeanUtil.copyProperties(taskInfo, newTask);
        taskMapper.insert(newTask);
        return taskInfo;
    }

    @Override
    public Boolean accept(String taskId) {
        return null;
    }

    @Override
    public Boolean agreeAcceptance(String accepterId) {
        return null;
    }

    @Override
    public Boolean submit(String taskId) {
        return null;
    }

    @Override
    public Boolean confirmSubmit(String taskId) {
        return null;
    }
}
