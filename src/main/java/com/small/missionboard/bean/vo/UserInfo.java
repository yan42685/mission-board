package com.small.missionboard.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 前端可见的用户信息
 */
@Data
public class UserInfo {
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private Boolean gender;

    @ApiModelProperty(value = "学院")
    private String faculty;

    @ApiModelProperty(value = "学号")
    private String studentNumber;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "默认联系方式")
    private String contactInformation;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像URL")
    private String avatarUrl;

    @ApiModelProperty(value = "信誉值")
    private Integer credit;

    @ApiModelProperty(value = "用户角色")
    private String role;

    @ApiModelProperty(value = "完成的任务总数")
    private Integer totalTasksFinished;

    @ApiModelProperty(value = "正在接的任务数")
    private Boolean currentTasksAccepted;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

}
