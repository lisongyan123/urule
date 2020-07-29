//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.model.rule.math.MathSign;

public class MathValue extends AbstractValue {
    private MathSign mathSign;

    public MathValue() {
    }

    public MathSign getMathSign() {
        return this.mathSign;
    }

    public void setMathSign(MathSign var1) {
        this.mathSign = var1;
    }

    public ValueType getValueType() {
        return ValueType.Math;
    }

    public String getId() {
        String var1 = "[数学符号](" + this.mathSign.getId() + ")";
        if (this.arithmetic != null) {
            var1 = var1 + this.arithmetic.getId();
        }

        return var1;
    }
}
