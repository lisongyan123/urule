//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.lhs.ConditionTemplateCriterion;
import com.bstek.urule.model.rule.lhs.Criterion;
import org.dom4j.Element;

public class ConditionTemplateCriterionParser extends CriterionParser {
    public ConditionTemplateCriterionParser() {
    }

    public boolean support(String var1) {
        return var1.equals("template");
    }

    public Criterion parse(Element var1) {
        ConditionTemplateCriterion var2 = new ConditionTemplateCriterion();
        var2.setId(var1.attributeValue("id"));
        var2.setName(var1.attributeValue("name"));
        return var2;
    }
}
