package com.small.missionboard.controller;

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
    public String login(String jsCode) throws Exception {
        if (StringUtils.isBlank(jsCode)) {
            throw new Exception("jsCode 不能为空");
        }

        return "token";


    }
}
