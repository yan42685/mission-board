package com.small.missionboard.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TestEntity {
    @ApiModelProperty(value = "名字", example = "张三")
    String name;
}
