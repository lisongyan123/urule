//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;

public class RuleNode extends BindingNode {
    private FlowNodeType type;
    private String file;
    private String version;

    public RuleNode() {
        this.type = FlowNodeType.Rule;
    }

    public RuleNode(String var1) {
        super(var1);
        this.type = FlowNodeType.Rule;
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

    public String getFile() {
        return this.file;
    }

    public void setFile(String var1) {
        this.file = var1;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String var1) {
        this.version = var1;
    }
}
