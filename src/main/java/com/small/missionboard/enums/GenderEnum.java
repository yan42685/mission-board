package com.small.missionboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    /**
     * 性别
     */
    UNKNOWN(0),
    MALE(1),
    FEMALE(2)
    ;

    private int value;
}
