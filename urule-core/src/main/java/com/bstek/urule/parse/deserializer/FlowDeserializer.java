//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.parse.flow.FlowDefinitionParser;
import org.dom4j.Element;

public class FlowDeserializer implements Deserializer<FlowDefinition> {
    public static final String BEAN_ID = "urule.flowDeserializer";
    private FlowDefinitionParser a;

    public FlowDeserializer() {
    }

    public FlowDefinition deserialize(Element var1) {
        return this.a.parse(var1);
    }

    public boolean support(Element var1) {
        return this.a.support(var1.getName());
    }

    public void setFlowDefinitionParser(FlowDefinitionParser var1) {
        this.a = var1;
    }
}
