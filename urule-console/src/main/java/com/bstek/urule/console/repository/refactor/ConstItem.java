//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.repository.refactor;

public class ConstItem extends FieldItem {
    private String a;
    private String b;
    private String c;

    public ConstItem() {
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

    public String getCategory() {
        return this.a;
    }

    public void setCategory(String var1) {
        this.a = var1;
    }
}
