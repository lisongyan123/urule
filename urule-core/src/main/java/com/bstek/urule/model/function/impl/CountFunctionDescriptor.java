//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.function.impl;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.function.Argument;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.runtime.WorkingMemory;
import java.util.Collection;

public class CountFunctionDescriptor implements FunctionDescriptor {
    private boolean disabled = false;

    public CountFunctionDescriptor() {
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean var1) {
        this.disabled = var1;
    }

    public String getLabel() {
        return "统计数量";
    }

    public String getName() {
        return "Count";
    }

    public Object doFunction(Object var1, String var2, WorkingMemory var3) {
        Collection var4 = null;
        if (var1 instanceof Collection) {
            var4 = (Collection)var1;
            return var4.size();
        } else {
            throw new RuleException("Function[count] parameter must be java.util.Collection type.");
        }
    }

    public Argument getArgument() {
        Argument var1 = new Argument();
        var1.setName("集合对象");
        return var1;
    }
}
