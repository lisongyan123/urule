//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow.ins;

import com.bstek.urule.model.flow.FlowNode;
import com.bstek.urule.model.flow.ProcessDefinition;
import java.util.List;

public interface ProcessInstance {
    ProcessDefinition getProcessDefinition();

    List<ProcessInstance> getChildren();

    int getParallelInstanceCount();

    String getId();

    FlowNode getCurrentNode();

    ProcessInstance getParent();
}
