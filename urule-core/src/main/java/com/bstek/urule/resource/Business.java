//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.resource;

import com.bstek.urule.model.template.ConditionTemplate;
import com.bstek.urule.parse.deserializer.ConditionTemplateDeserializer;
import org.dom4j.Element;

public class ConditionTemplateResourceBuilder implements ResourceBuilder<ConditionTemplate> {
    private ConditionTemplateDeserializer a;

    public ConditionTemplateResourceBuilder() {
    }

    public ConditionTemplate build(Element var1, String var2) {
        return this.a.deserialize(var1);
    }

    public ResourceType getType() {
        return ResourceType.ConditionTemplate;
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public void setConditionTemplateDeserializer(ConditionTemplateDeserializer var1) {
        this.a = var1;
    }
}
