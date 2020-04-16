package com.small.missionboard.controller;

import com.small.missionboard.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户信息")
@RequestMapping("api/user_info")
@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

//    public JsonWrapper<Boolean> modifyNickname(String newNickname) {
//    }
}
