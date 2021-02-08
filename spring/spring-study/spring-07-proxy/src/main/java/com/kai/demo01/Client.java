package com.kai.demo01;

/**
 * @author 刘凯丽
 * @createTime 2021/2/6 19:01
 * @projectName spring-study
 * @className Client.java
 * @description TODO
 */
public class Client {
    public static void main(String[] args) {
        Host host = new Host();
        Proxy proxy = new Proxy(host);

        proxy.rent();

    }
}
