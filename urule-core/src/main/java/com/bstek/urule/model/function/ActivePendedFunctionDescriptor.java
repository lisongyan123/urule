//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.function;

import com.bstek.urule.runtime.WorkingMemory;

public class ActivePendedFunctionDescriptor implements FunctionDescriptor {
    private boolean enableActivePendedGroupAndExecute;

    public ActivePendedFunctionDescriptor() {
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
            if (this.enableActivePendedGroupAndExecute) {
                var3.activePendedGroupAndExecute(var4);
            } else {
                var3.activePendedGroup(var4);
            }

            return null;
        }
    }

    public String getName() {
        return "ActiveAgenda";
    }

    public String getLabel() {
        return "激活执行组";
    }

    public void setEnableActivePendedGroupAndExecute(boolean var1) {
        this.enableActivePendedGroupAndExecute = var1;
    }

    public boolean isDisabled() {
        return false;
    }
}
