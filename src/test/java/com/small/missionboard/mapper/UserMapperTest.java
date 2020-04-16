package com.small.missionboard.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.small.missionboard.bean.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserMapperTest {
    @Test
    public void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }


    @Autowired
    UserMapper userMapper;

    @Test
    void selectByOpenId() {
        User user = userMapper.selectByOpenId("333");
        System.out.println(user);
        Assertions.assertNull(userMapper.selectByOpenId("null"));
    }

    @Transactional
    @Test
    void checkMybatisPlusEnable() {
        User testUser = new User();
        testUser.setOpenId("openid");
        testUser.setNickname("testUser999");

        userMapper.insert(testUser);
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("open_id", "openid"));
        Assertions.assertEquals(user.getOpenId(), "openid");
        System.out.println(user);

    }
}
    