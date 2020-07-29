//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import org.codehaus.jackson.annotate.JsonIgnore;

public class VariableCategoryValue extends AbstractValue {
    private String variableCategory;
    private ValueType valueType;

    public VariableCategoryValue() {
        this.valueType = ValueType.VariableCategory;
    }

    public VariableCategoryValue(String var1) {
        this.valueType = ValueType.VariableCategory;
        this.variableCategory = var1;
    }

    public ValueType getValueType() {
        return this.valueType;
    }

    public void setVariableCategory(String var1) {
        this.variableCategory = var1;
    }

    @JsonIgnore
    public String getId() {
        String var1 = "[变量对象]" + this.variableCategory;
        if (this.arithmetic != null) {
            var1 = var1 + this.arithmetic.getId();
        }

        return var1;
    }

    public String getVariableCategory() {
        return this.variableCategory;
    }
}
