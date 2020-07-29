//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.crosstab.CrosstabDefinition;
import com.bstek.urule.parse.crosstab.CrosstabParser;
import org.dom4j.Element;

public class CrosstableDeserializer implements Deserializer<CrosstabDefinition> {
    public static final String BEAN_ID = "urule.crosstableDeserializer";
    private CrosstabParser a;

    public CrosstableDeserializer() {
    }

    public CrosstabDefinition deserialize(Element var1) {
        return this.a.parse(var1);
    }

    public boolean support(Element var1) {
        return "crosstab".equals(var1.getName());
    }

    public void setCrosstabParser(CrosstabParser var1) {
        this.a = var1;
    }
}
