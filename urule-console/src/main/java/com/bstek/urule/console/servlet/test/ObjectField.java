//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.test;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;

public class ObjectField {
    private String a;
    private String b;
    private String c;
    private Datatype d;
    private Op e;

    public ObjectField() {
    }

    public String getName() {
        return this.a;
    }

    public void setName(String var1) {
        this.a = var1;
    }

    public String getLabel() {
        return this.b;
    }

    public void setLabel(String var1) {
        this.b = var1;
    }

    public String getValue() {
        return this.c;
    }

    public void setValue(String var1) {
        this.c = var1;
    }

    public Datatype getDatatype() {
        return this.d;
    }

    public void setDatatype(Datatype var1) {
        this.d = var1;
    }

    public Op getOp() {
        return this.e;
    }

    public void setOp(Op var1) {
        this.e = var1;
    }

    public String toString() {
        String var1 = "{name=" + this.a + ", label=" + this.b + ", value=" + this.c;
        if (this.e != null) {
            var1 = var1 + ", op=" + this.e;
        }

        var1 = var1 + "}";
        return var1;
    }
}

