//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.action;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.lhs.CommonFunctionParameter;
import com.bstek.urule.runtime.rete.Context;
import java.util.Map;

public class ExecuteCommonFunctionAction extends AbstractAction {
    private String a;
    private String b;
    private CommonFunctionParameter c;

    public ExecuteCommonFunctionAction() {
    }

    public ActionValue execute(Context var1, Map<String, Object> var2) {
        FunctionDescriptor var3 = null;
        if (Utils.getFunctionDescriptorMap().containsKey(this.a)) {
            var3 = Utils.findFunctionDescriptor(this.a);
        } else if (Utils.getFunctionDescriptorLabelMap().containsKey(this.b)) {
            var3 = (FunctionDescriptor)Utils.getFunctionDescriptorLabelMap().get(this.b);
        }

        if (var3 == null) {
            throw new RuleException("Function[" + this.a + "] not exist.");
        } else {
            String var4 = this.b == null ? this.a : this.b;
            Value var5 = null;
            Object var6 = null;
            if (this.c != null) {
                var5 = this.c.getObjectParameter();
                var6 = var1.getValueCompute().complexValueCompute(var5, var1, var2);
            }

            String var7 = null;
            if (var3.getArgument() != null && var3.getArgument().isNeedProperty()) {
                var7 = this.c.getProperty();
            }

            if (this.debug) {
                var1.getLogger().logExecuteFunction(var4, var6);
            }

            Object var8 = var3.doFunction(var6, var7, var1.getWorkingMemory());
            return var8 == null ? null : new ActionValueImpl(this.a, var8);
        }
    }

    public ActionType getActionType() {
        return ActionType.ExecuteCommonFunction;
    }

    public String getName() {
        return this.a;
    }

    public void setName(String var1) {
        this.a = var1;
    }

    public String getLabel() {
        return this.b;
    }

    public void setLabel(String var1) {
        this.b = var1;
    }

    public CommonFunctionParameter getParameter() {
        return this.c;
    }

    public void setParameter(CommonFunctionParameter var1) {
        this.c = var1;
    }
}
