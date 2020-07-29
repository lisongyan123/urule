//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;

public class RulePackageNode extends BindingNode {
    private FlowNodeType type;
    private String packageId;
    private String project;

    public RulePackageNode() {
        this.type = FlowNodeType.RulePackage;
    }

    public RulePackageNode(String var1) {
        super(var1);
        this.type = FlowNodeType.RulePackage;
    }

    public FlowNodeType getType() {
        return this.type;
    }

    public void enterNode(FlowContext var1, FlowInstance var2) {
        var2.setCurrentNode(this);
        this.executeNodeEvent(EventType.enter, var1, var2);
        this.executeKnowledgePackage(var1, var2);
        this.executeNodeEvent(EventType.leave, var1, var2);
        this.leave((String)null, var1, var2);
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String var1) {
        this.project = var1;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public void setPackageId(String var1) {
        this.packageId = var1;
    }
}
