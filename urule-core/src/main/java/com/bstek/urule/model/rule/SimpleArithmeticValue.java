//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

public class SimpleArithmeticValue {
    private String content;
    private SimpleArithmetic arithmetic;

    public SimpleArithmeticValue() {
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String var1) {
        this.content = var1;
    }

    public SimpleArithmetic getArithmetic() {
        return this.arithmetic;
    }

    public void setArithmetic(SimpleArithmetic var1) {
        this.arithmetic = var1;
    }

    public String getId() {
        String var1 = this.content;
        if (this.arithmetic != null) {
            var1 = var1 + this.arithmetic.getId();
        }

        return var1;
    }
}
