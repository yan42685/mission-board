package com.small.missionboard.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 创建任务需要的信息
 */
@ApiModel(value = "创建任务需要的信息", description = "")
@Data
public class TaskCreateInfo {
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "交易方式")
    private String transactionMeans;

    @ApiModelProperty(value = "截止时间")
    private LocalDateTime deadline;

    @ApiModelProperty(value = "是否一接受任务就自动确认")
    private Boolean quickAccept;

    @ApiModelProperty(value = "联系方式")
    private String contactInformation;
}
