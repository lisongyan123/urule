//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.math;

import com.bstek.urule.Utils;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.runtime.rete.Context;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class FractionMath implements MathSign {
    private Value numerator;
    private Value denominator;

    public FractionMath() {
    }

    public Object calculate(Context var1, Map<String, Object> var2) {
        Object var3 = var1.getValueCompute().complexValueCompute(this.numerator, var1, var2);
        Object var4 = var1.getValueCompute().complexValueCompute(this.denominator, var1, var2);
        BigDecimal var5 = Utils.toBigDecimal(var3);
        BigDecimal var6 = Utils.toBigDecimal(var4);
        BigDecimal var7 = var5.divide(var6, 15, RoundingMode.HALF_UP).stripTrailingZeros();
        return var7.stripTrailingZeros();
    }

    public Value getNumerator() {
        return this.numerator;
    }

    public void setNumerator(Value var1) {
        this.numerator = var1;
    }

    public Value getDenominator() {
        return this.denominator;
    }

    public void setDenominator(Value var1) {
        this.denominator = var1;
    }

    public MathType getType() {
        return MathType.fraction;
    }

    public String getId() {
        return "[分数]" + this.numerator.getId() + "/" + this.denominator.getId();
    }
}
