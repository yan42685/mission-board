package com.small.missionboard.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

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

    @ApiModelProperty(value = "小程序范围内唯一id")
    private String openId;

    @ApiModelProperty(value = "信誉值")
    private Integer credit;

    @ApiModelProperty(value = "用户角色")
    private String role;

    @ApiModelProperty(value = "用户账号状态")
    private Integer state;

    @ApiModelProperty(value = "完成的任务总数")
    private Integer totalTasksFinished;

    @ApiModelProperty(value = "正在接的任务数")
    private Boolean currentTasksAccepted;

    @ApiModelProperty(value = "备注信息")
    private String remarks;


}
