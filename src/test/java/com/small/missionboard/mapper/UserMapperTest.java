package com.small.missionboard.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.small.missionboard.bean.entity.Task;
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
    @Autowired
    TaskMapper taskMapper;

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

    @Transactional
    @Test
    void selectById() {
        User user = new User();
        Long fakeId = 12354L;
        user.setId(fakeId);
        userMapper.insert(user);
        User user1 = userMapper.selectById(fakeId);
        Assertions.assertEquals(user1.getId(), fakeId);
    }


    @Test
    @Transactional
    void selectAcceptedTasksCount() {
        Task task = new Task();
        Long userId = 68594L;
        task.setReceiverId(String.valueOf(userId));
        String status = "abc,def";
        task.setStatus(status);
        taskMapper.insert(task);

        Integer count = userMapper.selectCurrentTasksAccepted(userId, "abc");
        Assertions.assertEquals(1, count);
    }


}
    