//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.model.rule.lhs.CommonFunctionParameter;
import org.codehaus.jackson.annotate.JsonIgnore;

public class CommonFunctionValue extends AbstractValue {
    @JsonIgnore
    private String id;
    private String name;
    private String label;
    private CommonFunctionParameter parameter;
    private ValueType valueType;

    public CommonFunctionValue() {
        this.valueType = ValueType.CommonFunction;
    }

    public String getId() {
        if (this.id == null) {
            this.id = "[函数]" + this.label + "(" + this.parameter.getId() + ")";
            if (this.arithmetic != null) {
                this.id = this.id + this.arithmetic.getId();
            }
        }

        return this.id;
    }

    public ValueType getValueType() {
        return this.valueType;
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

    public void setValueType(ValueType var1) {
        this.valueType = var1;
    }

    public CommonFunctionParameter getParameter() {
        return this.parameter;
    }

    public void setParameter(CommonFunctionParameter var1) {
        this.parameter = var1;
    }
}
