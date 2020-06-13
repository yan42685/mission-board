package com.small.missionboard.controller;

import com.small.missionboard.bean.dto.TaskCreateInfo;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.JsonWrapper;
import com.small.missionboard.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "处理任务")
@RequestMapping("api/task")
@Validated
@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @ApiOperation("获取指定任务的信息")
    @ApiImplicitParam(name = "taskId", value = "任务编号")
    @GetMapping("info/get")
    public JsonWrapper<TaskInfo> getInfo(Long taskId) {
        return new JsonWrapper<>(taskService.getInfo(taskId));
    }

    @ApiOperation("创建任务")
    @GetMapping("create")
    public JsonWrapper<Boolean> create(TaskCreateInfo info) {
        return new JsonWrapper<>(taskService.create(info));
    }

    @ApiOperation("接受任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务编号"),
            @ApiImplicitParam(name = "notes", value = "备注")
    })
    @GetMapping("accept")
    public JsonWrapper<String> accept(Long taskId, String notes) {
        taskService.accept(taskId, notes);
        return new JsonWrapper<>("");
    }

    @ApiOperation("同意接受任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务编号"),
            @ApiImplicitParam(name = "receiverId", value = "接受者id")
    })
    @GetMapping("agree_acceptance")
    public JsonWrapper<String> agreeAcceptance(Long taskId, String receiverId) {
        taskService.agreeAcceptance(taskId, receiverId);
        return new JsonWrapper<>("");
    }

    @ApiOperation("提交任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务编号"),
            @ApiImplicitParam(name = "starCount", value = "评价星级", dataType = "int"),
            @ApiImplicitParam(name = "comment", value = "评价内容")
    })
    @GetMapping("submit")
    public JsonWrapper<String> submit(Long taskId, Integer starCount, String comment) {
        taskService.submit(taskId, starCount, comment);
        return new JsonWrapper<>("");
    }

    @ApiOperation("确认提交任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务编号"),
            @ApiImplicitParam(name = "starCount", value = "评价星级", dataType = "int"),
            @ApiImplicitParam(name = "comment", value = "评价内容")
    })
    @GetMapping("confirm_submit")
    public JsonWrapper<String> confirmSubmit(Long taskId, Integer starCount, String comment) {
        taskService.confirmSubmit(taskId, starCount, comment);
        return new JsonWrapper<>("");
    }

    @ApiOperation(value = "取消任务")
    @ApiImplicitParam(name = "taskId", value = "任务编号")
    @GetMapping("cancel")
    public JsonWrapper<String> cancel(Long taskId) {
        taskService.cancel(taskId);
        return new JsonWrapper<>("");
    }

    @ApiOperation(value = "删除任务", notes = "只有出现在公公任务列表的任务可以删除")
    @ApiImplicitParam(name = "taskId", value = "任务编号")
    @GetMapping("delete")
    public JsonWrapper<String> delete(Long taskId) {
        return new JsonWrapper<>("");
    }

// TODO: 搜索功能
}
