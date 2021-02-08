package com.kai.diy;

import org.springframework.stereotype.Component;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 16:25
 * @projectName spring-study
 * @className MyPointCut.java
 * @description TODO
 */
@Component
public class MyPointCut {

    public void before(){
        System.out.println("========执行方法前");
    }
    public void after(){
        System.out.println("========执行方法后");
    }
}
