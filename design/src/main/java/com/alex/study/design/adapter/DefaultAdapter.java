package com.alex.study.design.adapter;

/**
 * 缺省适配(Default Adapter)模式为一个接口提供缺省实现，这样子类型可以从这个缺省实现进行扩展，而不必从原有接口进行扩展。
 * 作为适配器模式的一个特例，缺省是适配模式在JAVA语言中有着特殊的应用。
 * <p>
 * 适配器模式的用意是要改变源的接口，以便于目标接口相容。缺省适配的用意稍有不同，它是为了方便建立一个不平庸的适配器类而提供的一种平庸实现。
 *
 * @author liangxiaofei.
 * @since 2019/4/26 10:29 AM.
 */
public class DefaultAdapter implements Target {

    public void sampleOperation1() {

    }

    public void sampleOperation2() {

    }
}
