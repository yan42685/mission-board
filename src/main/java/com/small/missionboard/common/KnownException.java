package com.small.missionboard.common;

import lombok.Getter;

@Getter
public class KnownException extends RuntimeException {
    private static final long serialVersionUID = 3413470958629677916L;
    /**
     * 异常代码
     */
    private Integer code;

    public KnownException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
