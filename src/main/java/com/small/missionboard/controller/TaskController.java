package com.small.missionboard.controller;

import com.small.missionboard.bean.dto.TaskCreateInfo;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.JsonWrapper;
import com.small.missionboard.service.TaskService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "处理任务")
@RequestMapping("api/task")
@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("info/get")
    public JsonWrapper<TaskInfo> getInfo(Long taskId) {
        return new JsonWrapper<>(taskService.getInfo(taskId));
    }

    public JsonWrapper<TaskInfo> create(TaskCreateInfo info) {
        return new JsonWrapper<>(taskService.create(info));
    }

    public JsonWrapper<String> accept(Long taskId, String receiverNotes) {
        taskService.accept(taskId, receiverNotes);
        return new JsonWrapper<>("");
    }

    public JsonWrapper<String> agreeAcceptance(Long taskId, String receiverId) {
        taskService.agreeAcceptance(taskId, receiverId);
        return new JsonWrapper<>("");
    }

    public JsonWrapper<String> submit(Long taskId) {
        taskService.submit(taskId);
        return new JsonWrapper<>("");
    }

    public JsonWrapper<String> confirmSubmit(Long taskId) {
        taskService.confirmSubmit(taskId);
        return new JsonWrapper<>("");
    }

    public JsonWrapper<String> cancel(Long taskId) {
        taskService.cancel(taskId);
        return new JsonWrapper<>("");
    }

    public JsonWrapper<String> delete(Long taskId) {
        return new JsonWrapper<>("");
    }


}
