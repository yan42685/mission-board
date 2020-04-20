package com.small.missionboard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.small.missionboard.bean.dto.TaskCreateInfo;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.enums.TaskStatusEnum;

import java.util.List;

public interface TaskService extends IService<Task> {
    /**
     * 获取任务信息
     */
    TaskInfo getInfo(Long taskId);

    /**
     * 创建任务
     */
    TaskInfo create(TaskCreateInfo info);

    /**
     * 接受任务
     */
    void accept(Long taskId, String receiverNotes);

    /**
     * 同意接受者接受任务
     */
    void agreeAcceptance(Long taskId, String receiverId);

    /**
     * 提交任务
     */
    void submit(Long taskId, Integer starCount, String comment);

    /**
     * 确认任务完成
     */
    void confirmSubmit(Long taskId, Integer starCount, String comment);

    /**
     * 任务被取消，重新进入公共任务列表
     */
    void cancel(Long taskId);

    /**
     * 永久删除这个任务
     */
    void delete(Long taskId);

    /**
     * 获取状态列表
     */
    List<String> getStatusList(Long taskId);

    /**
     * 判断是否有某个状态
     */
    boolean hasStatus(Long taskId, TaskStatusEnum status);


}
