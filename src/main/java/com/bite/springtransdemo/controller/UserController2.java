package com.bite.springtransdemo.controller;

import com.bite.springtransdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequestMapping("/user2")
@RestController
public class UserController2 {
    @Autowired
    private UserService userService;

    @Transactional
    @RequestMapping("/registry")
    public String registry(String name, String password) {
        // 用户注册
        Integer result = userService.registryUser(name, password);
        log.info("用户注册成功, 影响行数: {}", result);
        try {
            int a = 10/0;
        } catch (Exception e) {
            log.error("程序发生异常, e: ", e);
//            throw new RuntimeException(e);
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "注册成功";
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/r2")
    public String r2(String name, String password) throws IOException {
        // 用户注册
        Integer result = userService.registryUser(name, password);
        log.info("用户注册成功, 影响行数: {}", result);
        throw new IOException();
    }

    @Transactional(isolation = Isolation.DEFAULT)
    @RequestMapping("/r3")
    public String r3(String name, String password) throws IOException {
        // 用户注册
        Integer result = userService.registryUser(name, password);
        log.info("用户注册成功, 影响行数: {}", result);
        throw new IOException();
    }
}