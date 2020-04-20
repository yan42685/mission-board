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
    public List<TaskInfo> getList(String queryMethod) {
        return getList(queryMethod, false);
    }

    @Override
    public List<TaskInfo> getReverseList(String queryMethod) {
        return getList(queryMethod, true);
    }

    private List<TaskInfo> getList(String method, boolean reverse) {
        if (EnumUtil.notContains(TaskQueryMethodEnum.class, method)) {
            throw new KnownException(ExceptionEnum.QUERY_METHOD_NOT_EXISTS);
        }
        return null;
    }
}
