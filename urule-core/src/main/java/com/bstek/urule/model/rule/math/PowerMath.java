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

public class PowerMath implements MathSign {
    private Value power;
    private Value base;

    public PowerMath() {
    }

    public Object calculate(Context var1, Map<String, Object> var2) {
        Object var3 = var1.getValueCompute().complexValueCompute(this.power, var1, var2);
        Object var4 = var1.getValueCompute().complexValueCompute(this.base, var1, var2);
        BigDecimal var5 = Utils.toBigDecimal(var3);
        BigDecimal var6 = Utils.toBigDecimal(var4);
        return (new BigDecimal(Math.pow(var6.doubleValue(), var5.doubleValue()))).stripTrailingZeros();
    }

    public Value getPower() {
        return this.power;
    }

    public void setPower(Value var1) {
        this.power = var1;
    }

    public Value getBase() {
        return this.base;
    }

    public void setBase(Value var1) {
        this.base = var1;
    }

    public MathType getType() {
        return MathType.power;
    }

    public String getId() {
        return "[乘方]" + this.base.getId() + "^" + this.power.getId();
    }
}
