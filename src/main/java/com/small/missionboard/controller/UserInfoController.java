package com.small.missionboard.controller;

import com.small.missionboard.bean.vo.UserInfo;
import com.small.missionboard.common.JsonWrapper;
import com.small.missionboard.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    // TODO: 把修改信息都整合到一个接口
    @ApiOperation("修改昵称")
    @GetMapping("nickname/set")
    public JsonWrapper<Boolean> modifyNickname(String nickname) {
        return new JsonWrapper<>(userInfoService.modifyNickname(nickname));
    }

    @ApiOperation("修改性别")
    @GetMapping("gender/set")
    public JsonWrapper<Boolean> modifyGender(String gender) {
        return new JsonWrapper<>(userInfoService.modifyGender(gender));
    }

    @ApiOperation("修改学院")
    @GetMapping("faculty/set")
    public JsonWrapper<Boolean> modifyFaculty(String faculty) {
        return new JsonWrapper<>(userInfoService.modifyFaculty(faculty));
    }

    @ApiOperation("修改联系方式")
    @GetMapping("contact_info/set")
    public JsonWrapper<Boolean> modifyContactInformation(String contactInfo) {
        return new JsonWrapper<>(userInfoService.modifyContactInformation(contactInfo));
    }

    @ApiOperation("修改手机号")
    @GetMapping("phone_number/set")
    public JsonWrapper<Boolean> modifyPhoneNumber(String phoneNumber) {
        return new JsonWrapper<>(userInfoService.modifyPhoneNumber(phoneNumber));
    }

    /**
     * TODO: 用 get 请求上传文件就会出现image是null的情况，暂时不知道为什么
     */
    @ApiOperation("修改头像")
    @PostMapping("avatar/set")
    public JsonWrapper<Boolean> modifyAvatarUrl(MultipartFile image) {
        return new JsonWrapper<>(userInfoService.modifyAvatarUrl(image));
    }

    @ApiOperation("获取头像")
    @GetMapping(value = "avatar/get", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getAvatar() {
        return userInfoService.getAvatar();
    }
}
