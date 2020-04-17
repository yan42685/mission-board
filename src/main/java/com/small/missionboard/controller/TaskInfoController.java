package com.small.missionboard.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "任务信息")
@RequestMapping("api/task/info")
@RestController
public class TaskInfoController {
}
