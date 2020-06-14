package com.small.missionboard.controller;

import com.small.missionboard.bean.dto.ModifiableUserInfo;
import com.small.missionboard.bean.vo.UserInfo;
import com.small.missionboard.common.JsonWrapper;
import com.small.missionboard.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "用户信息")
@RequestMapping("api/user/info")
@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @ApiOperation("获取当前用户信息")
    @GetMapping("get")
    public JsonWrapper<UserInfo> getCurrentUserInfo() {
        return new JsonWrapper<>(userInfoService.getCurrentUserInfo());
    }

    @ApiOperation("修改用户信息")
    @GetMapping("set")
    public JsonWrapper<Boolean> modifyUserInfo(ModifiableUserInfo info) {
        return new JsonWrapper<>(userInfoService.modifyUserInfo(info));
    }

    @ApiOperation("获取指定用户信息")
    @GetMapping("get/{id}")
    public JsonWrapper<UserInfo> getInfoById(@PathVariable Long id) {
        return new JsonWrapper<>(userInfoService.getInfoById(id));
    }


    // 废弃下两个api，取消相关需求了
//    @ApiOperation("修改头像")
//    @PostMapping("avatar/set")
//    public JsonWrapper<Boolean> modifyAvatarUrl(MultipartFile image) {
//        return new JsonWrapper<>(userInfoService.modifyAvatarUrl(image));
//    }
//
//    @ApiOperation("获取头像")
//    @GetMapping(value = "avatar/get", produces = MediaType.IMAGE_PNG_VALUE)
//    public byte[] getAvatar() {
//        return userInfoService.getAvatar();
//    }
}
