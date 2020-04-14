package com.small.missionboard.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "注册信息", description = "")
@Data
public class RegistryInfo {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "学校")
    private String university;
    @ApiModelProperty(value = "学院")
    private String faculty;
    @ApiModelProperty(value = "学号")
    private String studentNumber;
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
}
