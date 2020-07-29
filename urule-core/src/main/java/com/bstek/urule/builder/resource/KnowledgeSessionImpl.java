//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.resource;

import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.parse.deserializer.FlowDeserializer;
import org.dom4j.Element;

public class FlowResourceBuilder implements ResourceBuilder<FlowDefinition> {
    private FlowDeserializer a;

    public FlowResourceBuilder() {
    }

    public FlowDefinition build(Element var1, String var2) {
        return this.a.deserialize(var1);
    }

    public ResourceType getType() {
        return ResourceType.Flow;
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public void setFlowDeserializer(FlowDeserializer var1) {
        this.a = var1;
    }
}
