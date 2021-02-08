package com.kai.log;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 16:04
 * @projectName spring-study
 * @className AfterLog.java
 * @description TODO
 */

@Component
public class AfterLog implements AfterReturningAdvice {
//    returnValue返回值
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("执行了"+method.getName()+"  返回结果为:"+returnValue);
    }
}
