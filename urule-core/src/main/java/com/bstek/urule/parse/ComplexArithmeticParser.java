//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.ArithmeticType;
import com.bstek.urule.model.rule.ComplexArithmetic;
import java.util.Iterator;
import org.dom4j.Element;

public class ComplexArithmeticParser implements Parser<ComplexArithmetic> {
    private ValueParser a;
    private ParenParser b;

    public ComplexArithmeticParser() {
    }

    public ComplexArithmetic parse(Element var1) {
        ComplexArithmetic var2 = new ComplexArithmetic();
        ArithmeticType var3 = ArithmeticType.valueOf(var1.attributeValue("type"));
        var2.setType(var3);
        Iterator var4 = var1.elements().iterator();

        while(var4.hasNext()) {
            Object var5 = var4.next();
            if (var5 != null && var5 instanceof Element) {
                Element var6 = (Element)var5;
                if (this.a.support(var6.getName())) {
                    var2.setValue(this.a.parse(var6));
                } else if (this.b.support(var6.getName())) {
                    var2.setValue(this.b.parse(var6));
                }
            }
        }

        return var2;
    }

    public void setValueParser(ValueParser var1) {
        this.a = var1;
    }

    public void setParenParser(ParenParser var1) {
        this.b = var1;
    }

    public boolean support(String var1) {
        return var1.equals("complex-arith");
    }
}
