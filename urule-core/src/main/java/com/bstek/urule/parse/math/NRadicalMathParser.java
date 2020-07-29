//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.math;

import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.NRadicalMath;
import com.bstek.urule.parse.ValueParser;
import java.util.Iterator;
import org.dom4j.Element;

public class NRadicalMathParser extends MathParser {
    public NRadicalMathParser(ValueParser var1) {
        super(var1);
    }

    public MathSign parse(Element var1) {
        NRadicalMath var2 = new NRadicalMath();
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (var5.getName().equals("power")) {
                    var2.setPower(this.parseValue(var5));
                } else if (var5.getName().equals("value")) {
                    var2.setValue(this.parseValue(var5));
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("nradical-sign");
    }
}
