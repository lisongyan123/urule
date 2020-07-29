//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import org.dom4j.Element;

public interface Parser<T> {
    boolean support(String var1);

    T parse(Element var1);
}
