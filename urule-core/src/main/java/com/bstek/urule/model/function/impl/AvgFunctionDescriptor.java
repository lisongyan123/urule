//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.function.impl;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.function.Argument;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.runtime.WorkingMemory;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

public class AvgFunctionDescriptor implements FunctionDescriptor {
    private boolean disabled = false;

    public AvgFunctionDescriptor() {
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean var1) {
        this.disabled = var1;
    }

    public String getName() {
        return "Avg";
    }

    public String getLabel() {
        return "求平均值";
    }

    public Object doFunction(Object var1, String var2, WorkingMemory var3) {
        Collection var4 = null;
        if (var1 instanceof Collection) {
            var4 = (Collection)var1;
            BigDecimal var5 = null;
            Iterator var6 = var4.iterator();

            while(var6.hasNext()) {
                Object var7 = var6.next();
                Object var8 = Utils.getObjectProperty(var7, var2);
                BigDecimal var9 = Utils.toBigDecimal(var8);
                if (var5 == null) {
                    var5 = var9;
                } else {
                    var5 = var5.add(var9);
                }
            }

            BigDecimal var10 = var5.divide(new BigDecimal(var4.size()), 12, 4);
            return var10.doubleValue();
        } else {
            throw new RuleException("Function[avg] parameter must be java.util.Collection type.");
        }
    }

    public Argument getArgument() {
        Argument var1 = new Argument();
        var1.setName("集合对象");
        var1.setNeedProperty(true);
        return var1;
    }
}
