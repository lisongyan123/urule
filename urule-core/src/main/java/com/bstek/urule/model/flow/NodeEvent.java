//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.ProcessInstance;

public interface NodeEvent {
    void enter(FlowNode var1, ProcessInstance var2, FlowContext var3);

    void leave(FlowNode var1, ProcessInstance var2, FlowContext var3);
}
