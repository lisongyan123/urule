//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import org.codehaus.jackson.annotate.JsonIgnore;

public class SimpleArithmetic extends Arithmetic {
    @JsonIgnore
    private String id;
    private SimpleArithmeticValue value;

    public SimpleArithmetic() {
    }

    public SimpleArithmeticValue getValue() {
        return this.value;
    }

    public void setValue(SimpleArithmeticValue var1) {
        this.value = var1;
    }

    public String getId() {
        if (this.id != null) {
            return this.id;
        } else {
            this.id = this.type.toString() + this.value.getId();
            return this.id;
        }
    }
}
