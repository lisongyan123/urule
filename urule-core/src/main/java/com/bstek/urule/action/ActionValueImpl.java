//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.action;

public class ActionValueImpl implements ActionValue {
    private String a;
    private Object b;

    public ActionValueImpl(String var1, Object var2) {
        this.a = var1;
        this.b = var2;
    }

    public String getActionId() {
        return this.a;
    }

    public Object getValue() {
        return this.b;
    }
}
