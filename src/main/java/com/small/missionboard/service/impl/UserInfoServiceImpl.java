package com.small.missionboard.service.impl;

import com.small.missionboard.service.UserInfoService;
import com.small.missionboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserService userService;

    @Override
    public boolean modifyNickname(String newNickname) {
        return false;
    }

    @Override
    public boolean modifyGender(String newGender) {
        return false;
    }

    @Override
    public boolean modifyFaculty(String newFaculty) {
        return false;
    }

    @Override
    public boolean modifyContactInformation(String newContactInformation) {
        return false;
    }

    @Override
    public boolean modifyPhoneNumber(String newPhoneNumber) {
        return false;
    }

    // TODO: 完成文件上传之后再实现这个功能
    @Override
    public boolean modifyAvatarUrl(String newAvatarUrl) {
        return false;
    }
}
