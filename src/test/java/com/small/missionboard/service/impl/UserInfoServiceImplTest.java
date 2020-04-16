package com.small.missionboard.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.UserInfo;
import com.small.missionboard.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserInfoServiceImplTest {
    @Test
    public void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    UserService userService;

    @Test
    void getCurrentUserInfo() {
        User user = new User();
        user.setNickname("testName");
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(user, userInfo);
        Assertions.assertEquals(user.getNickname(), userInfo.getNickname());
    }
}
    