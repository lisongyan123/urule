//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

public class SignIValue extends AbstractValue {
    public SignIValue() {
    }

    public ValueType getValueType() {
        return ValueType.SignI;
    }

    public String getId() {
        String var1 = "[i]";
        if (this.arithmetic != null) {
            var1 = var1 + this.arithmetic.getId();
        }

        return var1;
    }
}
