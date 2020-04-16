package com.small.missionboard.service;

public interface UserInfoService {
    /**
     * 修改昵称
     */
    boolean modifyNickname(String token, String newNickname);

    /**
     * 修改性别
     */
    boolean modifyGender(String token, String newGender);

    /**
     * 修改学院 (比如说有转专业的)
     */
    boolean modifyFaculty(String token, String newFaculty);

    /**
     * 修改联系方式
     */
    boolean modifyContactInformation(String token, String newContactInformation);

    /**
     * 修改手机号
     */
    boolean modifyPhoneNumber(String token, String newPhoneNumber);

    /**
     * 修改头像
     */
    boolean modifyAvatarUrl(String token, String newAvatarUrl);

}
