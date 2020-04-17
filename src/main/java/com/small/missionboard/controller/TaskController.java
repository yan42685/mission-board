package com.small.missionboard.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "处理任务")
@RequestMapping("api/task")
@RestController
public class TaskController {
}
