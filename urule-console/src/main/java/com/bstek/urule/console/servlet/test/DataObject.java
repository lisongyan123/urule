//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.test;

import java.util.List;

public class DataObject {
    private String a;
    private List<ObjectField> b;

    public DataObject() {
    }

    public String getName() {
        return this.a;
    }

    public void setName(String var1) {
        this.a = var1;
    }

    public List<ObjectField> getFields() {
        return this.b;
    }

    public void setFields(List<ObjectField> var1) {
        this.b = var1;
    }

    public String toString() {
        return " [name=" + this.a + ", fields=" + this.b + "]";
    }
}
