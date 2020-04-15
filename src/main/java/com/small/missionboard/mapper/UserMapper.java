package com.small.missionboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.missionboard.bean.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from missionboard.user where open_id = #{openId}")
    User selectByOpenId(String openId);

}
