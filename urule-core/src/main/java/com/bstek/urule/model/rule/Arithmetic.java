//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

public abstract class Arithmetic {
    protected ArithmeticType type;

    public Arithmetic() {
    }

    public ArithmeticType getType() {
        return this.type;
    }

    public void setType(ArithmeticType var1) {
        this.type = var1;
    }

    public abstract String getId();
}
