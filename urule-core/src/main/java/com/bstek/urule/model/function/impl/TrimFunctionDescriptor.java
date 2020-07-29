//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.function.impl;

import com.bstek.urule.Utils;
import com.bstek.urule.model.function.Argument;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.runtime.WorkingMemory;

public class TrimFunctionDescriptor implements FunctionDescriptor {
    private boolean disabled = false;

    public TrimFunctionDescriptor() {
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean var1) {
        this.disabled = var1;
    }

    public String getLabel() {
        return "字符去空格";
    }

    public String getName() {
        return "Trim";
    }

    public Object doFunction(Object var1, String var2, WorkingMemory var3) {
        Object var4 = Utils.getObjectProperty(var1, var2);
        return var4 == null ? "null" : var4.toString().trim();
    }

    public Argument getArgument() {
        Argument var1 = new Argument();
        var1.setName("对象");
        var1.setNeedProperty(true);
        return var1;
    }
}
