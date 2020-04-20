package com.small.missionboard.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.small.missionboard.bean.vo.TaskInfo;

import java.util.List;

/**
 * 提供筛选，排序后的任务列表
 */
public interface TaskQueryService {

    List<TaskInfo> list(String queryMethod);

    List<TaskInfo> reverseList(String queryMethod);

    List<TaskInfo> reverseSortedPage(Page<TaskInfo> page, String method);

    List<TaskInfo> sortedPage(Page<TaskInfo> page, String method);
}
