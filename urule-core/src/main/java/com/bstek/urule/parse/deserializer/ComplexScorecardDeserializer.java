//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.scorecard.ComplexScorecardDefinition;
import com.bstek.urule.parse.scorecard.ComplexScorecardParser;
import org.dom4j.Element;

public class ComplexScorecardDeserializer implements Deserializer<ComplexScorecardDefinition> {
    public static final String BEAN_ID = "urule.complexScorecardDeserializer";
    private ComplexScorecardParser a;

    public ComplexScorecardDeserializer() {
    }

    public ComplexScorecardDefinition deserialize(Element var1) {
        ComplexScorecardDefinition var2 = this.a.parse(var1);
        return var2;
    }

    public void setComplexScorecardParser(ComplexScorecardParser var1) {
        this.a = var1;
    }

    public boolean support(Element var1) {
        return this.a.support(var1.getName());
    }
}
