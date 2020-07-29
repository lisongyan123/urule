//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.resource;

import com.bstek.urule.model.template.ActionTemplate;
import com.bstek.urule.parse.deserializer.ActionTemplateDeserializer;
import org.dom4j.Element;

public class ActionTemplateResourceBuilder implements ResourceBuilder<ActionTemplate> {
    private ActionTemplateDeserializer a;

    public ActionTemplateResourceBuilder() {
    }

    public ActionTemplate build(Element var1, String var2) {
        return this.a.deserialize(var1);
    }

    public ResourceType getType() {
        return ResourceType.ActionTemplate;
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public void setActionTemplateDeserializer(ActionTemplateDeserializer var1) {
        this.a = var1;
    }
}
