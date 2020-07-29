//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.Utils;
import com.bstek.urule.model.rule.Op;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.runtime.rete.EvaluationContext;
import java.math.BigDecimal;
import java.util.Map;

public class ConditionItem {
    private String left;
    private Op op;
    private Value value;

    public ConditionItem() {
    }

    public EvaluateResponse eval(Map<String, CalculateData> var1, EvaluationContext var2, Map<String, Object> var3) {
        EvaluateResponse var4 = new EvaluateResponse();
        if (!var1.containsKey(this.left)) {
            var4.setResult(false);
            var4.setRightResult("not null");
            return var4;
        } else {
            CalculateData var5 = (CalculateData)var1.get(this.left);
            Object var6 = var5.getResultValue();
            if (var6 == null) {
                var4.setResult(false);
                var4.setRightResult("not null");
                return var4;
            } else {
                Object var7 = var2.getValueCompute().complexValueCompute(this.value, var2, var3);
                if (var7 == null) {
                    var4.setResult(false);
                    var4.setRightResult(var6);
                    return var4;
                } else {
                    BigDecimal var8 = Utils.toBigDecimal(var6);
                    BigDecimal var9 = Utils.toBigDecimal(var7);
                    boolean var10 = false;
                    int var11 = var8.compareTo(var9);
                    if (this.op.equals(Op.Equals)) {
                        var10 = var11 == 0;
                    } else if (this.op.equals(Op.NotEquals)) {
                        var10 = var11 != 0;
                    } else if (this.op.equals(Op.LessThen)) {
                        var10 = var11 == -1;
                    } else if (this.op.equals(Op.LessThenEquals)) {
                        var10 = var11 == 0 || var11 == -1;
                    } else if (this.op.equals(Op.GreaterThen)) {
                        var10 = var11 == 1;
                    } else if (this.op.equals(Op.GreaterThenEquals)) {
                        var10 = var11 == 1 || var11 == 0;
                    }

                    var4.setOp(this.op);
                    var4.setResult(var10);
                    var4.setLeftResult(var8);
                    var4.setRightResult(var9);
                    return var4;
                }
            }
        }
    }

    public String getLeft() {
        return this.left;
    }

    public void setLeft(String var1) {
        this.left = var1;
    }

    public Op getOp() {
        return this.op;
    }

    public void setOp(Op var1) {
        this.op = var1;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }
}
