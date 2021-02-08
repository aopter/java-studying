package com.kai.log;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 15:40
 * @projectName spring-study
 * @className Log.java
 * @description TODO
 */

@Component
public class Log implements MethodBeforeAdvice {

//    method：要执行的目标对象的方法
//    args:参数
//    target：目标对象
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(o.getClass().getName()+"的"+method.getName()+"方法执行了");
    }
}
