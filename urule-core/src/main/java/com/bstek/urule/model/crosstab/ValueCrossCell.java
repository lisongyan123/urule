//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.crosstab;

import com.bstek.urule.model.rule.Value;

public class ValueCrossCell extends com.bstek.urule.model.crosstab.CrossCell {
    private Value value;

    public ValueCrossCell() {
    }

    public String getType() {
        return "value";
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }
}
