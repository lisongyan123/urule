//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.math;

import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.PiMath;
import com.bstek.urule.parse.ValueParser;
import org.dom4j.Element;

public class PiMathParser extends MathParser {
    public PiMathParser(ValueParser var1) {
        super(var1);
    }

    public boolean support(String var1) {
        return var1.equals("pi-sign");
    }

    public MathSign parse(Element var1) {
        return new PiMath();
    }
}
