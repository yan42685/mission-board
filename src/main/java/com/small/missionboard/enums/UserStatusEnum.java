package com.small.missionboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 账号状态
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    NORMAL("normal"),
    FORBIDDEN("forbidden"),
    ;
    private String value;
}
