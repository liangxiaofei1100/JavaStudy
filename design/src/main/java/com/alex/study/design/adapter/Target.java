package com.alex.study.design.adapter;

/**
 * 目标(Target)角色：这就是所期待得到的接口。注意：由于这里讨论的是类适配器模式，因此目标不可以是类。
 *
 * @author liangxiaofei.
 * @since 2019/4/26 10:21 AM.
 */
public interface Target {
    /**
     * 这是源类Adaptee也有的方法
     */
    void sampleOperation1();

    /**
     * 这是源类Adapteee没有的方法
     */
    void sampleOperation2();
}
