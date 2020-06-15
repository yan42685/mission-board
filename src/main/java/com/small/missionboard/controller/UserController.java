package com.small.missionboard.controller;

import com.small.missionboard.bean.dto.RegistryInfo;
import com.small.missionboard.common.JsonWrapper;
import com.small.missionboard.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Api(tags = "用户行为")
@RestController
@RequestMapping("api/user")
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("登录")
    @ApiImplicitParam(name = "jsCode", paramType = "query")
    @GetMapping("login")
    public JsonWrapper<String> login(@NotBlank(message = "jsCode不能为空") String jsCode) {
        String newToken = userService.login(jsCode);
        return new JsonWrapper<>(newToken);
    }

    @ApiOperation("注册")
    @ApiImplicitParam(name = "jsCode", paramType = "query")
    @GetMapping("register")
    public JsonWrapper<String> register(@NotBlank(message = "jsCode不能为空") String jsCode, @Validated RegistryInfo registryInfo) {
        String token = userService.register(jsCode, registryInfo);
        return new JsonWrapper<>(token);
    }

    @ApiOperation("判断是否注册")
    @ApiImplicitParam(name = "jsCode", paramType = "query")
    @GetMapping("is_registered")
    public JsonWrapper<Boolean> isRegistered(@NotBlank(message = "jsCode不能为空") String jsCode) {
        return new JsonWrapper<>(userService.isRegister(jsCode));
    }
//  TODO: 举报功能
//  TODO: 发送验证码
}
