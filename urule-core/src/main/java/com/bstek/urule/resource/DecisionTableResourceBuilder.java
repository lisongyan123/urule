//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.resource;

import com.bstek.urule.model.table.DecisionTable;
import com.bstek.urule.parse.deserializer.DecisionTableDeserializer;
import org.dom4j.Element;

public class DecisionTableResourceBuilder implements ResourceBuilder<DecisionTable> {
    private DecisionTableDeserializer a;

    public DecisionTableResourceBuilder() {
    }

    public DecisionTable build(Element var1, String var2) {
        return this.a.deserialize(var1);
    }

    public ResourceType getType() {
        return ResourceType.DecisionTable;
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public void setDecisionTableDeserializer(DecisionTableDeserializer var1) {
        this.a = var1;
    }
}
