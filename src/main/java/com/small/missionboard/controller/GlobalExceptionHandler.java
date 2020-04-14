package com.small.missionboard.controller;

import com.small.missionboard.bean.vo.JsonWrapper;
import com.small.missionboard.common.KnownException;
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
    @ExceptionHandler(Exception.class)
    public JsonWrapper handleUnknownException(Exception e, HttpServletRequest request) {
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), stackTrack);
        return new JsonWrapper(JsonWrapper.UNKNOWN_EXCEPTION, "服务器未知异常: " + stackTrack);
    }

    @ExceptionHandler(KnownException.class)
    public JsonWrapper handleKnownException(KnownException e, HttpServletRequest request) {
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), e.getMessage() + stackTrack);
        return new JsonWrapper(e.getCode(), "已知异常: " + e.getMessage());
    }
}
