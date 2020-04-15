package com.small.missionboard.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信登录返回的信息
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WxSession implements Serializable {
    private static final long serialVersionUID = 2433322373435697000L;
    private String openid;
    /**
     * JackSon配置了驼峰命名策略，所以这里不用写下划线
     */
    private String sessionKey;
    private String unionid;
}
