package com.small.missionboard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.small.missionboard.bean.dto.RegistryInfo;
import com.small.missionboard.bean.entity.User;

public interface UserService extends IService<User> {
    /**
     * 用户登录
     */
    String login(String jsCode);

    /**
     * 用户注册
     */
    String register(String jsCode, RegistryInfo registryInfo);

    /**
     * 获取当前用户对象
     */
    User getCurrentUser();

    /**
     * 获取当前用户 openId
     */
    String getOpenId();
}

