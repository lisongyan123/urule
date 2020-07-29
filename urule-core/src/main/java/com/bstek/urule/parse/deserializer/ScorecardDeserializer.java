//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.scorecard.ScorecardDefinition;
import com.bstek.urule.parse.scorecard.ScorecardParser;
import org.dom4j.Element;

public class ScorecardDeserializer implements Deserializer<ScorecardDefinition> {
    public static final String BEAN_ID = "urule.scorecardDeserializer";
    private ScorecardParser a;

    public ScorecardDeserializer() {
    }

    public ScorecardDefinition deserialize(Element var1) {
        ScorecardDefinition var2 = this.a.parse(var1);
        return var2;
    }

    public void setScorecardParser(ScorecardParser var1) {
        this.a = var1;
    }

    public boolean support(Element var1) {
        return this.a.support(var1.getName());
    }
}
