//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

public class ExcecuteScoreCardLog extends DataLog {
    private int a;
    private Object b;

    public ExcecuteScoreCardLog(int var1, Object var2) {
        this.a = var1;
        this.b = var2;
        this.msg = "---行" + var1 + ",得分：" + var2;
    }

    public int getRowNumber() {
        return this.a;
    }

    public Object getValue() {
        return this.b;
    }
}
