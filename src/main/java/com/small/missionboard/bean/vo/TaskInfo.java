package com.small.missionboard.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "任务信息")
public class TaskInfo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务编号")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "交易方式")
    private String transactionMeans;

    @ApiModelProperty(value = "发送者id")
    private String senderId;

    @ApiModelProperty(value = "接受者id, 可能有多个")
    private List<String> receiverIdList;

    @ApiModelProperty(value = "截止时间")
    private LocalDateTime deadline;

    @ApiModelProperty(value = "任务状态")
    private List<String> statusList;

    @ApiModelProperty(value = "最大可接该任务的人数")
    private Integer maxReceiver;

    @ApiModelProperty(value = "任务发送者对接受者的评价")
    private String commentOnReceiver;

    @ApiModelProperty(value = "对接受者的评价星级")
    private Integer starForReceiver;

    @ApiModelProperty(value = "任务接受者对发送者的评价")
    private String commentOnSender;

    @ApiModelProperty(value = "对发送者的评价星级")
    private Integer starForSender;

    @ApiModelProperty(value = "是否一接受任务就自动确认")
    private Boolean quickAccept;

    @ApiModelProperty(value = "接受者确认完成任务")
    private Boolean receiverConfirmed;

    @ApiModelProperty(value = "发送者确认完成任务")
    private Boolean senderConfirmed;

    @ApiModelProperty(value = "任务提交时间")
    private LocalDateTime submitTime;

    @ApiModelProperty(value = "接受者留言")
    private String receiverNotes;

    @ApiModelProperty(value = "发送者联系方式")
    private String senderContactInfo;

    @ApiModelProperty(value = "发送者信誉")
    private String senderCredit;
}
