//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

public class Path {
    private boolean a;
    private Activity b;

    public Path(Activity var1) {
        this.b = var1;
        if (var1 instanceof JoinActivity) {
            JoinActivity var2 = (JoinActivity)var1;
            var2.addFromPath(this);
        }

    }

    public Activity getTo() {
        return this.b;
    }

    public boolean isPassed() {
        return this.a;
    }

    public void setPassed(boolean var1) {
        this.a = var1;
    }
}
