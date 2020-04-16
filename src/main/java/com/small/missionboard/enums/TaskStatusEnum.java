package com.small.missionboard.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 因为任务可能存在多个状态，所以用位运算表示其状态
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {

    // 已发出
    DELIVERED("delivered"),
    // 已被接受 (仍需要等待发送者同意接受者去做任务)
    ACCEPTED("accepted"),
    // 进行中
    ONGOING("ongoing"),
    // 等待结算 (即等待发送者评价)
    TO_BE_CONFIRMED("to_be_confirmed"),
    // 超时未完成
    TIMEOUT_NOT_FINISHED("timeout_not_finished"),
    // 超时未确认
    TIMEOUT_NOT_CONFIRMED("timeout_not_confirmed"),
    // 已完成
    FINISHED("finished"),
    ;

    private String value;
}
