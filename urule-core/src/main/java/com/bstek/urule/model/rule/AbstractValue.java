//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

public abstract class AbstractValue implements Value {
    protected ComplexArithmetic arithmetic;

    public AbstractValue() {
    }

    public ComplexArithmetic getArithmetic() {
        return this.arithmetic;
    }

    public void setArithmetic(ComplexArithmetic var1) {
        this.arithmetic = var1;
    }
}
