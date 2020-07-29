//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

public class ScoreCardLog extends DataLog {
    private String a;
    private String b;

    public ScoreCardLog(String var1, String var2) {
        this.a = var1;
        this.b = var2;
        if (var2.endsWith(".scc")) {
            this.a = "复杂评分卡";
            this.msg = "执行[" + this.a + "]:" + var2;
        } else {
            this.msg = "执行评分卡[" + this.a + "]:" + var2;
        }

    }

    public String getName() {
        return this.a;
    }

    public String getPath() {
        return this.b;
    }
}
