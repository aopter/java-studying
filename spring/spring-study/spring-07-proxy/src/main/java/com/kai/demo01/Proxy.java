package com.kai.demo01;

/**
 * @author 刘凯丽
 * @createTime 2021/2/6 19:03
 * @projectName spring-study
 * @className Proxy.java
 * @description TODO
 */
public class Proxy implements Rent{

    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    public void rent() {
        seeHouse();
        host.rent();
        heTong();
    }

//    看房
    private void seeHouse(){
        System.out.println("中介带你看房！！！");
    }
//    签合同
    private void heTong(){
        System.out.println("和你签合同！！！");
    }
//
}
