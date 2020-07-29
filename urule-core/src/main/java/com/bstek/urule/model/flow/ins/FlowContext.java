//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow.ins;

import com.bstek.urule.runtime.response.FlowExecutionResponse;
import com.bstek.urule.runtime.rete.Context;
import java.util.List;
import java.util.Map;

public interface FlowContext extends Context {
    Object getVariable(String var1);

    Map<String, Object> getVariables();

    void addVariable(String var1, Object var2);

    void removeVariable(String var1);

    List<FlowInstance> getFlowInstances();

    void addFlowInstance(FlowInstance var1);

    FlowExecutionResponse getResponse();
}
