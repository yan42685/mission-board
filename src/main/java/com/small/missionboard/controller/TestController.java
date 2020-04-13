package com.small.missionboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("测试API")
@RequestMapping("api")
@RestController
public class TestController {
    @ApiOperation("测试")
    @RequestMapping("test")
    public String test() {
        return "hello world";
    }

    @ApiOperation(value = "加法运算", notes = "两个整数相加", response = String.class)
    @RequestMapping("add")
    public String add(int a, int b) {
        return String.valueOf(a+b);
    }
}
