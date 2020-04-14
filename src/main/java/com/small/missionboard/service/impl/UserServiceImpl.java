package com.small.missionboard.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.missionboard.bean.entity.User;
import com.small.missionboard.bean.vo.JsonWrapper;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.config.WxMaConfiguration;
import com.small.missionboard.constant.WxConstants;
import com.small.missionboard.mapper.UserMapper;
import com.small.missionboard.service.UserService;
import com.small.missionboard.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 登录态过期时间 5 小时
     */
    private static final Long LOGIN_EXPIRE_TIME = 60 * 60 * 5L;
    private final WxMaService wxService = WxMaConfiguration.getMaService(WxConstants.APP_ID);

    public String login(String token, String jsCode) throws WxErrorException {
        WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(jsCode);
        String openId = session.getOpenid();
        String sessionKey = session.getSessionKey();

        // 用户不存在时需要注册
        if (userMapper.selectByOpenId(openId) == null) {
            throw new KnownException(JsonWrapper.NOT_REGISTER, "用户未注册");
        }

        // 如果已经登录就刷新过期时间
        if (RedisUtils.hasKey(token)) {
            RedisUtils.expire(token, LOGIN_EXPIRE_TIME);
            return token;
        }

        //  把登录状态保存到redis
        String newToken = UUID.randomUUID().toString();
        RedisUtils.set(newToken, session, LOGIN_EXPIRE_TIME);
        return newToken;

    }

    public void register(String jsCode, String signature, String rawData, String encryptedData, String iv) throws WxErrorException {
        // 用户信息校验
        String sessionKey = wxService.getUserService().getSessionInfo(jsCode).getSessionKey();
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            throw new KnownException(JsonWrapper.USER_VERIFICATION_FAILED, "用户身份校验失败");
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        User user = new User();


//
//            return JsonUtils.toJson(userInfo);
//

    }

}
