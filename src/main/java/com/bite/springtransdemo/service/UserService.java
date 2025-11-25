package com.bite.springtransdemo.service;

import com.bite.springtransdemo.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Transactional(propagation = Propagation.NESTED)
    public Integer registryUser(String name, String password) {
        // 插入用户信息
        return userInfoMapper.insert(name, password);
    }
}