package com.small.missionboard.controller;

import com.small.missionboard.bean.vo.JSONWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试API")
@RequestMapping("api")
@RestController
public class TestController {

    @ApiOperation("翻转字符串")
    @ApiImplicitParam(name = "string",  value = "原始字符串", dataType = "string", required = true)
    @GetMapping("reverse")
    public JSONWrapper<String> reverse(String string) {
        return new JSONWrapper<>(new StringBuilder(string).reverse().toString());
    }

    @ApiOperation("返回hello world")
    @GetMapping("test")
    public JSONWrapper<String> test() {
        return new JSONWrapper<>("hello, world");
    }

    @ApiOperation(value = "加法运算", notes = "两个整数相加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "加数", dataType = "int", required = true),
            @ApiImplicitParam(name = "y", value = "被加数", dataType = "int", required = true)
    })
    @GetMapping("add")
    public JSONWrapper<Integer> add(int x, int y) {
        return new JSONWrapper<>(x + y);
    }
}
