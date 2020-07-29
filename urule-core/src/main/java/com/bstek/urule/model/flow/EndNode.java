//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;

public class EndNode extends FlowNode {
    private FlowNodeType type;

    public EndNode() {
        this.type = FlowNodeType.End;
    }

    public FlowNodeType getType() {
        return this.type;
    }

    public EndNode(String var1) {
        super(var1);
        this.type = FlowNodeType.End;
    }

    public void enterNode(FlowContext var1, FlowInstance var2) {
        this.executeNodeEvent(EventType.enter, var1, var2);
        var2.setCurrentNode(this);
        this.executeNodeEvent(EventType.leave, var1, var2);
    }
}
