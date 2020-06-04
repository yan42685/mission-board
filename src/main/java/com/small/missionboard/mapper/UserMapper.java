package com.small.missionboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.missionboard.bean.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where open_id = #{openId}")
    User selectByOpenId(String openId);

    /**
     * 获取用户正在进行的任务数量
     */
    Integer selectCurrentTasksAccepted(Long userId, String status);

    Integer selectTotalTasksFinished(Long userId, String status);
}
