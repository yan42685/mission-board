package com.small.missionboard.controller;

import com.small.missionboard.bean.dto.RegistryInfo;
import com.small.missionboard.bean.vo.JsonWrapper;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户行为")
@Slf4j
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("login")
    public JsonWrapper<String> login(String jsCode) throws Exception {
        if (StringUtils.isBlank(jsCode)) {
            throw new KnownException(JsonWrapper.EMPTY_JS_CODE, "jsCode不能为空");
        }
        String newToken = userService.login(jsCode);
        return new JsonWrapper<>(newToken);
    }

    @GetMapping("register")
    public JsonWrapper<String> register(String jsCode, RegistryInfo registryInfo) {
        String token = userService.register(jsCode, registryInfo);
        return new JsonWrapper<>(token);
    }

}
