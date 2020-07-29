//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;

public class ActionNode extends FlowNode {
    private String actionBean;
    private FlowNodeType type;

    public ActionNode() {
        this.type = FlowNodeType.Action;
    }

    public ActionNode(String var1) {
        super(var1);
        this.type = FlowNodeType.Action;
    }

    public void enterNode(FlowContext var1, FlowInstance var2) {
        var2.setCurrentNode(this);
        this.executeNodeEvent(EventType.enter, var1, var2);
        FlowAction var3 = (FlowAction)var1.getApplicationContext().getBean(this.actionBean);
        var3.execute(this, var1, var2);
        this.executeNodeEvent(EventType.leave, var1, var2);
        this.leave((String)null, var1, var2);
    }

    public FlowNodeType getType() {
        return this.type;
    }

    public String getActionBean() {
        return this.actionBean;
    }

    public void setActionBean(String var1) {
        this.actionBean = var1;
    }
}
