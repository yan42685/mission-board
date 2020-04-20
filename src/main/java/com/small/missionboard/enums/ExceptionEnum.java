package com.small.missionboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 已知异常枚举
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    /**
     * 异常码规则：
     * 正数表示内部异常
     * 负数表示外部异常，应由调用者处理
     */
    TO_BE_IMPLEMENTED(50, "该功能还未实现"),
    UNKNOWN_EXCEPTION(99, "服务器未知异常"),
    NOT_REGISTER(-1, "用户未注册"),
    NOT_LOGIN(-2, "用户未登录"),
    NO_PERMISSION(-3, "用户没有足够权限"),

    /**
     * 用户身份校验失败(signature 与 sessionKey 不匹配)
     */
    USER_VERIFICATION_FAILED(-4, "用户身份校验失败"),
    EMPTY_JS_CODE(-5, "jsCode不能为空"),
    WX_LOGIN_FAIL(-6, "微信登录接口调用失败"),
    INVALID_PARAM(-7, "参数校验失败"),
    CURRENT_ACCEPTED_TASKS_OVERFLOW(-8, "同一时间可接受的任务数量达到上限"),
    WRONG_RECEIVER_ID(-9, "错误的接受者ID, 该ID并不在该任务的接收者列表里"),
    DELETE_TASK_FAIL(-10, "只有任务发送者在任务处于 DELIVERED 状态时能删除任务");

    private int errorCode;
    private String errorMsg;


}
