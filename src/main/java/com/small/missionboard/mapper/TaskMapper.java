package com.small.missionboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.missionboard.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {

}
