package com.small.missionboard.service.impl;

import cn.hutool.core.util.EnumUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.enums.ExceptionEnum;
import com.small.missionboard.enums.TaskQueryMethodEnum;
import com.small.missionboard.enums.TaskSortMethodEnum;
import com.small.missionboard.mapper.TaskMapper;
import com.small.missionboard.service.TaskQueryService;
import com.small.missionboard.service.TaskService;
import com.small.missionboard.service.UserService;
import com.small.missionboard.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提供筛选，排序后的任务列表
 */
@Service
public class TaskQueryServiceImpl implements TaskQueryService {
    @Autowired
    TaskService taskService;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserService userService;

    @Override
    public List<TaskInfo> list(String method) {
        if (EnumUtil.notContains(TaskQueryMethodEnum.class, method)) {
            throw new KnownException(ExceptionEnum.QUERY_METHOD_NOT_EXISTS);
        }
        List<Task> taskList;
        String currentUserId = userService.getCurrentUser().getId().toString();
        TaskQueryMethodEnum queryMethod = TaskQueryMethodEnum.valueOf(method.toUpperCase());
        // 根据传入的查询方法进行查询
        switch (queryMethod) {
            case NOT_ACCEPTED:
                taskList = taskMapper.notAcceptedList(currentUserId);
                break;
            case ONGOING:
                taskList = taskMapper.ongoingList(currentUserId);
                break;
            case FINISHED:
                taskList = taskMapper.finishedList(currentUserId);
                break;
            case TIMEOUT_NOT_SUBMITTED:
                taskList = taskMapper.timeoutNotSubmittedList(currentUserId);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + queryMethod);
        }
        return ConvertUtils.task2TaskInfo(taskList);
    }

    @Override
    public List<TaskInfo> sortedPage(Integer pageNum, Integer size, String method) {
        return sortedPage(pageNum, size, method, false);
    }

    @Override
    public List<TaskInfo> reverseSortedPage(Integer pageNum, Integer size, String method) {
        return sortedPage(pageNum, size, method, true);
    }

    private List<TaskInfo> sortedPage(Integer pageNum, Integer size, String method, boolean reverse) {
        if (EnumUtil.notContains(TaskSortMethodEnum.class, method)) {
            throw new KnownException(ExceptionEnum.QUERY_METHOD_NOT_EXISTS);
        }
        List<Task> taskList;
        String reverseFlag = reverse ? "reverse" : null;
        TaskSortMethodEnum sortMethod = TaskSortMethodEnum.valueOf(method.toUpperCase());
        switch (sortMethod) {
            case TIME:
                taskList = taskMapper.sortByTimePage(new Page<>(pageNum, size), reverseFlag);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }


        return ConvertUtils.task2TaskInfo(taskList);
    }
}
