//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.Op;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.Criterion;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class CriteriaParser extends CriterionParser {
    private ValueParser a;
    private LeftParser b;

    public CriteriaParser() {
    }

    public Criterion parse(Element var1) {
        Criteria var2 = new Criteria();
        String var3 = var1.attributeValue("op");
        if (StringUtils.isNotBlank(var3)) {
            Op var4 = Op.valueOf(var3);
            var2.setOp(var4);
        }

        Iterator var8 = var1.elements().iterator();

        while(var8.hasNext()) {
            Object var5 = var8.next();
            if (var5 != null && var5 instanceof Element) {
                Element var6 = (Element)var5;
                String var7 = var6.getName();
                if (var7.equals("value")) {
                    var2.setValue(this.a.parse(var6));
                } else if (var7.equals("left")) {
                    var2.setLeft(this.b.parse(var6));
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("atom");
    }

    public void setValueParser(ValueParser var1) {
        this.a = var1;
    }

    public void setLeftParser(LeftParser var1) {
        this.b = var1;
    }
}
