package com.small.missionboard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.missionboard.bean.dto.RegistryInfo;
import com.small.missionboard.bean.dto.WxSession;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.common.WxConstants;
import com.small.missionboard.enums.ExceptionEnum;
import com.small.missionboard.enums.TaskStatusEnum;
import com.small.missionboard.mapper.UserMapper;
import com.small.missionboard.service.UserService;
import com.small.missionboard.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    public String login(String jsCode) {
        String token = ServletUtils.getToken();
        WxSession session = callLoginApi(jsCode);
        String openId = session.getOpenid();

        // 用户不存在时需要注册
        if (userMapper.selectByOpenId(openId) == null) {
            throw new KnownException(ExceptionEnum.NOT_REGISTER);
        }

        // 如果已经登录就刷新token过期时间
        if (StringUtils.isNotBlank(token) && RedisUtils.hasKey(token)) {
            RedisUtils.expire(token, LOGIN_EXPIRE_TIME);
        } else { // 未登录就新建一个token
            token = newToken(session);
        }
        return token;
    }

    @Override
    public String register(String jsCode, RegistryInfo registryInfo) {
        WxSession session = callLoginApi(jsCode);
        String openId = session.getOpenid();
        User previousUser = userMapper.selectByOpenId(openId);
        if (previousUser != null) {
            throw new KnownException(ExceptionEnum.ACCOUNT_REGISTERED);
        }

        // 获取并保存用户信息
        User user = new User();
        BeanUtils.copyProperties(registryInfo, user);
        user.setOpenId(session.getOpenid());
        userMapper.insert(user);

        return newToken(session);
    }

    /**
     * 新生成一个随机token
     */
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
        String url = UrlUtils.addParameterMap(WxConstants.LOGIN_URL, params);
        String jsonData = restTemplate.getForObject(url, String.class);
        try {
            return JsonUtils.json2Object(jsonData, WxSession.class);
        } catch (Exception e) {
            throw new KnownException(ExceptionEnum.WX_LOGIN_FAIL);
        }
    }

    @Override
    public User getCurrentUser() {
        String openId = getOpenId();
        return userMapper.selectByOpenId(openId);
    }

    @Override
    public String getOpenId() {
        // 获取当前用户的token
        String token = ServletUtils.getToken();
        if (token == null) {
            throw new KnownException(ExceptionEnum.NOT_LOGIN);
        }
        WxSession session = RedisUtils.get(token, WxSession.class);
        if (session == null) {
            throw new KnownException(ExceptionEnum.NOT_LOGIN);
        }
        return session.getOpenid();
    }

    @Override
    public Integer currentTasksAcceptedCount() {
        User currentUser = getCurrentUser();
        return userMapper.selectCurrentTasksAccepted(currentUser.getId(), TaskStatusEnum.ONGOING.getValue());
    }

    @Override
    public Integer totalTasksFinished() {
        User currentUser = getCurrentUser();
        return userMapper.selectTotalTasksFinished(currentUser.getId(), TaskStatusEnum.FINISHED.getValue());
    }


}
