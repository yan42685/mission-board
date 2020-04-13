package com.small.missionboard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.missionboard.entity.User;
import com.small.missionboard.mapper.UserMapper;
import com.small.missionboard.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
