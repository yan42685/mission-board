package com.small.missionboard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.small.missionboard.bean.dto.TaskCreateInfo;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.vo.TaskInfo;

import java.util.List;

public interface TaskService extends IService<Task> {
    /**
     * 创建任务
     */
    TaskInfo create(TaskCreateInfo info);

    /**
     * 接受任务
     */
    void accept(String taskId, String receiverNotes);

    /**
     * 同意接受任务
     */
    void agreeAcceptance(String taskId, String accepterId);

    /**
     * 提交任务
     */
    void submit(String taskId);

    /**
     * 确认任务完成
     */
    void confirmSubmit(String taskId);

    /**
     * 获取状态列表
     */
    List<String> getStatusList(String taskId);

    /**
     * 判断是否有某个状态
     */
    boolean hasStatus(String taskId, String status);


}
