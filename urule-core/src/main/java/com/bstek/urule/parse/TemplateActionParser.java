//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.action.Action;
import com.bstek.urule.action.TemplateAction;
import org.dom4j.Element;

public class TemplateActionParser extends ActionParser {
    public TemplateActionParser() {
    }

    public boolean support(String var1) {
        return var1.equals("action-template");
    }

    public Action parse(Element var1) {
        TemplateAction var2 = new TemplateAction();
        var2.setId(var1.attributeValue("id"));
        var2.setName(var1.attributeValue("name"));
        return var2;
    }
}
