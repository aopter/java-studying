package com.kai.demo03;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 11:48
 * @projectName spring-study
 * @className Client.java
 * @description TODO
 */
public class Client {
    public static void main(String[] args) {
//        真实角色
        Host host = new Host();
//        代理角色:现在没有
        ProxyInvocationHandler in = new ProxyInvocationHandler();
//        通过调用程序处理角色来处理我们要调用的借口对象
        in.setRent(host);
//        创建代理角色
        Rent proxy = (Rent) in.getProxy();
        proxy.rent();
    }
}
