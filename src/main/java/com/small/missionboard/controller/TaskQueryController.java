package com.small.missionboard.controller;

import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.JsonWrapper;
import com.small.missionboard.enums.TaskQueryMethodEnum;
import com.small.missionboard.enums.TaskSortMethodEnum;
import com.small.missionboard.service.TaskQueryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "查询任务")
@RequestMapping("api/task/query")
@RestController
public class TaskQueryController {
    @Autowired
    TaskQueryService taskQueryService;

    @GetMapping("list")
    public JsonWrapper<List<TaskInfo>> list(TaskQueryMethodEnum queryMethod) {
        return new JsonWrapper<>(taskQueryService.list(queryMethod.name()));
    }

    @GetMapping("list/reverse")
    public JsonWrapper<List<TaskInfo>> reverseList(TaskQueryMethodEnum queryMethod) {
        return new JsonWrapper<>(taskQueryService.reverseList(queryMethod.name()));
    }

    @GetMapping("sorted_page")
    public JsonWrapper<List<TaskInfo>> sortedPage(Integer pageNum, Integer size, TaskSortMethodEnum method) {
        return new JsonWrapper<>(taskQueryService.sortedPage(pageNum, size, method.name()));
    }

    @GetMapping("sorted_page/reverse")
    public JsonWrapper<List<TaskInfo>> reverseSortedPage(Integer pageNum, Integer size, TaskSortMethodEnum method) {
        return new JsonWrapper<>(taskQueryService.reverseSortedPage(pageNum, size, method.name()));
    }

}
