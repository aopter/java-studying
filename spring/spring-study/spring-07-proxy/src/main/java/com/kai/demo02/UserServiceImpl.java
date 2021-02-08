package com.kai.demo02;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 11:15
 * @projectName spring-study
 * @className UserServiceImpl.java
 * @description TODO
 */
public class UserServiceImpl implements UserService{
    public void add() {
        System.out.println("增加了一个用户");
    }

    public void delete() {
        System.out.println("删除了一个用户");
    }

    public void update() {
        System.out.println("更新了一个用户");
    }

    public void query() {
        System.out.println("查询了用户");
    }
}
