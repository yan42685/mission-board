package com.small.missionboard.bean.vo;


import lombok.Data;

@Data
public class JSONWrapper<T> {

    private static final long serialVersionUID = 1L;
    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_STRING = "success";

    public static final int UNKNOWN_EXCEPTION = 99;
    public static final int NOT_LOGIN = -1;
    public static final int NO_PERMISSION = -2;

    /**
     * 状态码  == 0 成功
     *        > 0 服务器内部异常
     *        < 0 外部异常，由调用方处理
     */
    private int code;

    /**
     * 返回API调用情况
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    private JSONWrapper(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JSONWrapper(int code, String msg) {
        this(code, msg, null);
    }

    public JSONWrapper(T data) {
        this(SUCCESS_CODE, SUCCESS_STRING, data);
    }
}