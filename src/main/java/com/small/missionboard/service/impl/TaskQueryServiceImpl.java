package com.small.missionboard.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.vo.TaskInfo;
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
    public List<TaskInfo> list(TaskQueryMethodEnum method) {
        List<Task> taskList;
        String currentUserId = userService.getCurrentUser().getId().toString();
        // 根据传入的查询方法进行查询
        switch (method) {
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
                throw new IllegalStateException("Unexpected value: " + method);
        }
        return ConvertUtils.task2TaskInfo(taskList);
    }

    @Override
    public List<TaskInfo> sortedPage(Integer pageNum, Integer size, TaskSortMethodEnum method) {
        return sortedPage(pageNum, size, method, null, false);
    }

    @Override
    public List<TaskInfo> reverseSortedPage(Integer pageNum, Integer size, TaskSortMethodEnum method) {
        return sortedPage(pageNum, size, method, null, true);
    }

    @Override
    public List<TaskInfo> sortedPageSearch(Integer pageNum, Integer size, TaskSortMethodEnum method, String fuzzyTitle) {
        return sortedPage(pageNum, size, method, fuzzyTitle, false);
    }

    @Override
    public List<TaskInfo> reverseSortedPageSearch(Integer pageNum, Integer size, TaskSortMethodEnum method, String fuzzyTitle) {
        return sortedPage(pageNum, size, method, fuzzyTitle, true);
    }

    private List<TaskInfo> sortedPage(Integer pageNum, Integer size, TaskSortMethodEnum sortMethod, String fuzzyTitle, boolean reverse) {
        List<Task> taskList;
        String reverseFlag = reverse ? "reverse" : null;
        Page<Task> page = new Page<>(pageNum, size);
        switch (sortMethod) {
            case TIME:
                taskList = taskMapper.sortByTimePage(page, fuzzyTitle, reverseFlag);
                break;
            case SENDER_CREDIT:
                taskList = taskMapper.sortBySenderCredit(page, fuzzyTitle, reverseFlag);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sortMethod);
        }
        return ConvertUtils.task2TaskInfo(taskList);
    }
}
