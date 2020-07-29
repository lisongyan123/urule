//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

public class ScoreCardBean extends DataLog {
    private String a;

    public ScoreCardBean(String var1) {
        this.a = var1;
        this.msg = "---执行自定义评分卡得分计算Bean:" + var1;
    }

    public String getBean() {
        return this.a;
    }
}