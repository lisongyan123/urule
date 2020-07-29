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

public class ExtremumMath implements MathSign {
    private String name;
    private Value value1;
    private Value value2;

    public ExtremumMath() {
    }

    public Object calculate(Context var1, Map<String, Object> var2) {
        Object var3 = var1.getValueCompute().complexValueCompute(this.value1, var1, var2);
        Object var4 = var1.getValueCompute().complexValueCompute(this.value2, var1, var2);
        if (this.name.equals("max")) {
            return this.doMax(var3, var4);
        } else if (this.name.equals("min")) {
            return this.doMin(var3, var4);
        } else {
            throw new RuleException("不支持的极值函数：" + this.name);
        }
    }

    private BigDecimal doMax(Object var1, Object var2) {
        BigDecimal var3 = Utils.toBigDecimal(var1);
        BigDecimal var4 = Utils.toBigDecimal(var2);
        return var3.compareTo(var4) > 0 ? var3 : var4;
    }

    private BigDecimal doMin(Object var1, Object var2) {
        BigDecimal var3 = Utils.toBigDecimal(var1);
        BigDecimal var4 = Utils.toBigDecimal(var2);
        return var3.compareTo(var4) < 1 ? var3 : var4;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public Value getValue1() {
        return this.value1;
    }

    public void setValue1(Value var1) {
        this.value1 = var1;
    }

    public Value getValue2() {
        return this.value2;
    }

    public void setValue2(Value var1) {
        this.value2 = var1;
    }

    public String getId() {
        return "[极值(" + this.name + ")](" + this.value1.getId() + "," + this.value2.getId() + ")";
    }

    public MathType getType() {
        return MathType.extremum;
    }
}
