//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

public class ScoreCardSumLog extends DataLog {
    private String a;
    private Object b;

    public ScoreCardSumLog(String var1, Object var2) {
        this.a = var1;
        this.b = var2;
        this.msg = "---评分卡" + var1 + ",得分：" + var2;
    }

    public String getCardName() {
        return this.a;
    }

    public Object getValue() {
        return this.b;
    }
}
