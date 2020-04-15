package com.small.missionboard.controller;

import com.small.missionboard.bean.dto.RegistryInfo;
import com.small.missionboard.bean.vo.JsonWrapper;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("login")
    public String login(String token, String jsCode) throws Exception {
        if (StringUtils.isBlank(jsCode)) {
            throw new KnownException(JsonWrapper.EMPTY_JS_CODE, "jsCode不能为空");
        }
        return userService.login(token, jsCode);
    }

    @GetMapping("register")
    public String register(String jsCode, String signature, String rawData, String encryptedData, String iv, RegistryInfo registryInfo) {
        return userService.register(jsCode, signature, rawData, encryptedData, iv, registryInfo);
    }
}
