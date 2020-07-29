//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;

public class StartNode extends FlowNode {
    private FlowNodeType type;

    public StartNode() {
        this.type = FlowNodeType.Start;
    }

    public StartNode(String var1) {
        super(var1);
        this.type = FlowNodeType.Start;
    }

    public FlowNodeType getType() {
        return this.type;
    }

    public void enterNode(FlowContext var1, FlowInstance var2) {
        this.executeNodeEvent(EventType.enter, var1, var2);
        this.executeNodeEvent(EventType.leave, var1, var2);
        this.leave((String)null, var1, var2);
    }
}
