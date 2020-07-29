//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.math;

import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.UpRoundMath;
import com.bstek.urule.parse.ValueParser;
import org.dom4j.Element;

public class UpRoundMathParser extends MathParser {
    public UpRoundMathParser(ValueParser var1) {
        super(var1);
    }

    public boolean support(String var1) {
        return var1.equals("up-round");
    }

    public MathSign parse(Element var1) {
        UpRoundMath var2 = new UpRoundMath();
        var2.setValue(this.parseValue(var1));
        return var2;
    }
}
