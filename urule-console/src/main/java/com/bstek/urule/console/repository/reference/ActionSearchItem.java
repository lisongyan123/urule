package com.bstek.urule.console.repository.reference;

public class ActionSearchItem implements SearchItem {
    private String a;
    private String b;
    private String c;
    private String d;

    public ActionSearchItem() {
    }

    public String getBeanLabel() {
        return this.a;
    }

    public void setBeanLabel(String var1) {
        this.a = var1;
    }

    public String getBeanName() {
        return this.b;
    }

    public void setBeanName(String var1) {
        this.b = var1;
    }

    public String getMethodLabel() {
        return this.c;
    }

    public void setMethodLabel(String var1) {
        this.c = var1;
    }

    public String getMethodName() {
        return this.d;
    }

    public void setMethodName(String var1) {
        this.d = var1;
    }
}
