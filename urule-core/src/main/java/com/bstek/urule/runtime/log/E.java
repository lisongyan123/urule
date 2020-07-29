//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

import com.bstek.urule.model.flow.ActionNode;
import com.bstek.urule.model.flow.DecisionNode;
import com.bstek.urule.model.flow.EndNode;
import com.bstek.urule.model.flow.FlowNode;
import com.bstek.urule.model.flow.ForkNode;
import com.bstek.urule.model.flow.JoinNode;
import com.bstek.urule.model.flow.RuleNode;
import com.bstek.urule.model.flow.RulePackageNode;
import com.bstek.urule.model.flow.ScriptNode;
import com.bstek.urule.model.flow.StartNode;

public class FlowNodeLog extends DataLog {
    private String a;
    private String b;
    private String c;
    private boolean d;

    public FlowNodeLog(FlowNode var1, String var2, boolean var3) {
        this.d = var3;
        this.b = var1.getName();
        this.a = var2;
        if (var1 instanceof ActionNode) {
            this.c = "动作节点";
        } else if (var1 instanceof DecisionNode) {
            this.c = "决策节点";
        } else if (var1 instanceof ScriptNode) {
            this.c = "脚本节点";
        } else if (var1 instanceof RuleNode) {
            this.c = "规则节点";
        } else if (var1 instanceof RulePackageNode) {
            this.c = "知识包节点";
        } else if (var1 instanceof EndNode) {
            this.c = "结束节点";
        } else if (var1 instanceof StartNode) {
            this.c = "开始节点";
        } else if (var1 instanceof ForkNode) {
            this.c = "分支节点";
        } else if (var1 instanceof JoinNode) {
            this.c = "聚合节点";
        }

        if (var3) {
            this.msg = ">>>进入" + this.c + "：" + this.b;
        } else {
            this.msg = ">>>离开" + this.c + "：" + this.b;
        }

    }

    public String getNodeName() {
        return this.b;
    }

    public String getNodeType() {
        return this.c;
    }

    public String getFile() {
        return this.a;
    }

    public boolean isEnter() {
        return this.d;
    }
}