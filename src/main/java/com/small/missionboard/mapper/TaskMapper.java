package com.small.missionboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.missionboard.bean.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {

}
