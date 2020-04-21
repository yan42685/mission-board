package com.small.missionboard.service;

import com.small.missionboard.bean.vo.TaskInfo;

import java.util.List;

/**
 * 提供筛选，排序后的任务列表
 */
public interface TaskQueryService {

    /**
     * 按任务的状态查询任务列表
     */
    List<TaskInfo> list(String queryMethod);

    /**
     * 公共任务列表排序
     */
    List<TaskInfo> sortedPage(Integer pageNum, Integer size, String method);

    List<TaskInfo> reverseSortedPage(Integer pageNum, Integer size, String method);
}
