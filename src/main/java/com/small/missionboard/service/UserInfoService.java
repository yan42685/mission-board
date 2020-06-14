package com.small.missionboard.service;

import com.small.missionboard.bean.dto.ModifiableUserInfo;
import com.small.missionboard.bean.vo.UserInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 对前端开放的用户信息
 */
public interface UserInfoService {

    /**
     * 当前用户信息
     */
    UserInfo getCurrentUserInfo();

    /**
     * 修改用户信息
     */
    boolean modifyUserInfo(ModifiableUserInfo info);

    /**
     * 获取用户信息
     */
    UserInfo getInfoById(Long id);


    /**
     * 修改头像
     */
    boolean modifyAvatarUrl(MultipartFile image);

    /**
     * 下载头像
     */
    byte[] getAvatar();

}
