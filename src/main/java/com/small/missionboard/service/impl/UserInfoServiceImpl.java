package com.small.missionboard.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        String openId = userService.getOpenId();
        return userService.update(new UpdateWrapper<User>()
                .eq("open_id", openId)
                .set("nickname", newNickname)
        );
    }

    @Override
    public boolean modifyGender(String newGender) {
        String openId = userService.getOpenId();
        return userService.update(new UpdateWrapper<User>()
                .eq("open_id", openId)
                .set("gender", newGender)
        );
    }

    @Override
    public boolean modifyFaculty(String newFaculty) {
        String openId = userService.getOpenId();
        return userService.update(new UpdateWrapper<User>()
                .eq("open_id", openId)
                .set("faculty", newFaculty)
        );
    }

    @Override
    public boolean modifyContactInformation(String newContactInformation) {
        String openId = userService.getOpenId();
        return userService.update(new UpdateWrapper<User>()
                .eq("open_id", openId)
                .set("contact_information", newContactInformation)
        );
    }

    @Override
    public boolean modifyPhoneNumber(String newPhoneNumber) {
        String openId = userService.getOpenId();
        return userService.update(new UpdateWrapper<User>()
                .eq("open_id", openId)
                .set("phone_number", newPhoneNumber)
        );
    }

    // TODO: 完成文件上传之后再实现这个功能
    @Override
    public boolean modifyAvatarUrl(String newAvatarUrl) {
        return false;
    }
}
