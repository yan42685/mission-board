package com.small.missionboard.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.UserInfo;
import com.small.missionboard.service.UserInfoService;
import com.small.missionboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserService userService;

    @Override
    public UserInfo getCurrentUserInfo() {
        User currentUser = userService.getCurrentUser();
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(currentUser, userInfo);
        return userInfo;
    }

    @Override
    public boolean modifyNickname(String newNickname) {
        User currentUser = userService.getCurrentUser();
        currentUser.setNickname(newNickname);
        return userService.updateById(currentUser);

    }

    @Override
    public boolean modifyGender(String newGender) {
        User currentUser = userService.getCurrentUser();
        currentUser.setGender(newGender);
        return userService.updateById(currentUser);
    }

    @Override
    public boolean modifyFaculty(String newFaculty) {
        User currentUser = userService.getCurrentUser();
        currentUser.setFaculty(newFaculty);
        return userService.updateById(currentUser);
    }

    @Override
    public boolean modifyContactInformation(String newContactInformation) {
        User currentUser = userService.getCurrentUser();
        currentUser.setContactInformation(newContactInformation);
        return userService.updateById(currentUser);
    }

    @Override
    public boolean modifyPhoneNumber(String newPhoneNumber) {
        User currentUser = userService.getCurrentUser();
        currentUser.setPhoneNumber(newPhoneNumber);
        return userService.updateById(currentUser);
    }

    // TODO: 完成文件上传之后再实现这个功能
    @Override
    public boolean modifyAvatarUrl(String newAvatarUrl) {
        return false;
    }
}
