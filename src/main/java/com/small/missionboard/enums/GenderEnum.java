package com.small.missionboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    /**
     * 性别
     */
    UNKNOWN("未知"),
    MALE("男"),
    FEMALE("女");

    private String value;
}
