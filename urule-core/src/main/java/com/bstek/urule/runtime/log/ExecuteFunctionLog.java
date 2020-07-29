//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

public class ExecuteFunctionLog extends DataLog {
    private String a;

    public ExecuteFunctionLog(String var1, Object var2) {
        this.a = var1;
        this.msg = "***执行函数：" + var1 + ">" + (var2 == null ? "" : var2);
    }

    public String getFunctionName() {
        return this.a;
    }
}
