package com.small.missionboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    /**
     * 账号状态
     */
    NORMAL("normal"),
    FORBIDDEN("forbidden"),
    ;
    private String value;
}
