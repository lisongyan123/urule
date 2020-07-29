//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.math;

import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.TriangleFunctionMath;
import com.bstek.urule.parse.ValueParser;
import org.dom4j.Element;

public class TriangleFunctionMathParser extends MathParser {
    public TriangleFunctionMathParser(ValueParser var1) {
        super(var1);
    }

    public boolean support(String var1) {
        return var1.equals("triangle");
    }

    public MathSign parse(Element var1) {
        TriangleFunctionMath var2 = new TriangleFunctionMath();
        var2.setName(var1.attributeValue("name"));
        var2.setValue(this.parseValue(var1));
        return var2;
    }
}
