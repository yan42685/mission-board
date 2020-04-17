package com.small.missionboard.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "查询任务")
@RequestMapping("api/task/query")
@RestController
public class TaskQueryController {
}
