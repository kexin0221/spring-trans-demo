package com.bite.springtransdemo.controller;

import com.bite.springtransdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition definitions;

    @RequestMapping("/registry")
    public String registry(String name, String password) {
        // 开启事务
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(definitions);
        // 用户注册
        userService.registryUser(name, password);
        log.info("用户注册成功");
        // 事务提交
//        dataSourceTransactionManager.commit(transaction);
        // 事务回滚
        dataSourceTransactionManager.rollback(transaction);
        return "注册成功";

    }
}