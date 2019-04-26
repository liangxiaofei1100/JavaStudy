package com.alex.study.design.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @author liangxiaofei.
 * @since 2019/4/25 10:59 AM.
 */
public class ProxyDemo {

    public static void main(String[] args) {
        System.out.println("===不用代理===");
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHouse();

        System.out.println("===静态代理===");
        BuyHouse buyHouseProxy = new BuyHouseProxy(buyHouse);
        buyHouseProxy.buyHouse();

        System.out.println("===JDK动态代理===");
        BuyHouse jdkDynamicProxy = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(), new Class[]{BuyHouse.class}, new BuyHouseInvocationHandler(buyHouse));
        jdkDynamicProxy.buyHouse();

        System.out.println("===CGlib动态代理===");
        BuyHouseMethodInterceptor buyHouseMethodInterceptor = new BuyHouseMethodInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BuyHouseImpl.class);
        enhancer.setCallback(buyHouseMethodInterceptor);
        BuyHouse cglibDynamicProxy = (BuyHouseImpl) enhancer.create();
        cglibDynamicProxy.buyHouse();
    }
}
