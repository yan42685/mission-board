package com.small.missionboard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.small.missionboard.bean.dto.TaskCreateInfo;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.vo.TaskInfo;

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
    void agreeAcceptance(String accepterId);

    /**
     * 提交任务
     */
    void submit(String taskId);

    /**
     * 确认任务完成
     */
    void confirmSubmit(String taskId);


}
