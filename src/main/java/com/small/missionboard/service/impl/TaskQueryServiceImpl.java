package com.small.missionboard.service.impl;

import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.service.TaskQueryService;
import com.small.missionboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {
    @Autowired
    TaskService taskService;

    @Override
    public List<TaskInfo> sortBy(String method, boolean reverse) {
        return null;
    }
}
