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

public class StringLengthFunctionDescriptor implements FunctionDescriptor {
    private boolean disabled = false;

    public StringLengthFunctionDescriptor() {
    }

    public Argument getArgument() {
        Argument var1 = new Argument();
        var1.setName("对象");
        var1.setNeedProperty(true);
        return var1;
    }

    public Object doFunction(Object var1, String var2, WorkingMemory var3) {
        Object var4 = Utils.getObjectProperty(var1, var2);
        if (var4 == null) {
            return 0;
        } else if (!(var4 instanceof String)) {
            throw new RuleException("Function[StringLength] parameter value must be String.");
        } else {
            return var4.toString().length();
        }
    }

    public String getName() {
        return "StringLength";
    }

    public String getLabel() {
        return "计算字符长度";
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean var1) {
        this.disabled = var1;
    }
}
