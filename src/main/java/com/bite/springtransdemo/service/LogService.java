package com.bite.springtransdemo.service;

import com.bite.springtransdemo.mapper.LogInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LogService {
    @Autowired
    private LogInfoMapper logInfoMapper;

    @Transactional(propagation = Propagation.NESTED)
    public Integer insertLog(String name, String op) {
        // 记录用户操作
        return logInfoMapper.insertLog(name, "用户注册");
    }
}
