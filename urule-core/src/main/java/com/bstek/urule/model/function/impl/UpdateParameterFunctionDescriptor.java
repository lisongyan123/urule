//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.function.impl;

import com.bstek.urule.model.function.Argument;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.runtime.WorkingMemory;

public class UpdateParameterFunctionDescriptor implements FunctionDescriptor {
    public UpdateParameterFunctionDescriptor() {
    }

    public Argument getArgument() {
        return null;
    }

    public Object doFunction(Object var1, String var2, WorkingMemory var3) {
        return var3.update(var3.getParameters());
    }

    public String getName() {
        return "UpdateParameter";
    }

    public String getLabel() {
        return "更新参数";
    }

    public boolean isDisabled() {
        return false;
    }
}
