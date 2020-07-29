//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.action.VariableAssignAction;
import com.bstek.urule.model.rule.ObjectValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.runtime.rete.EvaluationContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalculateData {
    private CalculateItem item;
    private int count = 1;
    private BigDecimal value;
    private Object resultValue;
    private boolean doAssignment;
    private List<Object> collectObjects = new ArrayList();

    public CalculateData(CalculateItem var1) {
        this.item = var1;
    }

    public Object getResultValue() {
        return this.resultValue;
    }

    public void buildValue(EvaluationContext var1, Map<String, Object> var2) {
        BigDecimal var3 = BigDecimal.valueOf((long)this.count);
        switch(this.item.getType()) {
            case count:
                this.resultValue = var3;
                break;
            case avg:
                if (this.value != null) {
                    BigDecimal var4 = this.value.divide(var3, 8, 4).stripTrailingZeros();
                    this.resultValue = var4;
                } else {
                    this.resultValue = null;
                }
                break;
            case max:
                this.resultValue = this.value;
                break;
            case min:
                this.resultValue = this.value;
                break;
            case sum:
                this.resultValue = this.value;
                break;
            case collect:
                this.resultValue = this.collectObjects;
        }

        if (this.item.isEnableAssignment() && !this.doAssignment) {
            ObjectValue var5 = new ObjectValue(this.resultValue);
            this.doAssignment(var5, var1, var2);
            this.doAssignment = true;
        }

    }

    private void doAssignment(Value var1, EvaluationContext var2, Map<String, Object> var3) {
        VariableAssignAction var4 = new VariableAssignAction();
        var4.setValue(var1);
        var4.setDatatype(this.item.getAssignDatatype());
        var4.setVariableName(this.item.getAssignVariable());
        var4.setVariableLabel(this.item.getAssignVariableLabel());
        var4.setVariableCategory(this.item.getAssignVariableCategory());
        var4.setKeyLabel(this.item.getKeyLabel());
        var4.setKeyName(this.item.getKeyName());
        var4.execute(var2, var3);
    }

    public void addObject(Object var1) {
        this.collectObjects.add(var1);
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int var1) {
        this.count = var1;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public void setValue(BigDecimal var1) {
        this.value = var1;
    }
}
