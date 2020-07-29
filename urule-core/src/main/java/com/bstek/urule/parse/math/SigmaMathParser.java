//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.math;

import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.SigmaMath;
import com.bstek.urule.parse.ValueParser;
import java.util.Iterator;
import org.dom4j.Element;

public class SigmaMathParser extends MathParser {
    public SigmaMathParser(ValueParser var1) {
        super(var1);
    }

    public boolean support(String var1) {
        return var1.equals("sigma-sign");
    }

    public MathSign parse(Element var1) {
        SigmaMath var2 = new SigmaMath();
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (var5.getName().equals("expr")) {
                    var2.setExpr(this.parseValue(var5));
                } else if (var5.getName().equals("ivalue")) {
                    var2.setIvalue(this.parseValue(var5));
                } else if (var5.getName().equals("superior")) {
                    var2.setSuperior(this.parseValue(var5));
                }
            }
        }

        return var2;
    }
}
