package com.bstek.urule.console.repository.scenario;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;

public class DataField {
    private String a;
    private String b;
    private Datatype c;
    private Op d;

    public DataField() {
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

    public Op getOp() {
        return this.d;
    }

    public void setOp(Op var1) {
        this.d = var1;
    }

    public Datatype getDatatype() {
        return this.c;
    }

    public void setDatatype(Datatype var1) {
        this.c = var1;
    }
}
