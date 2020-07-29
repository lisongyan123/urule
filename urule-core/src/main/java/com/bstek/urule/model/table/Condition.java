//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.table;

import com.bstek.urule.model.rule.Op;
import com.bstek.urule.model.rule.Value;

public class Condition {
    private Op op;
    private Value value;

    public Condition() {
    }

    public Op getOp() {
        return this.op;
    }

    public void setOp(Op var1) {
        this.op = var1;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }
}
