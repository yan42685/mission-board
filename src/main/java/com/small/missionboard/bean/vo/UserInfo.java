package com.small.missionboard.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 前端可见的用户信息
 */
@ApiModel(value = "用户信息")
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 7965515778780477599L;

    @ApiModelProperty(value = "用户编号")
    private Long id;

    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @NotBlank(message = "性别不能为空")
    @ApiModelProperty(value = "性别")
    private String gender;

    @NotBlank(message = "学院不能为空")
    @ApiModelProperty(value = "学院")
    private String faculty;

    @NotBlank(message = "学号不能为空")
    @ApiModelProperty(value = "学号")
    private String studentNumber;

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "默认联系方式")
    private String contactInformation;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像URL")
    private String avatarUrl;

    @NotBlank(message = "信誉值不能为空")
    @ApiModelProperty(value = "信誉值")
    private Integer credit;

    @NotBlank(message = "用户角色不能为空")
    @ApiModelProperty(value = "用户角色")
    private String role;

    @NotBlank(message = "完成的任务总数不能为空")
    @ApiModelProperty(value = "完成的任务总数")
    private Integer totalTasksFinished;

    @NotBlank(message = "正在接的任务数不能为空")
    @ApiModelProperty(value = "正在接的任务数")
    private Integer currentTasksAccepted;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

}
