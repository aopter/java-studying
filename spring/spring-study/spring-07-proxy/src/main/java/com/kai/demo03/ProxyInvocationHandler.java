package com.kai.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 刘凯丽
 * @createTime 2021/2/7 11:42
 * @projectName spring-study
 * @className ProxyInvocationHandler.java
 * @description TODO
 */
public class ProxyInvocationHandler implements InvocationHandler {

//    被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    //    获得代理实例
    public Object getProxy(){
        return  Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }

//   处理代理市里，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

//        动态代理的本质
        Object result = method.invoke(rent, args);
        return result;
    }
}
