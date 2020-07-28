//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.repository.refactor;

public class CategoryItem implements Item {
    private String a;
    private String b;

    public CategoryItem() {
    }

    public String getOldCategory() {
        return this.a;
    }

    public void setOldCategory(String var1) {
        this.a = var1;
    }

    public String getNewCategory() {
        return this.b;
    }

    public void setNewCategory(String var1) {
        this.b = var1;
    }
}
