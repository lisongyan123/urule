//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.Utils;
import com.bstek.urule.model.function.Argument;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.runtime.rete.EvaluationContext;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonIgnore;

public class CommonFunctionLeftPart implements LeftPart {
    @JsonIgnore
    private String id;
    private String name;
    private String label;
    private CommonFunctionParameter parameter;

    public CommonFunctionLeftPart() {
    }

    public Object evaluate(EvaluationContext var1, Map<String, Object> var2) {
        FunctionDescriptor var3 = Utils.findFunctionDescriptor(this.name);
        Value var4 = this.parameter.getObjectParameter();
        Object var5 = var1.getValueCompute().complexValueCompute(var4, var1, var2);
        Argument var6 = var3.getArgument();
        String var7 = null;
        if (var6.isNeedProperty()) {
            var7 = this.parameter.getProperty();
        }

        return var3.doFunction(var5, var7, var1.getWorkingMemory());
    }

    public String getId() {
        if (this.id == null) {
            this.id = this.label + "(" + this.parameter.getId() + ")";
        }

        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String var1) {
        this.label = var1;
    }

    public CommonFunctionParameter getParameter() {
        return this.parameter;
    }

    public void setParameter(CommonFunctionParameter var1) {
        this.parameter = var1;
    }
}
