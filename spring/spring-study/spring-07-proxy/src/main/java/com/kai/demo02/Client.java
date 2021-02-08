package com.kai.demo02;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 11:22
 * @projectName spring-study
 * @className Client.java
 * @description TODO
 */
public class Client {
    public static void main(String[] args) {
        UserServiceProxy userServiceProxy = new UserServiceProxy();
        UserServiceImpl userService = new UserServiceImpl();
        userServiceProxy.setUserServiceImpl(userService);

        userServiceProxy.query();
    }
}
