//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ParenValue extends AbstractValue {
    private Value value;

    public ParenValue() {
    }

    @JsonIgnore
    public String getId() {
        String var1 = "(";
        if (this.value != null) {
            var1 = var1 + this.value.getId();
        }

        var1 = var1 + ")";
        if (this.arithmetic != null) {
            var1 = var1 + this.arithmetic.getId();
        }

        return var1;
    }

    public ValueType getValueType() {
        return ValueType.Paren;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }
}
