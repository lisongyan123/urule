//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.table.DecisionTable;
import com.bstek.urule.parse.table.DecisionTableParser;
import org.dom4j.Element;

public class DecisionTableDeserializer implements Deserializer<DecisionTable> {
    public static final String BEAN_ID = "urule.decisionTableDeserializer";
    private DecisionTableParser a;

    public DecisionTableDeserializer() {
    }

    public DecisionTable deserialize(Element var1) {
        return this.a.parse(var1);
    }

    public boolean support(Element var1) {
        return this.a.support(var1.getName());
    }

    public void setDecisionTableParser(DecisionTableParser var1) {
        this.a = var1;
    }
}
