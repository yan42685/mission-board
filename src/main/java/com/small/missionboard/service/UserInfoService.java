package com.small.missionboard.service;

import com.small.missionboard.bean.vo.UserInfo;

public interface UserInfoService {

    /**
     * 获取对前端开放的当前用户信息
     */
    UserInfo getCurrentUserInfo();

    /**
     * 修改昵称
     */
    boolean modifyNickname(String newNickname);

    /**
     * 修改性别
     */
    boolean modifyGender(String newGender);

    /**
     * 修改学院 (比如说有转专业的)
     */
    boolean modifyFaculty(String newFaculty);

    /**
     * 修改联系方式
     */
    boolean modifyContactInformation(String newContactInformation);

    /**
     * 修改手机号
     */
    boolean modifyPhoneNumber(String newPhoneNumber);

    /**
     * 修改头像
     */
    boolean modifyAvatarUrl(String newAvatarUrl);

}
