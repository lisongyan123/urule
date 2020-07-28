package com.bstek.urule.console.repository.reference;

public class ConstantSearchItem implements SearchItem {
    private String a;
    private String b;
    private String c;

    public ConstantSearchItem() {
    }

    public String getConstCategoryLabel() {
        return this.a;
    }

    public void setConstCategoryLabel(String var1) {
        this.a = var1;
    }

    public String getConstLabel() {
        return this.b;
    }

    public void setConstLabel(String var1) {
        this.b = var1;
    }

    public String getConstName() {
        return this.c;
    }

    public void setConstName(String var1) {
        this.c = var1;
    }
}
