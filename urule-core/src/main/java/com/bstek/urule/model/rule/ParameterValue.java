//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.model.library.Datatype;
import org.codehaus.jackson.annotate.JsonIgnore;

public class ParameterValue extends AbstractValue {
    private String keyName;
    private String keyLabel;
    private Datatype datatype;
    private String variableName;
    private String variableLabel;
    private ValueType valueType;

    public ParameterValue() {
        this.valueType = ValueType.Parameter;
    }

    public ValueType getValueType() {
        return this.valueType;
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

    public String getKeyName() {
        return this.keyName;
    }

    public void setKeyName(String var1) {
        this.keyName = var1;
    }

    public String getKeyLabel() {
        return this.keyLabel;
    }

    public void setKeyLabel(String var1) {
        this.keyLabel = var1;
    }

    public Datatype getDatatype() {
        return this.datatype;
    }

    public void setDatatype(Datatype var1) {
        this.datatype = var1;
    }

    @JsonIgnore
    public String getId() {
        String var1 = "[P]参数";
        if (this.keyLabel != null) {
            var1 = var1 + "." + this.keyLabel;
        }

        var1 = var1 + "." + this.variableLabel;
        if (this.arithmetic != null) {
            var1 = var1 + this.arithmetic.getId();
        }

        return var1;
    }
}
