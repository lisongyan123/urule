//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.monitor;

public class IODataField {
    private String a;
    private String b;
    private Object c;

    public IODataField() {
    }

    public String getName() {
        return this.a;
    }

    public void setName(String var1) {
        this.a = var1;
    }

    public String getLabel() {
        return this.b;
    }

    public void setLabel(String var1) {
        this.b = var1;
    }

    public Object getValue() {
        return this.c;
    }

    public void setValue(Object var1) {
        this.c = var1;
    }

    public String toString() {
        return "IODataField [name=" + this.a + ", label=" + this.b + ", value=" + this.c + "]";
    }
}
