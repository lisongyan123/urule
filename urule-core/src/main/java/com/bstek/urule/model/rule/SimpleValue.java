//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import org.codehaus.jackson.annotate.JsonIgnore;

public class SimpleValue extends AbstractValue {
    private String content;
    private ValueType valueType;

    public SimpleValue() {
        this.valueType = ValueType.Input;
    }

    public ValueType getValueType() {
        return this.valueType;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String var1) {
        this.content = var1;
    }

    @JsonIgnore
    public String getId() {
        String var1 = "[字符]" + this.content;
        if (this.arithmetic != null) {
            var1 = var1 + this.arithmetic.getId();
        }

        return var1;
    }
}
