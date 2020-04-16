package com.small.missionboard.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.small.missionboard.bean.dto.WxSession;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.common.WxConstants;
import com.small.missionboard.mapper.UserMapper;
import com.small.missionboard.util.JsonUtils;
import com.small.missionboard.util.RedisUtils;
import com.small.missionboard.util.UrlUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class UserServiceImplTest {
    @Test
    public void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserServiceImpl userServiceImpl;


    @Test
    void login() {
        Map<String, String> params = new HashMap<>();
        params.put("appid", WxConstants.APP_ID);
        params.put("secret", WxConstants.APP_SECRET);
        params.put("js_code", "test_js_code");
        params.put("grant_type", "authorization_code");
        // 调用微信登录的 API
        String url = UrlUtils.addParameterList(WxConstants.LOGIN_URL, params);
        String jsonData = restTemplate.getForObject(url, String.class);
//        System.out.println(jsonData);
        try {
            System.out.println(JsonUtils.json2Object(jsonData));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @Transactional
    @Test
    void getByToken() {
        String token = "TestGetByToken";
        WxSession session = new WxSession("openid", "sessionKey", "unionid");
        // 把session存入Redis
        RedisUtils.set(token, session);
        // 取出session
        WxSession sessionFromRedis = RedisUtils.get(token, WxSession.class);
        Assertions.assertNotNull(sessionFromRedis);

        User testUser = new User();
        testUser.setOpenId(sessionFromRedis.getOpenid());
        testUser.setNickname("testUser999");
        // TODO: 通过下面这行测试
//        userMapper.insert(testUser);
        System.out.println(RedisUtils.get(token, WxSession.class));
        System.out.println();
//        User user = userServiceImpl.getByToken(token);
//        System.out.println(user);


    }
}
    