package com.small.missionboard.controller;

import com.small.missionboard.bean.entity.User;
import com.small.missionboard.service.UserService;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
        // 自动配置 MockMvc
class TestControllerTest {
    @Test
    public void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    MockMvc mvc;
    @MockBean  // 把该mock的userService注入到TestController并替换原来的userService  同理DAO也可以mock
            UserService userService;

    @Test
    void reverse() throws Exception {
        // 仅仅用于示例
        Mockito.when(userService.getCurrentUser()).thenReturn(new User().setCredit(33));
        mvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8090/api/reverse")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("str", "12345"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("data", IsEqual.equalTo("54321")))
        ;


    }
}
    