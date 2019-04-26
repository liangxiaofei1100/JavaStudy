package com.alex.study.design.adapter;

/**
 * @author liangxiaofei.
 * @since 2019/4/26 10:31 AM.
 */
public class AdapterDemo {

    public static void main(String[] args) {
        System.out.println("===类适配器===");
        Target target = new ClassAdapter();
        target.sampleOperation1();
        target.sampleOperation2();

        System.out.println("===对象适配器===");
        Adaptee adaptee = new Adaptee();
        target = new ObjectAdapter(adaptee);
        target.sampleOperation1();
        target.sampleOperation2();

        System.out.println("===默认适配器===");
        target = new SpecificAdapter();
        target.sampleOperation1();
        target.sampleOperation2();
    }
}
