//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.model.library.Datatype;
import org.codehaus.jackson.annotate.JsonIgnore;

public class VariableValue extends AbstractValue {
    private String variableName;
    private String variableLabel;
    private String variableCategory;
    private Datatype datatype;
    private ValueType valueType;

    public VariableValue() {
        this.valueType = ValueType.Variable;
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

    public String getVariableCategory() {
        return this.variableCategory;
    }

    public void setVariableCategory(String var1) {
        this.variableCategory = var1;
    }

    public Datatype getDatatype() {
        return this.datatype;
    }

    public void setDatatype(Datatype var1) {
        this.datatype = var1;
    }

    public ValueType getValueType() {
        return this.valueType;
    }

    @JsonIgnore
    public String getId() {
        String var1 = "[变量]" + this.variableCategory + "." + this.variableLabel;
        if (this.arithmetic != null) {
            var1 = var1 + this.arithmetic.getId();
        }

        return var1;
    }
}
