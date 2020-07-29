//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.action;

public abstract class AbstractAction implements Action {
    private int a;
    protected boolean debug;

    public AbstractAction() {
    }

    public int compareTo(Action var1) {
        return var1.getPriority() - this.a;
    }

    public int getPriority() {
        return this.a;
    }

    public void setDebug(boolean var1) {
        this.debug = var1;
    }

    public void setPriority(int var1) {
        this.a = var1;
    }
}
