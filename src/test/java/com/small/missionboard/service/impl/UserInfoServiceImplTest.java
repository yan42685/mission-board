package com.small.missionboard.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.UserInfo;
import com.small.missionboard.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserInfoServiceImplTest {
    @Test
    public void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    UserService userService;
    @Autowired
    UserInfoServiceImpl userInfoServiceImpl;

    @Test
    void getCurrentUserInfo() {
        User user = new User();
        user.setNickname("testName");
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(user, userInfo);
        Assertions.assertEquals(user.getNickname(), userInfo.getNickname());
    }

    @Transactional
    @Test
    void modifyFaculty() {
        String faculty = "testFaculty";
        String openId = "12345";
        String newFaculty = "a";
        Long id = 555L;
        User user = new User();
        user.setId(id);
        user.setOpenId(openId);
        user.setFaculty(faculty);
        userService.save(user);
        userService.update(new UpdateWrapper<User>()
                .eq("open_id", openId)
                .set("faculty", newFaculty));
        user = userService.getById(id);
        Assertions.assertEquals(user.getFaculty(), newFaculty);


    }


}
    