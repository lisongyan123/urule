package com.bstek.urule.console.repository.refactor;

public class BeanItem implements Item {
    private String a;
    private String b;
    private String c;
    private String d;

    public BeanItem() {
    }

    public String getOldBeanId() {
        return this.a;
    }

    public void setOldBeanId(String var1) {
        this.a = var1;
    }

    public String getNewBeanId() {
        return this.b;
    }

    public void setNewBeanId(String var1) {
        this.b = var1;
    }

    public String getOldBeanLabel() {
        return this.c;
    }

    public void setOldBeanLabel(String var1) {
        this.c = var1;
    }

    public String getNewBeanLabel() {
        return this.d;
    }

    public void setNewBeanLabel(String var1) {
        this.d = var1;
    }
}
