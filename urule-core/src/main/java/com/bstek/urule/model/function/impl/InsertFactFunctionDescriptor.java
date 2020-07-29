//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.function.impl;

import com.bstek.urule.model.function.Argument;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.runtime.WorkingMemory;

public class InsertFactFunctionDescriptor implements FunctionDescriptor {
    private boolean disabled = false;

    public InsertFactFunctionDescriptor() {
    }

    public Argument getArgument() {
        Argument var1 = new Argument();
        var1.setName("要插入的对象");
        return var1;
    }

    public Object doFunction(Object var1, String var2, WorkingMemory var3) {
        boolean var4 = var3.insert(var1);
        var3.update(var1);
        return var4;
    }

    public String getName() {
        return "InsertFact";
    }

    public String getLabel() {
        return "插入对象到工作区";
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean var1) {
        this.disabled = var1;
    }
}
