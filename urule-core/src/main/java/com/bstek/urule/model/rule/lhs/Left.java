//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.model.rule.ComplexArithmetic;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Left {
    @JsonIgnore
    private String id;
    private LeftPart leftPart;
    private LeftType type;
    private ComplexArithmetic arithmetic;

    public Left() {
    }

    public LeftPart getLeftPart() {
        return this.leftPart;
    }

    public void setLeftPart(LeftPart var1) {
        this.leftPart = var1;
    }

    public ComplexArithmetic getArithmetic() {
        return this.arithmetic;
    }

    public void setArithmetic(ComplexArithmetic var1) {
        this.arithmetic = var1;
    }

    public LeftType getType() {
        return this.type;
    }

    public void setType(LeftType var1) {
        this.type = var1;
    }

    public String getId() {
        if (this.id == null) {
            this.id = this.leftPart.getId();
            if (this.arithmetic != null) {
                this.id = this.id + this.arithmetic.getId();
            }
        }

        return this.id;
    }
}
