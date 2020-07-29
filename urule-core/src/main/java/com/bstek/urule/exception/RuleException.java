//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.exception;

public class RuleException extends RuntimeException {
    private static final long a = -8624533394127244753L;
    private String b;

    public RuleException() {
    }

    public RuleException(String var1) {
        super(var1);
    }

    public RuleException(Exception var1) {
        super(var1);
        var1.printStackTrace();
    }

    public RuleException(String var1, Exception var2) {
        super(var2);
        if (var1 != null) {
            var1 = "错误发生位置：" + var1;
            System.err.println(var1);
        }

        var2.printStackTrace();
        this.b = var1;
    }

    public String getTipMsg() {
        return this.b;
    }
}
