package com.small.missionboard.service;

import com.small.missionboard.bean.vo.TaskInfo;

import java.util.List;

/**
 * 提供筛选，排序后的任务列表
 */
public interface TaskQueryService {

    List<TaskInfo> list(String queryMethod);

    List<TaskInfo> reverseList(String queryMethod);

    List<TaskInfo> reverseSortedPage(Integer pageNum, Integer size, String method);

    List<TaskInfo> sortedPage(Integer pageNum, Integer size, String method);
}
