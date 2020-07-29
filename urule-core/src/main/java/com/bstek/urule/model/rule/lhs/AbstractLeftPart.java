//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.runtime.rete.EvaluationContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public abstract class AbstractLeftPart implements LeftPart {
    protected String id;
    protected String variableName;
    protected String variableLabel;
    protected String variableCategory;

    public AbstractLeftPart() {
    }

    protected ExprValue computeValue(EvaluationContext var1, Map<String, Object> var2) {
        ExprValue var3 = new ExprValue();
        Collection var4 = this.getTargetFacts(var1, var2);
        int var5 = var4.size();
        int var6 = 0;
        int var7 = 0;
        ArrayList var8 = new ArrayList();
        Iterator var9 = var4.iterator();

        while(var9.hasNext()) {
            Object var10 = var9.next();
            boolean var11 = true;
            if (var11) {
                ++var6;
                var8.add(var10);
            } else {
                ++var7;
            }
        }

        var3.setFacts(var8);
        var3.setMatch(var6);
        var3.setTotal(var5);
        var3.setNotMatch(var7);
        return var3;
    }

    private Collection<?> getTargetFacts(EvaluationContext var1, Map<String, Object> var2) {
        String var3 = var1.getVariableCategoryClass(this.variableCategory);
        Object var4 = var2.get(var3);
        Object var5 = Utils.getObjectProperty(var4, this.variableName);
        if (var5 == null) {
            throw new RuleException("Collection value can not be null.");
        } else if (var5 instanceof Collection) {
            return (Collection)var5;
        } else {
            throw new RuleException("Value is not collection type.");
        }
    }

    public String getVariableCategory() {
        return this.variableCategory;
    }

    public void setVariableCategory(String var1) {
        this.variableCategory = var1;
    }

    public String getVariableName() {
        return this.variableName;
    }

    public void setVariableName(String var1) {
        this.variableName = var1;
    }

    public String getVariableLabel() {
        return this.variableLabel;
    }

    public void setVariableLabel(String var1) {
        this.variableLabel = var1;
    }
}
