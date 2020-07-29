//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.function;

import com.bstek.urule.runtime.WorkingMemory;

public class ActiveAndExecutePendedFunctionDescriptor implements FunctionDescriptor {
    public ActiveAndExecutePendedFunctionDescriptor() {
    }

    public Argument getArgument() {
        Argument var1 = new Argument();
        var1.setName("执行组名");
        var1.setNeedProperty(false);
        return var1;
    }

    public Object doFunction(Object var1, String var2, WorkingMemory var3) {
        if (var1 == null) {
            return null;
        } else {
            String var4 = var1.toString();
            var3.activePendedGroupAndExecute(var4);
            return null;
        }
    }

    public String getName() {
        return "ActivePended";
    }

    public String getLabel() {
        return "激活执行组并执行";
    }

    public boolean isDisabled() {
        return false;
    }
}
