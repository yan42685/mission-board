package com.small.missionboard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.missionboard.bean.dto.RegistryInfo;
import com.small.missionboard.bean.dto.WxSession;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.JsonWrapper;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.common.WxConstants;
import com.small.missionboard.mapper.UserMapper;
import com.small.missionboard.service.UserService;
import com.small.missionboard.util.JsonUtils;
import com.small.missionboard.util.RedisUtils;
import com.small.missionboard.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserMapper userMapper;
    /**
     * 登录态过期时间 5 小时
     */
    private static final Long LOGIN_EXPIRE_TIME = 60 * 60 * 5L;

    @Override
    public String login(String token, String jsCode) {
        WxSession session = callLoginApi(jsCode);
        String openId = session.getOpenid();

        // 用户不存在时需要注册
        if (userMapper.selectByOpenId(openId) == null) {
            throw new KnownException(JsonWrapper.NOT_REGISTER, "用户未注册");
        }

        // 如果已经登录就刷新过期时间
        if (RedisUtils.hasKey(token)) {
            RedisUtils.expire(token, LOGIN_EXPIRE_TIME);
            return token;
        }

        return newToken(session);
    }

    @Override
    public String register(String jsCode, RegistryInfo registryInfo) {
        WxSession session = callLoginApi(jsCode);

        // 获取并保存用户信息
        User user = new User();
        user.setName(registryInfo.getName());
        user.setFaculty(registryInfo.getFaculty());
        user.setPhoneNumber(registryInfo.getPhoneNumber());
        user.setStudentNumber(registryInfo.getStudentNumber());
        user.setOpenId(session.getOpenid());
        userMapper.insert(user);

        return newToken(session);
    }

    private String newToken(WxSession session) {
        //  把登录状态保存到redis
        String newToken = UUID.randomUUID().toString();
        RedisUtils.set(newToken, session, LOGIN_EXPIRE_TIME);
        return newToken;
    }

    /**
     * 调用微信的登录接口
     */
    private WxSession callLoginApi(String jsCode) {
        Map<String, String> params = new HashMap<>(4);
        params.put("appid", WxConstants.APP_ID);
        params.put("secret", WxConstants.APP_SECRET);
        params.put("js_code", jsCode);
        params.put("grant_type", WxConstants.LOGIN_GRANT_TYPE);
        String url = UrlUtils.addParameterList(WxConstants.LOGIN_URL, params);
        String jsonData = restTemplate.getForObject(url, String.class);
        try {
            return JsonUtils.json2Object(jsonData, WxSession.class);
        } catch (Exception e) {
            String errorMessage = "微信登录接口调用失败: " + jsonData;
            log.error(errorMessage);
            throw new KnownException(JsonWrapper.WX_LOGIN_FAIL, errorMessage);
        }
    }
}
