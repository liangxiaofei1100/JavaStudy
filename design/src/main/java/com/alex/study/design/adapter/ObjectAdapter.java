package com.alex.study.design.adapter;

/**
 * 对象适配器模式
 * 与类的适配器模式一样，对象的适配器模式把被适配的类的API转换成为目标类的API，与类的适配器模式不同的是，
 * 对象的适配器模式不是使用继承关系连接到Adaptee类，而是使用委派关系连接到Adaptee类。
 *
 * @author liangxiaofei.
 * @since 2019/4/26 10:24 AM.
 */
public class ObjectAdapter implements Target {

    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void sampleOperation1() {
        adaptee.sampleOperation1();
    }

    public void sampleOperation2() {
        System.out.println("sampleOperation2");
    }
}
