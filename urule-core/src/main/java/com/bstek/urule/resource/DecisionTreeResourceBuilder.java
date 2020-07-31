//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.resource;

import com.bstek.urule.model.decisiontree.DecisionTree;
import com.bstek.urule.parse.deserializer.DecisionTreeDeserializer;
import org.dom4j.Element;

public class DecisionTreeResourceBuilder implements ResourceBuilder<DecisionTree> {
    private DecisionTreeDeserializer a;

    public DecisionTreeResourceBuilder() {
    }

    public DecisionTree build(Element var1, String var2) {
        return this.a.deserialize(var1);
    }

    public ResourceType getType() {
        return ResourceType.DecisionTree;
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public void setDecisionTreeDeserializer(DecisionTreeDeserializer var1) {
        this.a = var1;
    }
}
