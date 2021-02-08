package com.kai.demo04;

import com.kai.demo03.Rent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 11:42
 * @projectName spring-study
 * @className ProxyInvocationHandler.java
 * @description 动态代理工具类
 */
public class ProxyInvocationHandler implements InvocationHandler {

//    被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //    获得代理实例
    public Object getProxy(){
        return  Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

//   处理代理市里，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("执行方法前");
//        动态代理的本质
        Object result = method.invoke(target, args);
        System.out.println("执行方法后");

        return result;
    }
}
