package com.small.missionboard.bean.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 解密后的信息
 */
@NoArgsConstructor
@Data
public class WxUserInfo {
    private String openId;
    private String nickName;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
    private WatermarkBean watermark;

    @NoArgsConstructor
    @Data
    public static class WatermarkBean {
        private String appid;
        private String timestamp;
    }
}
