package com.small.missionboard.mapper;

import com.small.missionboard.bean.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from missionboard.user where open_id = #{openId}")
    User selectByOpenId(String openId);

}
