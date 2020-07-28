package com.bstek.urule.console.repository.reference;

public class VariableSearchItem implements SearchItem {
    private String a;
    private String b;
    private String c;

    public VariableSearchItem() {
    }

    public String getVarCategory() {
        return this.a;
    }

    public void setVarCategory(String var1) {
        this.a = var1;
    }

    public String getVarLabel() {
        return this.b;
    }

    public void setVarLabel(String var1) {
        this.b = var1;
    }

    public String getVarName() {
        return this.c;
    }

    public void setVarName(String var1) {
        this.c = var1;
    }
}
