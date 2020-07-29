//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.resource;

import org.dom4j.Element;

public interface ResourceBuilder<T> {
    T build(Element var1, String var2);

    boolean support(Element var1);

    ResourceType getType();
}
