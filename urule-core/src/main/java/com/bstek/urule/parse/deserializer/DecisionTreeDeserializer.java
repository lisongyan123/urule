//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.decisiontree.DecisionTree;
import com.bstek.urule.parse.decisiontree.DecisionTreeParser;
import org.dom4j.Element;

public class DecisionTreeDeserializer implements Deserializer<DecisionTree> {
    public static final String BEAN_ID = "urule.decisionTreeDeserializer";
    private DecisionTreeParser a;

    public DecisionTreeDeserializer() {
    }

    public DecisionTree deserialize(Element var1) {
        return this.a.parse(var1);
    }

    public void setDecisionTreeParser(DecisionTreeParser var1) {
        this.a = var1;
    }

    public boolean support(Element var1) {
        return this.a.support(var1.getName());
    }
}
