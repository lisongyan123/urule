//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.repository.refactor;

public abstract class FieldItem implements Item {
    private String a;
    private String b;
    private String c;
    private String d;

    public FieldItem() {
    }

    public String getNewName() {
        return this.a;
    }

    public void setNewName(String var1) {
        this.a = var1;
    }

    public String getNewLabel() {
        return this.b;
    }

    public void setNewLabel(String var1) {
        this.b = var1;
    }

    public String getOldName() {
        return this.c;
    }

    public void setOldName(String var1) {
        this.c = var1;
    }

    public String getOldLabel() {
        return this.d;
    }

    public void setOldLabel(String var1) {
        this.d = var1;
    }
}
