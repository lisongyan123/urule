//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.math;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.runtime.rete.Context;
import java.math.BigDecimal;
import java.util.Map;

public class TriangleFunctionMath implements MathSign {
    private String name;
    private Value value;

    public TriangleFunctionMath() {
    }

    public Object calculate(Context var1, Map<String, Object> var2) {
        Object var3 = var1.getValueCompute().complexValueCompute(this.value, var1, var2);
        double var4;
        if (this.name.equals("sin")) {
            var4 = Math.sin(Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("cos")) {
            var4 = Math.cos(Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("tan")) {
            var4 = Math.tan(Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("cot")) {
            var4 = 1.0D / Math.tan(Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("sec")) {
            var4 = 1.0D / Math.cos(Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("csc")) {
            var4 = 1.0D / Math.sin(Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("arcsin")) {
            var4 = Math.asin(Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("arccos")) {
            var4 = Math.acos(Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("arctan")) {
            var4 = Math.atan(Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("arccot")) {
            var4 = Math.atan(1.0D / Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("arcsec")) {
            var4 = Math.acos(1.0D / Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else if (this.name.equals("arccsc")) {
            var4 = Math.asin(1.0D / Utils.toBigDecimal(var3).doubleValue());
            return (new BigDecimal(var4)).stripTrailingZeros();
        } else {
            throw new RuleException("Unknow triangle function name :" + this.name + "");
        }
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public String getId() {
        return "[三角函数(" + this.name + ")](" + this.value.getId() + ")";
    }

    public MathType getType() {
        return MathType.triangle;
    }
}
