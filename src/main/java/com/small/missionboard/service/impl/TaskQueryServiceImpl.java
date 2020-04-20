package com.small.missionboard.service.impl;

import com.small.missionboard.service.TaskQueryService;
import com.small.missionboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {
    @Autowired
    TaskService taskService;


}
