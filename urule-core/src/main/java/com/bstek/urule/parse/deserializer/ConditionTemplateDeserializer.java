//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.template.ConditionTemplate;
import com.bstek.urule.parse.ConditionTemplateParser;
import org.dom4j.Element;

public class ConditionTemplateDeserializer implements Deserializer<ConditionTemplate> {
    public static final String BEAN_ID = "urule.conditionTemplateDeserializer";
    private ConditionTemplateParser a;

    public ConditionTemplateDeserializer() {
    }

    public ConditionTemplate deserialize(Element var1) {
        return this.a.parse(var1);
    }

    public boolean support(Element var1) {
        return this.a.support(var1.getName());
    }

    public void setConditionTemplateParser(ConditionTemplateParser var1) {
        this.a = var1;
    }
}
