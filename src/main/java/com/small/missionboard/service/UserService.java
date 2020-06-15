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
     * 判断是否注册过
     */
    Boolean isRegister(String jsCode);

    /**
     * 获取当前用户对象
     */
    User getCurrentUser();

    /**
     * 获取当前用户 openId
     */
    String getOpenId();

    /**
     * 当前用户正在接受的任务数
     */
    Integer currentTasksAcceptedCount();

    /**
     * 当前用户完成的任务数
     */
    Integer totalTasksFinished();

}

