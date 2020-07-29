//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.ParenValue;
import java.util.Iterator;
import org.dom4j.Element;

public class ParenParser implements Parser<ParenValue> {
    private ValueParser a;
    private ComplexArithmeticParser b;

    public ParenParser() {
    }

    public ParenValue parse(Element var1) {
        ParenValue var2 = new ParenValue();
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (this.a.support(var5.getName())) {
                    var2.setValue(this.a.parse(var5));
                } else if (this.b.support(var5.getName())) {
                    var2.setArithmetic(this.b.parse(var5));
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("paren");
    }

    public void setValueParser(ValueParser var1) {
        this.a = var1;
    }

    public void setArithmeticParser(ComplexArithmeticParser var1) {
        this.b = var1;
    }
}
