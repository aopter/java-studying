package com.kai.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 16:39
 * @projectName spring-study
 * @className AnonotationPointCut.java
 * @description TODO
 */

@Component//需要注册类
@Aspect//表明这个类是一个切面
public class AnonotationPointCut {

    @Before("execution(* com.kai.service.UserserviceImpl.*(..))")
    public void before(){
        System.out.println("========执行方法前");
    }

    @After("execution(* com.kai.service.UserserviceImpl.*(..))")
    public void after(){
        System.out.println("========执行方法后");
    }

//    在环绕增强中 我们可以给定一个参数 代表我们要获取切入的点
    @Around("execution(* com.kai.service.UserserviceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("环绕前");
//        获得签名的方法
        Signature signature = joinPoint.getSignature();
        System.out.println("签名："+signature);
//        执行方法
        joinPoint.proceed();//方法执行
        System.out.println("环绕后");
    }
}
