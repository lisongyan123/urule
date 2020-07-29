//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.math;

import com.bstek.urule.model.rule.math.AbsoluteMath;
import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.parse.ValueParser;
import org.dom4j.Element;

public class AbsoluteMathParser extends MathParser {
    public AbsoluteMathParser(ValueParser var1) {
        super(var1);
    }

    public MathSign parse(Element var1) {
        AbsoluteMath var2 = new AbsoluteMath();
        var2.setValue(this.parseValue(var1));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("absolute-sign");
    }
}
