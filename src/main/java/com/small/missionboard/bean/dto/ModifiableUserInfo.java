package com.small.missionboard.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("可修改的用户信息")
@Data
public class ModifiableUserInfo implements Serializable {

    private static final long serialVersionUID = 7965515778780477599L;

    @ApiModelProperty(value = "学院")
    private String faculty;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "默认联系方式")
    private String contactInformation;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "备注信息")
    private String remarks;
}
