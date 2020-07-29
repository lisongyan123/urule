//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.ArithmeticType;
import com.bstek.urule.model.rule.SimpleArithmetic;
import com.bstek.urule.model.rule.SimpleArithmeticValue;
import java.util.Iterator;
import org.dom4j.Element;

public class SimpleArithmeticParser implements Parser<SimpleArithmetic> {
    public SimpleArithmeticParser() {
    }

    public SimpleArithmetic parse(Element var1) {
        SimpleArithmetic var2 = new SimpleArithmetic();
        ArithmeticType var3 = ArithmeticType.valueOf(var1.attributeValue("type"));
        var2.setType(var3);
        SimpleArithmeticValue var4 = new SimpleArithmeticValue();
        var4.setContent(var1.attributeValue("value"));
        var2.setValue(var4);
        Iterator var5 = var1.elements().iterator();

        while(var5.hasNext()) {
            Object var6 = var5.next();
            if (var6 != null && var6 instanceof Element) {
                Element var7 = (Element)var6;
                if (this.support(var7.getName())) {
                    var4.setArithmetic(this.parse(var7));
                    break;
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("simple-arith");
    }
}
