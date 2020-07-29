//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.table.ScriptDecisionTable;
import com.bstek.urule.parse.table.ScriptDecisionTableParser;
import org.dom4j.Element;

public class ScriptDecisionTableDeserializer implements Deserializer<ScriptDecisionTable> {
    public static final String BEAN_ID = "urule.scriptDecisionTableDeserializer";
    private ScriptDecisionTableParser a;

    public ScriptDecisionTableDeserializer() {
    }

    public ScriptDecisionTable deserialize(Element var1) {
        return this.a.parse(var1);
    }

    public boolean support(Element var1) {
        return this.a.support(var1.getName());
    }

    public void setScriptDecisionTableParser(ScriptDecisionTableParser var1) {
        this.a = var1;
    }
}
