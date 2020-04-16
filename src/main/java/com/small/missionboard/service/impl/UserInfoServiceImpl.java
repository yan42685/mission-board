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
    public boolean modifyNickname(String token, String newNickname) {
        return false;
    }

    @Override
    public boolean modifyGender(String token, String newGender) {
        return false;
    }

    @Override
    public boolean modifyFaculty(String token, String newFaculty) {
        return false;
    }

    @Override
    public boolean modifyContactInformation(String token, String newContactInformation) {
        return false;
    }

    @Override
    public boolean modifyPhoneNumber(String token, String newPhoneNumber) {
        return false;
    }

    @Override
    public boolean modifyAvatarUrl(String token, String newAvatarUrl) {
        return false;
    }
}
