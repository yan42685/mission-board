package com.small.missionboard.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 因为任务可能存在多个状态，所以用位运算表示其状态
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum implements StatusEnum {

    // 已发出
    DELIVERED(1),
    // 已被接受 (仍需要等待发送者同意接受者去做任务)
    ACCEPTED(2),
    // 进行中
    ONGOING(4),
    // 等待结算 (即等待发送者评价)
    EIGHT(8),
    // 超时未完成
    TIMEOUT_NOT_FINISHED(16),
    // 超时未确认
    THIRTY_TWO(32),
    // 已完成
    FINISHED(64),
    ;

    private int value;
}
