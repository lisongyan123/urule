//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.resource;

import com.bstek.urule.model.table.ScriptDecisionTable;
import com.bstek.urule.parse.deserializer.ScriptDecisionTableDeserializer;
import org.dom4j.Element;

public class ScriptDecisionTableResourceBuilder implements ResourceBuilder<ScriptDecisionTable> {
    private ScriptDecisionTableDeserializer a;

    public ScriptDecisionTableResourceBuilder() {
    }

    public ScriptDecisionTable build(Element var1, String var2) {
        return this.a.deserialize(var1);
    }

    public ResourceType getType() {
        return ResourceType.ScriptDecisionTable;
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public void setScriptDecisionTableDeserializer(ScriptDecisionTableDeserializer var1) {
        this.a = var1;
    }
}
