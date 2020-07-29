//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.resource;

import com.bstek.urule.model.library.constant.ConstantLibrary;
import com.bstek.urule.parse.deserializer.ConstantLibraryDeserializer;
import org.dom4j.Element;

public class ConstantLibraryResourceBuilder implements ResourceBuilder<ConstantLibrary> {
    private ConstantLibraryDeserializer a;

    public ConstantLibraryResourceBuilder() {
    }

    public ConstantLibrary build(Element var1, String var2) {
        return this.a.deserialize(var1);
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public ResourceType getType() {
        return ResourceType.ConstantLibrary;
    }

    public void setConstantLibraryDeserializer(ConstantLibraryDeserializer var1) {
        this.a = var1;
    }
}
