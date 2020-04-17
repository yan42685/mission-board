package com.small.missionboard.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * 创建任务需要的信息
 */
@ApiModel(value = "创建任务需要的信息", description = "")
@Data
public class TaskCreateInfo {
    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;

    @NotBlank(message = "内容不能为空")
    @ApiModelProperty(value = "内容")
    private String content;

    @NotBlank(message = "交易方式不能为空")
    @ApiModelProperty(value = "交易方式")
    private String transactionMeans;

    @NotEmpty(message = "截止时间不能为空")
    @ApiModelProperty(value = "截止时间")
    private LocalDateTime deadline;

    @ApiModelProperty(value = "是否一接受任务就自动确认")
    private Boolean quickAccept;

    @NotBlank(message = "联系方式不能为空")
    @ApiModelProperty(value = "发送者联系方式")
    private String senderContactInfo;
}
