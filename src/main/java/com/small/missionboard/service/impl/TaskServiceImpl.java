package com.small.missionboard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.missionboard.bean.dto.TaskCreateInfo;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.mapper.TaskMapper;
import com.small.missionboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskInfo create(TaskCreateInfo info) {
        return null;
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
