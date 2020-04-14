package com.small.missionboard.controller;
import com.small.missionboard.bean.vo.JSONWrapper;
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
    public JSONWrapper handleException(Exception e, HttpServletRequest request) {
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), stackTrack);
        return new JSONWrapper(JSONWrapper.UNKNOWN_EXCEPTION, "服务器未知异常: " + stackTrack);
    }
}