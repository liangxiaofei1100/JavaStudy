package com.alex.study.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liangxiaofei.
 * @since 2019/4/25 11:04 AM.
 */
public class BuyHouseInvocationHandler implements InvocationHandler {

    private Object object;

    public BuyHouseInvocationHandler(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买房前准备");
        Object result = method.invoke(object, args);
        System.out.println("买房后装修");
        return result;
    }
}
