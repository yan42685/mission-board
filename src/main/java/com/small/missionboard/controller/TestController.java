package com.small.missionboard.controller;

import com.small.missionboard.bean.dto.WxSession;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.common.JsonWrapper;
import com.small.missionboard.service.UserService;
import com.small.missionboard.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Api(tags = "测试API")
@RequestMapping("api")
@RestController
@Validated
public class TestController {
    @Autowired
    UserService userService;

    @ApiOperation("翻转字符串")
    @ApiImplicitParam(name = "str", value = "原始字符串", paramType = "query", dataType = "string", required = true)
    @GetMapping("reverse")
    public JsonWrapper<String> reverse(@NotBlank(message = "输入字符串不能为空") String str) {
        return new JsonWrapper<>(new StringBuilder(str).reverse().toString());
    }

    @ApiOperation(value = "加法运算", notes = "两个整数相加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "加数", dataType = "int", required = true),
            @ApiImplicitParam(name = "y", value = "被加数", dataType = "int", required = true)
    })
    @GetMapping("add")
    public JsonWrapper<Integer> add(int x, int y) {
        return new JsonWrapper<>(x + y);
    }

    @ApiOperation(value = "添加测试用户", notes = "可以自定义token 然后作为全局参数传入即可被看作测试账户登录")
    @GetMapping("create_test_user")
    public JsonWrapper<Boolean> createTestUser(String customToken) {
        String randomOpenId = UUID.randomUUID().toString();
        String randomSessionKey = UUID.randomUUID().toString();
        String randomUnionId = UUID.randomUUID().toString();
        WxSession session = new WxSession(randomOpenId, randomSessionKey, randomUnionId);
        User user = new User();
        user.setOpenId(randomOpenId);
        userService.save(user);
        // 负数表示无限期
        RedisUtils.set(customToken, session, -1L);
        return new JsonWrapper<>(true);
    }

    @ApiOperation("临时添加4个测试用户")
    @GetMapping("api/tmp_add_user")
    public JsonWrapper<Boolean> addTestUser2() {
        String[] tokens = {"token1", "token2", "token3", "token4"};
        String[] openIds = {"94e25fb3-6e93-4ece-85f9-0f7c0302e222", "813fd23e-a1c3-4890-bdce-871ddd51ebf0", "e91e3fec-f7cc-4f9a-b8db-9f1420c788e4", "93b708bd-713f-4120-86dd-85b144a88d8f"};
        for (int i = 0; i < 4; i++) {
            String randomSessionKey = UUID.randomUUID().toString();
            String randomUnionId = UUID.randomUUID().toString();
            WxSession session = new WxSession(openIds[i], randomSessionKey, randomUnionId);
            RedisUtils.set(tokens[i], session, -1L);
            Long expire = RedisUtils.getExpire(tokens[i]);
            System.out.println(tokens[i] + " expire time: " + expire + "秒");
        }


        return new JsonWrapper<>(true);
    }


}
