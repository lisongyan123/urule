//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.action;

import com.bstek.urule.Utils;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.runtime.rete.Context;
import com.bstek.urule.runtime.rete.ValueCompute;
import java.util.Map;

public class ConsolePrintAction extends AbstractAction {
    private Value a;
    private ActionType b;

    public ConsolePrintAction() {
        this.b = ActionType.ConsolePrint;
    }

    public ActionValue execute(Context var1, Map<String, Object> var2) {
        if (!Utils.isDebug()) {
            return null;
        } else {
            ValueCompute var3 = (ValueCompute)var1.getApplicationContext().getBean("urule.valueCompute");
            Object var4 = var3.complexValueCompute(this.a, var1, var2);
            var1.getLogger().logConsoleOutput(var4);
            return null;
        }
    }

    public Value getValue() {
        return this.a;
    }

    public void setValue(Value var1) {
        this.a = var1;
    }

    public ActionType getActionType() {
        return this.b;
    }
}
