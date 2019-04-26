package com.alex.study.design.adapter;

/**
 * 类适配器模式
 * 适配器(Adaper)角色：适配器类是本模式的核心。适配器把源接口转换成目标接口。显然，这一角色不可以是接口，而必须是具体类。
 *
 * @author liangxiaofei.
 * @since 2019/4/26 10:23 AM.
 */
public class ClassAdapter extends Adaptee implements Target {

    /**
     * 由于源类Adaptee没有方法sampleOperation2()
     * 因此适配器补充上这个方法
     */
    public void sampleOperation2() {
        System.out.println("sampleOperation2");
    }
}
