//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.Utils;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.runtime.rete.EvaluationContext;
import java.math.BigDecimal;
import java.util.Map;

public class CalculateItem {
    private CalculateType type;
    private Value value;
    private boolean enableAssignment;
    private String assignTargetType;
    private String assignVariableCategory;
    private String assignVariable;
    private String assignVariableLabel;
    private Datatype assignDatatype;
    private String keyLabel;
    private String keyName;

    public CalculateItem() {
    }

    public void calculate(EvaluationContext var1, Map<String, Object> var2, Object var3, Map<String, CalculateData> var4) {
        switch(this.type) {
            case count:
                CalculateData var5 = (CalculateData)var4.get(this.type.toString());
                if (var5 == null) {
                    var5 = new CalculateData(this);
                    var4.put(this.type.name(), var5);
                } else {
                    var5.setCount(var5.getCount() + 1);
                }
                break;
            case avg:
                this.doCal(var1, var2, var4);
                break;
            case max:
                this.doCal(var1, var2, var4);
                break;
            case min:
                this.doCal(var1, var2, var4);
                break;
            case sum:
                this.doCal(var1, var2, var4);
                break;
            case collect:
                CalculateData var6 = (CalculateData)var4.get(this.type.toString());
                if (var6 == null) {
                    var6 = new CalculateData(this);
                    var6.addObject(var3);
                    var4.put(this.type.name(), var6);
                } else {
                    var6.addObject(var3);
                }
        }

    }

    private void doCal(EvaluationContext var1, Map<String, Object> var2, Map<String, CalculateData> var3) {
        Object var4 = var1.getValueCompute().complexValueCompute(this.value, var1, var2);
        BigDecimal var5 = null;
        if (var4 != null) {
            var5 = Utils.toBigDecimal(var4);
        }

        CalculateData var6 = (CalculateData)var3.get(this.type.name());
        if (var6 == null) {
            var6 = new CalculateData(this);
            var6.setValue(var5);
            var3.put(this.type.name(), var6);
        } else {
            var6.setCount(var6.getCount() + 1);
            if (var5 != null) {
                BigDecimal var7 = var6.getValue();
                if (var7 != null) {
                    int var8;
                    if (this.type.equals(CalculateType.max)) {
                        var8 = var5.compareTo(var7);
                        if (var8 == 1) {
                            var6.setValue(var5);
                        }
                    } else if (this.type.equals(CalculateType.min)) {
                        var8 = var5.compareTo(var7);
                        if (var8 == 0) {
                            var6.setValue(var5);
                        }
                    } else {
                        BigDecimal var9 = Utils.toBigDecimal(var7);
                        var6.setValue(var5.add(var9));
                    }
                } else {
                    var6.setValue(var5);
                }
            }
        }

    }

    public CalculateType getType() {
        return this.type;
    }

    public void setType(CalculateType var1) {
        this.type = var1;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }

    public boolean isEnableAssignment() {
        return this.enableAssignment;
    }

    public void setEnableAssignment(boolean var1) {
        this.enableAssignment = var1;
    }

    public String getAssignTargetType() {
        return this.assignTargetType;
    }

    public void setAssignTargetType(String var1) {
        this.assignTargetType = var1;
    }

    public String getAssignVariableCategory() {
        return this.assignVariableCategory;
    }

    public void setAssignVariableCategory(String var1) {
        this.assignVariableCategory = var1;
    }

    public String getAssignVariable() {
        return this.assignVariable;
    }

    public void setAssignVariable(String var1) {
        this.assignVariable = var1;
    }

    public String getAssignVariableLabel() {
        return this.assignVariableLabel;
    }

    public void setAssignVariableLabel(String var1) {
        this.assignVariableLabel = var1;
    }

    public Datatype getAssignDatatype() {
        return this.assignDatatype;
    }

    public void setAssignDatatype(Datatype var1) {
        this.assignDatatype = var1;
    }

    public String getKeyLabel() {
        return this.keyLabel;
    }

    public void setKeyLabel(String var1) {
        this.keyLabel = var1;
    }

    public String getKeyName() {
        return this.keyName;
    }

    public void setKeyName(String var1) {
        this.keyName = var1;
    }
}
