package com.small.missionboard.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务状态枚举
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum implements StringEnum {

    // 已发出
    DELIVERED("delivered"),
    // 已被接受 (仍需要等待发送者同意接受者去做任务)
    ACCEPTED("accepted"),
    // 进行中
    ONGOING("ongoing"),
    // 等待结算 (即等待发送者评价)
    TO_BE_CONFIRMED("to_be_confirmed"),
    // 超时未提交
    TIMEOUT_NOT_SUBMITTED("timeout_not_submitted"),
    // 超时未确认
    TIMEOUT_NOT_CONFIRMED("timeout_not_confirmed"),
    // 超时未接收
    TIMEOUT_NOT_ACCEPTED("timeout_not_accepted"),
    // 已完成
    FINISHED("finished"),
    ;

    private String value;
}
