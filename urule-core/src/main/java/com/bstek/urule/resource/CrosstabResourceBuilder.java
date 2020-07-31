//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.resource;

import com.bstek.urule.model.crosstab.CrosstabDefinition;
import com.bstek.urule.parse.deserializer.CrosstableDeserializer;
import org.dom4j.Element;

public class CrosstabResourceBuilder implements ResourceBuilder<CrosstabDefinition> {
    private CrosstableDeserializer a;

    public CrosstabResourceBuilder() {
    }

    public CrosstabDefinition build(Element var1, String var2) {
        return this.a.deserialize(var1);
    }

    public ResourceType getType() {
        return ResourceType.CrossDecisionTable;
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public void setCrosstableDeserializer(CrosstableDeserializer var1) {
        this.a = var1;
    }
}
