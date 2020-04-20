package com.small.missionboard.service;

import com.small.missionboard.bean.vo.TaskInfo;

import java.util.List;

/**
 * 提供筛选，排序后的任务列表
 */
public interface TaskQueryService {

    List<TaskInfo> getList(String queryMethod);

    List<TaskInfo> getReverseList(String queryMethod);

}
