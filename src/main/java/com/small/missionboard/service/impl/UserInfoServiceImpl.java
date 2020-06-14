package com.small.missionboard.service.impl;

import cn.hutool.core.io.FileUtil;
import com.small.missionboard.bean.dto.ModifiableUserInfo;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.UserInfo;
import com.small.missionboard.service.UserInfoService;
import com.small.missionboard.service.UserService;
import com.small.missionboard.util.BeanUtils;
import com.small.missionboard.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 对前端开放的用户信息
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserService userService;

    @Override
    public UserInfo getCurrentUserInfo() {
        User currentUser = userService.getCurrentUser();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(currentUser, userInfo);
        userInfo.setCurrentTasksAccepted(userService.currentTasksAcceptedCount());
        userInfo.setTotalTasksFinished(userService.totalTasksFinished());
        return userInfo;
    }

    @Override
    public boolean modifyUserInfo(ModifiableUserInfo info) {
        User currentUser = userService.getCurrentUser();
        BeanUtils.copyProperties(info, currentUser);
        userService.updateById(currentUser);
        return true;
    }

    @Override
    public UserInfo getInfoById(Long id) {
        User user = userService.getById(id);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        return userInfo;
    }

    @Override
    public boolean modifyAvatarUrl(MultipartFile image) {
        User currentUser = userService.getCurrentUser();
        String avatarUrl = currentUser.getAvatarUrl();
        // 删除之前的头像
        FileUtil.del(avatarUrl);

        String imagePath = FileUtils.generateImagePath(image);
        FileUtils.store(image, imagePath);
        currentUser.setAvatarUrl(imagePath);
        userService.updateById(currentUser);
        return true;
    }

    @Override
    public byte[] getAvatar() {
        String avatarUrl = userService.getCurrentUser().getAvatarUrl();
        return FileUtils.load(avatarUrl);
    }


}
