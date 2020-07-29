//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.function.impl;

import com.bstek.urule.Utils;
import com.bstek.urule.model.function.Argument;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.runtime.WorkingMemory;
import java.math.BigDecimal;

public class CosFunctionDescriptor implements FunctionDescriptor {
    private boolean disabled = false;

    public CosFunctionDescriptor() {
    }

    public Argument getArgument() {
        Argument var1 = new Argument();
        var1.setName("对象");
        var1.setNeedProperty(true);
        return var1;
    }

    public Object doFunction(Object var1, String var2, WorkingMemory var3) {
        Object var4 = Utils.getObjectProperty(var1, var2);
        BigDecimal var5 = Utils.toBigDecimal(var4);
        return Math.cos(var5.doubleValue());
    }

    public String getName() {
        return "Cos";
    }

    public String getLabel() {
        return "求余弦";
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean var1) {
        this.disabled = var1;
    }
}
