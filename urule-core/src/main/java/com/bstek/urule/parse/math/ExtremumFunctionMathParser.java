//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.math;

import com.bstek.urule.model.rule.math.ExtremumMath;
import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.parse.ValueParser;
import org.dom4j.Element;

public class ExtremumFunctionMathParser extends MathParser {
    public ExtremumFunctionMathParser(ValueParser var1) {
        super(var1);
    }

    public boolean support(String var1) {
        return var1.equals("extremum");
    }

    public MathSign parse(Element var1) {
        ExtremumMath var2 = new ExtremumMath();
        var2.setName(var1.attributeValue("name"));
        var2.setValue1(this.parseValue(var1.element("value1")));
        var2.setValue2(this.parseValue(var1.element("value2")));
        return var2;
    }
}
