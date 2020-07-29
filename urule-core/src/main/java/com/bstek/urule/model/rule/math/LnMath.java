//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.math;

import com.bstek.urule.Utils;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.runtime.rete.Context;
import java.math.BigDecimal;
import java.util.Map;

public class LnMath implements MathSign {
    private Value value;

    public LnMath() {
    }

    public Object calculate(Context var1, Map<String, Object> var2) {
        Object var3 = var1.getValueCompute().complexValueCompute(this.value, var1, var2);
        double var4 = Utils.toBigDecimal(var3).doubleValue();
        return (new BigDecimal(Math.log(var4))).stripTrailingZeros();
    }

    public String getId() {
        return "[自然对数]" + this.value + "";
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }

    public MathType getType() {
        return MathType.ln;
    }
}
