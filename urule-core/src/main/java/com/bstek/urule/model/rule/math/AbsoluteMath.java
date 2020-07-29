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

public class AbsoluteMath implements MathSign {
    private Value value;

    public AbsoluteMath() {
    }

    public Object calculate(Context var1, Map<String, Object> var2) {
        Object var3 = var1.getValueCompute().complexValueCompute(this.value, var1, var2);
        if (var3 instanceof Integer) {
            int var9 = (Integer)var3;
            return Math.abs(var9);
        } else if (var3 instanceof Double) {
            double var8 = (Double)var3;
            return Math.abs(var8);
        } else if (var3 instanceof Float) {
            float var7 = (Float)var3;
            return Math.abs(var7);
        } else if (var3 instanceof Long) {
            long var6 = (Long)var3;
            return Math.abs(var6);
        } else {
            BigDecimal var4 = Utils.toBigDecimal(var3);
            return (new BigDecimal(Math.abs(var4.doubleValue()))).stripTrailingZeros();
        }
    }

    public MathType getType() {
        return MathType.absolute;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }

    public String getId() {
        return "[求绝对值]|" + this.value.getId() + "|";
    }
}
