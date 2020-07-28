package com.bstek.urule.console.repository.refactor;

public class VariableItem extends FieldItem {
    private String a;
    private String b;
    private String c;

    public VariableItem() {
    }

    public String getCategory() {
        return this.a;
    }

    public void setCategory(String var1) {
        this.a = var1;
    }

    public String getOldDatatype() {
        return this.b;
    }

    public void setOldDatatype(String var1) {
        this.b = var1;
    }

    public String getNewDatatype() {
        return this.c;
    }

    public void setNewDatatype(String var1) {
        this.c = var1;
    }
}
