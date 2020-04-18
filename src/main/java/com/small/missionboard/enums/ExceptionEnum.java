package com.small.missionboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    ;

    private int errorCode;
    private String errorMsg;


}
