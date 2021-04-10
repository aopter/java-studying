package com.kai.service;

import org.springframework.stereotype.Component;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 15:39
 * @projectName spring-study
 * @className UserserviceImpl.java
 * @description TODO
 */
@Component
public class UserserviceImpl implements UserService{
    public void add() {
        System.out.println("增加了一个用户");
    }

    public void delete() {
        System.out.println("删除了一个用户");
    }

    public void update() {
        System.out.println("更新了一个用户");
    }

    public void select() {
        System.out.println("查询了用户");
    }
}
