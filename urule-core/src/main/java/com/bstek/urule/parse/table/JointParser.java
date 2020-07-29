//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.table;

import com.bstek.urule.model.rule.Op;
import com.bstek.urule.model.table.Condition;
import com.bstek.urule.model.table.Joint;
import com.bstek.urule.model.table.JointType;
import com.bstek.urule.parse.Parser;
import com.bstek.urule.parse.ValueParser;
import java.util.Iterator;
import org.dom4j.Element;

public class JointParser implements Parser<Joint> {
    private ValueParser a;

    public JointParser() {
    }

    public Joint parse(Element var1) {
        Joint var2 = new Joint();
        var2.setType(JointType.valueOf(var1.attributeValue("type")));
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (var5.getName().equals("condition")) {
                    var2.addCondition(this.parseCondition(var5));
                } else if (this.support(var5.getName())) {
                    var2.addJoint(this.parse(var5));
                }
            }
        }

        return var2;
    }

    public Condition parseCondition(Element var1) {
        Condition var2 = new Condition();
        var2.setOp(Op.valueOf(var1.attributeValue("op")));
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (this.a.support(var5.getName())) {
                    var2.setValue(this.a.parse(var5));
                    break;
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("joint");
    }

    public void setValueParser(ValueParser var1) {
        this.a = var1;
    }
}
