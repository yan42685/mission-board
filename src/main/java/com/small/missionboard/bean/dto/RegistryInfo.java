package com.small.missionboard.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户注册时需要的信息
 */
@ApiModel(value = "注册信息", description = "")
@Data
public class RegistryInfo implements Serializable {

    private static final long serialVersionUID = -5636813189087278717L;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "学校")
    @NotBlank(message = "学校不能为空")
    private String university;

    @ApiModelProperty(value = "学院")
    @NotBlank(message = "学院不能为空")
    private String faculty;

    @ApiModelProperty(value = "学号")
    @NotBlank(message = "学号不能为空")
    private String studentNumber;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phoneNumber;
}
