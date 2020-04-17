package com.small.missionboard.controller;

import com.small.missionboard.bean.vo.UserInfo;
import com.small.missionboard.common.JsonWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Api(tags = "测试API")
@RequestMapping("api")
@RestController
@Validated
public class TestController {

    @ApiOperation("翻转字符串")
    @ApiImplicitParam(name = "str", value = "原始字符串", dataType = "string", required = true)
    @GetMapping("reverse")
    public JsonWrapper<String> reverse(@NotBlank(message = "输入字符串不能为空") String str) {
        return new JsonWrapper<>(new StringBuilder(str).reverse().toString());
    }

    @ApiOperation("返回hello world")
    @PostMapping("test")
    public JsonWrapper<String> test(@Valid @RequestBody UserInfo str) {
        System.out.println(str);
        return new JsonWrapper<>("hello, world");
    }

    @ApiOperation(value = "加法运算", notes = "两个整数相加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "加数", dataType = "int", required = true),
            @ApiImplicitParam(name = "y", value = "被加数", dataType = "int", required = true)
    })
    @GetMapping("add")
    public JsonWrapper<Integer> add(int x, int y) {
        return new JsonWrapper<>(x + y);
    }
}
