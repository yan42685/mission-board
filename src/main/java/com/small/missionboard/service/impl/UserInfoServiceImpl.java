package com.small.missionboard.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.small.missionboard.bean.dto.ModifiableUserInfo;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.UserInfo;
import com.small.missionboard.service.UserInfoService;
import com.small.missionboard.service.UserService;
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
        BeanUtil.copyProperties(currentUser, userInfo);
        userInfo.setCurrentTasksAccepted(userService.currentTasksAcceptedCount());
        userInfo.setTotalTasksFinished(userService.totalTasksFinished());
        return userInfo;
    }

    @Override
    public boolean modifyUserInfo(ModifiableUserInfo info) {
        User currentUser = userService.getCurrentUser();
        // 禁止source的null值 覆盖target的字段
        BeanUtil.copyProperties(info, currentUser, CopyOptions.create().setIgnoreNullValue(true));
        userService.updateById(currentUser);
        return true;
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
