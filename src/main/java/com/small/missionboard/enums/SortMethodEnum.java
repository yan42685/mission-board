package com.small.missionboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortMethodEnum implements StringEnum {
    /**
     * 随机顺序
     */
    RANDOM("random"),
    /**
     * 按时间排序
     */
    TIME("time"),


    ;

    private String value;
}
