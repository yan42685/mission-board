package com.small.missionboard.service.impl;

import cn.hutool.core.util.EnumUtil;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.enums.ExceptionEnum;
import com.small.missionboard.enums.TaskQueryMethodEnum;
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
    public List<TaskInfo> list(String queryMethod) {
        return list(queryMethod, false);
    }

    @Override
    public List<TaskInfo> reverseList(String queryMethod) {
        return list(queryMethod, true);
    }

    private List<TaskInfo> list(String method, boolean reverse) {
        if (EnumUtil.notContains(TaskQueryMethodEnum.class, method)) {
            throw new KnownException(ExceptionEnum.QUERY_METHOD_NOT_EXISTS);
        }
        return null;
    }

//    private List<TaskInfo>
}
