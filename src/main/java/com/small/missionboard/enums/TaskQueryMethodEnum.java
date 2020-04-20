package com.small.missionboard.enums;

/**
 * 任务列表查询方法
 */
public enum TaskQueryMethodEnum {
    // 随机排序
    RANDOM,
    // 按创建时间排序
    TIME,
    // 按发布人历史完成任务数排序
    TASK_FINISHED_COUNT,
    // 未被接受的任务, 包括超时未接收
    NOT_ACCEPTED,
    // 进行中
    ONGOING,
    // 已完成
    FINISHED,
    // 失败的，即超时未完成
    TIMEOUT_NOT_SUBMITTED;

}
