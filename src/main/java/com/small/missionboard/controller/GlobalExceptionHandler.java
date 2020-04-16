package com.small.missionboard.controller;

import com.small.missionboard.bean.vo.JsonWrapper;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 捕获全局异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理所有的未知异常
     */
    @ExceptionHandler(Exception.class)
    public JsonWrapper<String> handleUnknownException(Exception e, HttpServletRequest request) {
        int errorCode = ExceptionEnum.UNKNOWN_EXCEPTION.getErrorCode();
        String errorMessage = ExceptionEnum.UNKNOWN_EXCEPTION.getErrorMsg();
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), stackTrack);
        return new JsonWrapper<>(errorCode, errorMessage + "\n" + stackTrack);
    }

    /**
     * 处理所有的已知异常
     */
    @ExceptionHandler(KnownException.class)
    public JsonWrapper<String> handleKnownException(KnownException e, HttpServletRequest request) {
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), e.getMessage() + stackTrack);
        return new JsonWrapper<>(e.getErrorCode(), "已知异常: " + e.getMessage() + "\n" + stackTrack);
    }
}
