package com.small.missionboard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.missionboard.bean.dto.WxSession;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.constant.WxConstants;
import com.small.missionboard.mapper.UserMapper;
import com.small.missionboard.service.UserService;
import com.small.missionboard.util.JsonUtils;
import com.small.missionboard.util.RedisUtils;
import com.small.missionboard.util.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 登录态过期时间 5 小时
     */
    private static final Long LOGIN_EXPIRE_TIME = 60 * 60 * 5L;

    public String login(String token, String jsCode, String encryptedData, String iv) {


//        // 如果已经登录就刷新过期时间
//        if (RedisUtils.hasKey(token)) {
//            RedisUtils.expire(token, LOGIN_EXPIRE_TIME);
//            return token;
//        }
//
//        WxSession session = callLoginApi(jsCode);
//        String openId = session.getOpenid();
//        // 如果已经注册但未登录，就把登录状态保存到redis
//        if (userMapper.selectOne(new QueryWrapper<User>().eq("openId", openId)) != null) {
//            String tokenKey = UUID.randomUUID().toString();
//            WxSession tokenInfo = new WxSession(openId, session.getSessionKey(), session.getUnionid());
//            RedisUtils.set(tokenKey, tokenInfo, LOGIN_EXPIRE_TIME);
//            return tokenKey;
//        } else {  // 如果未注册
////            callUserInfoApi()

    }


        return null;
}

    private WxSession callLoginApi(String jsCode) {
        Map<String, String> params = new HashMap<>(4);
        params.put("appid", WxConstants.APP_ID);
        params.put("secret", WxConstants.APP_SECRET);
        params.put("js_code", jsCode);
        params.put("grant_type", WxConstants.LOGIN_GRANT_TYPE);
        String url = UrlUtils.addParameterList(WxConstants.LOGIN_URL, params);
        String jsonData = restTemplate.getForObject(url, String.class);
        return JsonUtils.json2Object(jsonData, WxSession.class);
    }


}
