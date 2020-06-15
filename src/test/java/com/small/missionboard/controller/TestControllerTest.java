package com.small.missionboard.controller;

import com.small.missionboard.bean.dto.WxSession;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.service.UserService;
import com.small.missionboard.util.RedisUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
    @Autowired
    UserService userService;

    @Test
    void reverse() throws Exception {
        // 仅仅用于示例
//        Mockito.when(userService.getCurrentUser()).thenReturn(new User().setCredit(33));
//        mvc.perform(MockMvcRequestBuilders
//                .get("http://localhost:8090/api/reverse")
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .param("str", "12345"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("data", IsEqual.equalTo("54321")))
//        ;


    }

//    @Test
//    void addTestUser() {
//        String[] names = {"token1", "token2", "token3", "token4"};
//        String[] openIds = {"150c47746-e93d-4894-ac40-bda2e484d25d", "1072ae19a-e26a-4ba1-bc02-089f37fda38b", "13958c696-4790-4a67-8cc4-085a9d920d5a", "145031e17-0957-4fdd-bdaf-0ad4f917452c"};
//        for (int i = 0; i < 4; i++) {
//            User user = new User();
//            user.setName(names[i])
//                    .setNickname(names[i])
//                    .setOpenId(openIds[i])
//            ;
//            userService.save(user);
//        }
//    }
}
    