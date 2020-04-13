package com.small.missionboard.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试API")
@RequestMapping("api")
@RestController
public class TestController {
    @ApiOperation("返回hello world")
    @PostMapping("test")
    public String test() {
        return "hello world";
    }

    @ApiOperation(value = "加法运算", notes = "两个整数相加", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "a", dataType = "int", value = "加数", required = true, defaultValue = ""),
            @ApiImplicitParam(paramType = "body", name = "b", dataType = "int", value = "被加数", required = true, defaultValue = "")
    })
    @PostMapping("add")
    public String add(int a, int b) {
        return String.valueOf(a + b);
    }
}
