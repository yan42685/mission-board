package com.small.missionboard.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class UrlUtilsTest {
    @Test
    public void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }


    @Test
    void addParameterList() {
        String url = "www.douban.com";
        Map<String, String> params = new HashMap<>();
        params.put("id", "12345");
        params.put("name", "abc");
        params.put("token", "xyz");
        Assertions.assertEquals(UrlUtils.addParameterMap(url, params),
                "www.douban.com?name=abc&id=12345&token=xyz");
    }


    @Test
    void addParameter() {
        String url = "www.google.com";
        Assertions.assertEquals(UrlUtils.addParameter(url, "name", "henry"),
                "www.google.com?name=henry");
    }
}
    