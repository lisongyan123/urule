//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.template.ActionTemplate;
import com.bstek.urule.parse.ActionTemplateParser;
import org.dom4j.Element;

public class ActionTemplateDeserializer implements Deserializer<ActionTemplate> {
    public static final String BEAN_ID = "urule.actionTemplateDeserializer";
    private ActionTemplateParser a;

    public ActionTemplateDeserializer() {
    }

    public ActionTemplate deserialize(Element var1) {
        return this.a.parse(var1);
    }

    public boolean support(Element var1) {
        return this.a.support(var1.getName());
    }

    public void setActionTemplateParser(ActionTemplateParser var1) {
        this.a = var1;
    }
}
