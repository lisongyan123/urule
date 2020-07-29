//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.math;

import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.RadicalMath;
import com.bstek.urule.parse.ValueParser;
import org.dom4j.Element;

public class RadicalMathParser extends MathParser {
    public RadicalMathParser(ValueParser var1) {
        super(var1);
    }

    public MathSign parse(Element var1) {
        RadicalMath var2 = new RadicalMath();
        var2.setValue(this.parseValue(var1));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("radical-sign");
    }
}
