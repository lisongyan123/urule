//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.model.library.Datatype;
import org.codehaus.jackson.annotate.JsonIgnore;

public class ConstantValue extends AbstractValue {
    private String constantName;
    private String constantLabel;
    private String constantCategory;
    private Datatype datatype;
    private ValueType valueType;

    public ConstantValue() {
        this.datatype = Datatype.String;
        this.valueType = ValueType.Constant;
    }

    public String getConstantName() {
        return this.constantName;
    }

    public void setConstantName(String var1) {
        this.constantName = var1;
    }

    public String getConstantLabel() {
        return this.constantLabel;
    }

    public void setConstantLabel(String var1) {
        this.constantLabel = var1;
    }

    public String getConstantCategory() {
        return this.constantCategory;
    }

    public void setConstantCategory(String var1) {
        this.constantCategory = var1;
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
        String var1 = "[常量]" + this.constantCategory + "." + this.constantLabel;
        if (this.arithmetic != null) {
            var1 = var1 + this.arithmetic.getId();
        }

        return var1;
    }
}
