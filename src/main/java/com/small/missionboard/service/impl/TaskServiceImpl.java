package com.small.missionboard.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.missionboard.bean.dto.TaskCreateInfo;
import com.small.missionboard.bean.entity.Task;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.TaskInfo;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.common.SeparatedStringBuilder;
import com.small.missionboard.enums.ExceptionEnum;
import com.small.missionboard.enums.TaskStatusEnum;
import com.small.missionboard.mapper.TaskMapper;
import com.small.missionboard.service.TaskService;
import com.small.missionboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserService userService;

    /**
     * 同一时间可以接受的任务数量
     */
    private static final Integer CURRENT_ACCEPTED_TASKS_MAX = 7;
    /**
     * 判定任务提交后发送者超时未确认的时间界限, 单位: 毫秒
     */
    private static final Long SUBMIT_DURATION_MAX = 1000 * 60 * 24L;

    @Override
    public TaskInfo getInfo(Long taskId) {
        // 根据时间更新任务状态
        Task task = updateStatusByTime(taskId);

        TaskInfo taskInfo = new TaskInfo();
        BeanUtil.copyProperties(task, taskInfo);
        List<String> statusList = new ArrayList<>(Arrays.asList(task.getStatus().split(SeparatedStringBuilder.SEPARATOR)));
        List<String> receiverIdList = new ArrayList<>(Arrays.asList(task.getReceiverId().split(SeparatedStringBuilder.SEPARATOR)));
        taskInfo.setStatusList(statusList)
                .setReceiverIdList(receiverIdList);

        return taskInfo;
    }

    @Override
    public TaskInfo create(TaskCreateInfo createInfo) {
        // 把创建新建任务的信息填到UserInfo里并补充一些信息
        TaskInfo taskInfo = new TaskInfo();
        BeanUtil.copyProperties(createInfo, taskInfo);
        User currentUser = userService.getCurrentUser();
        taskInfo.setSenderId(currentUser.getId().toString())
                .setStatusList(new ArrayList<>(Collections.singleton(TaskStatusEnum.DELIVERED.getValue())));

        Task newTask = new Task();
        BeanUtil.copyProperties(taskInfo, newTask);
        taskMapper.insert(newTask);
        return taskInfo;
    }

    @Override
    public void accept(Long taskId, String receiverNotes) {
        // 不能同时接受过多任务
        if (userService.currentTasksAcceptedCount() >= CURRENT_ACCEPTED_TASKS_MAX) {
            throw new KnownException(ExceptionEnum.CURRENT_ACCEPTED_TASKS_OVERFLOW);
        }

        Task task = taskMapper.selectById(taskId);
        String currentStatus = new SeparatedStringBuilder(task.getStatus())
                // 从公共任务列表消失
                .remove(TaskStatusEnum.DELIVERED)
                // 如果任务不是快速接受模式，就进入接受待确认状态, 否则直接进入进行中状态
                .addIfNot(TaskStatusEnum.ACCEPTED, task.getQuickAccept())
                .addIf(TaskStatusEnum.ONGOING, task.getQuickAccept())
                .build();

        User currentUser = userService.getCurrentUser();
        String currentReceiverIds = new SeparatedStringBuilder(task.getReceiverId())
                .add(currentUser.getId().toString())
                .build();

        task.setStatus(currentStatus)
                .setReceiverId(currentReceiverIds)
                .setReceiverNotes(receiverNotes);
        taskMapper.updateById(task);
    }

    @Override
    public void agreeAcceptance(Long taskId, String receiverId) {
        Task task = taskMapper.selectById(taskId);
        // 接受者必须在该任务的待确认接受者列表里
        if (!task.getReceiverId().contains(receiverId)) {
            throw new KnownException(ExceptionEnum.WRONG_RECEIVER_ID);
        }
        String currentStatus = new SeparatedStringBuilder(task.getStatus())
                .remove(TaskStatusEnum.ACCEPTED)
                .add(TaskStatusEnum.ONGOING)
                .build();
        String currentReceiverId = new SeparatedStringBuilder(task.getReceiverId())
                .clearAllAndAdd(receiverId)
                .build();
        task.setStatus(currentStatus)
                .setReceiverId(currentReceiverId);
        taskMapper.updateById(task);
    }

    @Override
    public void submit(Long taskId, Integer starCount, String comment) {
        Task task = taskMapper.selectById(taskId);
        String currentStatus = new SeparatedStringBuilder(task.getStatus())
                .remove(TaskStatusEnum.ONGOING)
                .remove(TaskStatusEnum.TIMEOUT_NOT_SUBMITTED)
                .add(TaskStatusEnum.TO_BE_CONFIRMED)
                .build();
        task.setStatus(currentStatus)
                .setStarForSender(starCount)
                .setCommentOnSender(comment)
                .setSubmitTime(LocalDateTime.now());
        taskMapper.updateById(task);
    }


    @Override
    public void confirmSubmit(Long taskId, Integer starCount, String comment) {
        Task task = taskMapper.selectById(taskId);
        String currentStatus = new SeparatedStringBuilder(task.getStatus())
                .remove(TaskStatusEnum.TO_BE_CONFIRMED)
                .remove(TaskStatusEnum.TIMEOUT_NOT_CONFIRMED)
                .add(TaskStatusEnum.FINISHED)
                .build();
        task.setStatus(currentStatus)
                .setStarForReceiver(starCount)
                .setCommentOnReceiver(comment);
        taskMapper.updateById(task);
    }

    @Override
    public void cancel(Long taskId) {
        Task task = taskMapper.selectById(taskId);
        String currentStatus = new SeparatedStringBuilder(task.getStatus())
                // 任务回到已发布状态，进入全局任务列表
                .clearAllAndAdd(TaskStatusEnum.DELIVERED)
                .build();
        task.setStatus(currentStatus)
                .setSubmitTime(null);
        taskMapper.updateById(task);
    }

    @Override
    public void delete(Long taskId) {
        Task task = taskMapper.selectById(taskId);
        String currentUserId = userService.getCurrentUser().getId().toString();
        String currentStatus = task.getStatus();
        // 只有任务发送者在任务处于 DELIVERED 状态时能删除任务
        if (!task.getSenderId().equals(currentUserId) || !currentStatus.equals(TaskStatusEnum.DELIVERED.getValue())) {
            throw new KnownException(ExceptionEnum.DELETE_TASK_FAIL);
        }
        taskMapper.deleteById(task);
    }

    @Override
    public List<String> getStatusList(Long taskId) {
        String statusString = taskMapper.selectById(taskId).getStatus();
        return new ArrayList<>(Arrays.asList(statusString.split(SeparatedStringBuilder.SEPARATOR)));
    }

    @Override
    public boolean hasStatus(Long taskId, TaskStatusEnum status) {
        String statusString = taskMapper.selectById(taskId).getStatus();
        return statusString.contains(status.getValue());
    }

    /**
     * 根据提交时间更新任务状态
     */
    private Task updateStatusByTime(Long taskId) {
        Task task = taskMapper.selectById(taskId);
        String statusString = task.getStatus();
        // 判断接受者是否超时未提交
        boolean isTimeoutNotSubmitted = hasStatus(taskId, TaskStatusEnum.ONGOING)
                && Duration.between(task.getDeadline(), LocalDateTime.now()).toMillis() >= 0;
        // 判断发送者是否超时未确认
        long submitDuration = Duration.between(task.getSubmitTime(), LocalDateTime.now()).toMillis();
        boolean isTimeoutNotConfirmed = hasStatus(taskId, TaskStatusEnum.TO_BE_CONFIRMED)
                && submitDuration >= SUBMIT_DURATION_MAX;
        String currentStatus = new SeparatedStringBuilder(statusString)
                .addIf(TaskStatusEnum.TIMEOUT_NOT_SUBMITTED, isTimeoutNotSubmitted)
                .addIf(TaskStatusEnum.TIMEOUT_NOT_CONFIRMED, isTimeoutNotConfirmed)
                .build();
        task.setStatus(currentStatus);
        taskMapper.updateById(task);
        return task;
    }

}
