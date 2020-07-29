//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

public class ExecuteBeanMethodLog extends DataLog {
    private String a;

    public ExecuteBeanMethodLog(String var1, String var2) {
        this.a = var1;
        this.msg = var1 + "(" + var2 + ")";
    }

    public String getMethodInfo() {
        return this.a;
    }
}
