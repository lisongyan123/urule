package com.bstek.urule.console.repository.reference;

public class ParameterSearchItem implements SearchItem {
    private String a;
    private String b;

    public ParameterSearchItem() {
    }

    public String getVarLabel() {
        return this.a;
    }

    public void setVarLabel(String var1) {
        this.a = var1;
    }

    public String getVarName() {
        return this.b;
    }

    public void setVarName(String var1) {
        this.b = var1;
    }
}