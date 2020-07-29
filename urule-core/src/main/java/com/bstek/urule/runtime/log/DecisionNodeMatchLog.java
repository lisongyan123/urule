//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

import com.bstek.urule.model.flow.FlowNode;

public class DecisionNodeMatchLog extends DataLog {
    private String a;
    private String b;
    private String c;

    public DecisionNodeMatchLog(FlowNode var1, String var2, String var3) {
        this.b = var1.getName();
        this.a = var2;
        this.c = var3;
        if (var3 == null) {
            this.msg = "决策节点【" + this.b + "】所有分支上的条件都不满足.";
        } else {
            this.msg = "决策节点【" + this.b + "】分支【" + var3 + "】上的条件满足.";
        }

    }

    public String getFile() {
        return this.a;
    }

    public void setFile(String var1) {
        this.a = var1;
    }

    public String getNodeName() {
        return this.b;
    }

    public void setNodeName(String var1) {
        this.b = var1;
    }

    public String getTo() {
        return this.c;
    }

    public void setTo(String var1) {
        this.c = var1;
    }
}
