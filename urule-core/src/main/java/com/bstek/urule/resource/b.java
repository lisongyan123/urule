//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.resource;

import com.bstek.urule.model.rule.RuleSet;
import org.dom4j.Element;

public class RuleSetResourceBuilder implements ResourceBuilder<RuleSet> {
    private RuleSetDeserializer a;

    public RuleSetResourceBuilder() {
    }

    public RuleSet build(Element var1, String var2) {
        return this.a.deserialize(var1);
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public ResourceType getType() {
        return ResourceType.RuleSet;
    }

    public void setRuleSetDeserializer(RuleSetDeserializer var1) {
        this.a = var1;
    }
}
