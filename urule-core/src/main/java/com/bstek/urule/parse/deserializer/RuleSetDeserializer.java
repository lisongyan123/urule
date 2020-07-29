//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.rule.RuleSet;
import com.bstek.urule.parse.RuleSetParser;
import org.dom4j.Element;

public class RuleSetDeserializer implements Deserializer<RuleSet> {
    public static final String BEAN_ID = "urule.ruleSetDeserializer";
    private RuleSetParser a;

    public RuleSetDeserializer() {
    }

    public RuleSet deserialize(Element var1) {
        return this.a.parse(var1);
    }

    public boolean support(Element var1) {
        return this.a.support(var1.getName());
    }

    public void setRuleSetParser(RuleSetParser var1) {
        this.a = var1;
    }
}
