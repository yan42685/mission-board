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
        RedisUtils.set(customToken, session, 60 * 24 * 365 * 200L);
        return new JsonWrapper<>(true);
    }


}
