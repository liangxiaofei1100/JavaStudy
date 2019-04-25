package com.alex.study.design.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liangxiaofei.
 * @since 2019/4/25 11:23 AM.
 */
public class BuyHouseMethodInterceptor implements MethodInterceptor {

    public BuyHouseImpl getInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BuyHouseImpl.class);
        enhancer.setCallback(this);
        return (BuyHouseImpl) enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("买房前准备");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("买房后装修");
        return result;
    }
}
