//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

public class ValueAssignLog extends DataLog {
    private String a;
    private Object b;

    public ValueAssignLog(String var1, Object var2) {
        this.a = var1;
        this.msg = "###变量赋值：" + var1 + "=" + var2;
    }

    public String getLeft() {
        return this.a;
    }

    public Object getRight() {
        return this.b;
    }
}
