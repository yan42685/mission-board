package com.small.missionboard.service;

import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.enums.TaskQueryMethodEnum;
import com.small.missionboard.enums.TaskSortMethodEnum;

import java.util.List;

/**
 * 提供筛选，排序后的任务列表
 */
public interface TaskQueryService {

    /**
     * 按任务的状态查询任务列表
     */
    List<TaskInfo> list(TaskQueryMethodEnum method);

    /**
     * 公共任务列表
     */
    List<TaskInfo> sortedPage(Integer pageNum, Integer size, TaskSortMethodEnum method);

    /**
     * 逆序公共任务列表
     */
    List<TaskInfo> reverseSortedPage(Integer pageNum, Integer size, TaskSortMethodEnum method);

    /**
     * 模糊查询
     */
    List<TaskInfo> sortedPageSearch(Integer pageNum, Integer size, TaskSortMethodEnum method, String fuzzyTitle);

    /**
     * 逆序模糊查询
     */
    List<TaskInfo> reverseSortedPageSearch(Integer pageNum, Integer size, TaskSortMethodEnum method, String fuzzyTitle);

}
