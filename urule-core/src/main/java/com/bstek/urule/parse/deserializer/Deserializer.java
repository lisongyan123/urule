//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import org.dom4j.Element;

public interface Deserializer<T> {
    T deserialize(Element var1);

    boolean support(Element var1);
}
