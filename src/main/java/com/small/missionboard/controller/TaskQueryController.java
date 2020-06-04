package com.small.missionboard.controller;

import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.JsonWrapper;
import com.small.missionboard.enums.TaskQueryMethodEnum;
import com.small.missionboard.enums.TaskSortMethodEnum;
import com.small.missionboard.service.TaskQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "查询个人任务列表", notes = "比如 未接受，进行中，已完成等, 只能用给定的大写英文")
    @ApiImplicitParam(name = "queryMethod", value = "查询方式")
    @GetMapping("list")
    public JsonWrapper<List<TaskInfo>> list(TaskQueryMethodEnum queryMethod) {
        return new JsonWrapper<>(taskQueryService.list(queryMethod));
    }

    @ApiOperation("公共任务列表的分页查询与排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页行数", dataType = "int"),
            @ApiImplicitParam(name = "method", value = "排序方法")
    })
    @GetMapping("sorted_page")
    public JsonWrapper<List<TaskInfo>> sortedPage(Integer pageNum, Integer size, TaskSortMethodEnum method) {
        return new JsonWrapper<>(taskQueryService.sortedPage(pageNum, size, method));
    }

    @ApiOperation("公共任务列表的分页查询与反向排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页行数", dataType = "int"),
            @ApiImplicitParam(name = "method", value = "排序方法")
    })
    @GetMapping("sorted_page/reverse")
    public JsonWrapper<List<TaskInfo>> reverseSortedPage(Integer pageNum, Integer size, TaskSortMethodEnum method) {
        return new JsonWrapper<>(taskQueryService.reverseSortedPage(pageNum, size, method));
    }

    @ApiOperation("模糊查询任务标题")
    @GetMapping("fuzzy")
    public JsonWrapper<List<TaskInfo>> sortedPageSearch(Integer pageNum, Integer size, TaskSortMethodEnum method, String fuzzyTitle) {
        return new JsonWrapper<>(taskQueryService.sortedPageSearch(pageNum, size, method, fuzzyTitle));
    }

    @ApiOperation("逆序模糊查询任务标题")
    @GetMapping("fuzzy/reverse")
    public JsonWrapper<List<TaskInfo>> reverseSortedPageSearch(Integer pageNum, Integer size, TaskSortMethodEnum method, String fuzzyTitle) {
        return new JsonWrapper<>(taskQueryService.reverseSortedPageSearch(pageNum, size, method, fuzzyTitle));
    }

}
