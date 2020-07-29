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

public class LogMath implements MathSign {
    private Value baseValue;
    private Value value;

    public LogMath() {
    }

    public Object calculate(Context var1, Map<String, Object> var2) {
        Object var3 = var1.getValueCompute().complexValueCompute(this.value, var1, var2);
        Object var4 = var1.getValueCompute().complexValueCompute(this.baseValue, var1, var2);
        double var5 = Utils.toBigDecimal(var3).doubleValue();
        double var7 = Utils.toBigDecimal(var4).doubleValue();
        return (new BigDecimal(Math.log(var5) / Math.log(var7))).stripTrailingZeros();
    }

    public String getId() {
        return "[对数]" + this.baseValue + "," + this.value + "";
    }

    public Value getBaseValue() {
        return this.baseValue;
    }

    public void setBaseValue(Value var1) {
        this.baseValue = var1;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }

    public MathType getType() {
        return MathType.log;
    }
}
