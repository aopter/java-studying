package com.kai.demo04;

import com.kai.demo02.UserService;
import com.kai.demo02.UserServiceImpl;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 11:54
 * @projectName spring-study
 * @className Client.java
 * @description TODO
 */
public class Client {
    public static void main(String[] args) {
//真实角色
        UserServiceImpl userService = new UserServiceImpl();
//       代理角色：不存在
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setTarget(userService);
//        动态生成代理类
        UserService proxy = (UserService) proxyInvocationHandler.getProxy();
//      执行方法
        proxy.add();

    }
}
