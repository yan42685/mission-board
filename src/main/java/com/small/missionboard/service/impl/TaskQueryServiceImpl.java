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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {
    @Autowired
    TaskService taskService;
    @Autowired
    TaskMapper taskMapper;

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
        return null;
    }


    private List<Task> sortByTimePage(Page<Task> page, boolean reverse) {
        String flag = reverse ? "reverse" : null;
        return taskMapper.sortByTimePage(page, flag);
    }

    private List<Task> sortByTaskFinishedCountPage(Page<Task> page, boolean reverse) {
        return null;
    }

    private List<Task> notAcceptedList() {
        return null;
    }

    private List<Task> ongoingList() {
        return null;
    }

    private List<Task> finishedList() {
        return null;
    }

    private List<Task> timeoutNotSubmittedList() {
        return null;
    }

}
