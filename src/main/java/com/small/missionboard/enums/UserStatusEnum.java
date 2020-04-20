package com.small.missionboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 账号状态
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    // 账号正常
    NORMAL("normal"),
    // 账号被封禁
    FORBIDDEN("forbidden"),
    ;
    private String value;
}
