//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.function.impl;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.function.Argument;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.runtime.WorkingMemory;

public class UpdateFactFunctionDescriptor implements FunctionDescriptor {
    private boolean disabled = false;

    public UpdateFactFunctionDescriptor() {
    }

    public Argument getArgument() {
        Argument var1 = new Argument();
        var1.setName("要更新的对象");
        return var1;
    }

    public Object doFunction(Object var1, String var2, WorkingMemory var3) {
        if (var1 instanceof String) {
            String var4 = (String)var1;
            if (!var4.equals("参数") && !var4.equals("parameter")) {
                throw new RuleException("Unsupport parameter[" + var4 + "].");
            } else {
                return var3.update(var3.getParameters());
            }
        } else {
            return var3.update(var1);
        }
    }

    public String getName() {
        return "UpdateFact";
    }

    public String getLabel() {
        return "更新工作区对象";
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean var1) {
        this.disabled = var1;
    }
}
