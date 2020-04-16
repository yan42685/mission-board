package com.small.missionboard.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.small.missionboard.common.WxConstants;
import com.small.missionboard.util.JsonUtils;
import com.small.missionboard.util.UrlUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    UserServiceImpl userServiceImpl;


    @Test
    void login() {
        Map<String, String> params = new HashMap<>();
        params.put("appid", WxConstants.APP_ID);
        params.put("secret", WxConstants.APP_SECRET);
        params.put("js_code", "test_js_code");
        params.put("grant_type", "authorization_code");
        // 调用微信登录的 API
        String url = UrlUtils.addParameterMap(WxConstants.LOGIN_URL, params);
        String jsonData = restTemplate.getForObject(url, String.class);
        try {
            System.out.println(JsonUtils.json2Object(jsonData));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
    