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

public class SigmaMath implements MathSign {
    private Value ivalue;
    private Value superior;
    private Value expr;

    public SigmaMath() {
    }

    public Object calculate(Context var1, Map<String, Object> var2) {
        Object var3 = var1.getValueCompute().complexValueCompute(this.ivalue, var1, var2);
        Object var4 = var1.getValueCompute().complexValueCompute(this.superior, var1, var2);
        int var5 = Utils.toBigDecimal(var3).intValue();
        int var6 = Utils.toBigDecimal(var4).intValue();
        BigDecimal var7 = new BigDecimal(0);

        for(int var8 = var5; var8 <= var6; ++var8) {
            var1.getWorkingMemory().getParameters().put("__math_sigma_step_index_", var8);
            Object var9 = var1.getValueCompute().complexValueCompute(this.expr, var1, var2);
            BigDecimal var10 = Utils.toBigDecimal(var9);
            var7 = var7.add(var10);
        }

        return var7.stripTrailingZeros();
    }

    public Value getIvalue() {
        return this.ivalue;
    }

    public void setIvalue(Value var1) {
        this.ivalue = var1;
    }

    public Value getSuperior() {
        return this.superior;
    }

    public void setSuperior(Value var1) {
        this.superior = var1;
    }

    public Value getExpr() {
        return this.expr;
    }

    public void setExpr(Value var1) {
        this.expr = var1;
    }

    public MathType getType() {
        return MathType.sigma;
    }

    public String getId() {
        return "[求和]Σ" + this.ivalue.getId() + "|" + this.superior.getId() + "|" + this.expr.getId();
    }
}
