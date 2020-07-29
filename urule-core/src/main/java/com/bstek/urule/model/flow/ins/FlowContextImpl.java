//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow.ins;

import com.bstek.urule.runtime.WorkingMemory;
import com.bstek.urule.runtime.response.ExecutionResponseImpl;
import com.bstek.urule.runtime.response.FlowExecutionResponse;
import com.bstek.urule.runtime.rete.ContextImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlowContextImpl extends ContextImpl implements FlowContext {
    private Map<String, Object> variableMap;
    private List<FlowInstance> flowInstances = new ArrayList();
    private ExecutionResponseImpl response = new ExecutionResponseImpl();

    public FlowContextImpl(WorkingMemory var1, Map<String, String> var2) {
        super(var1, var2);
    }

    public FlowExecutionResponse getResponse() {
        return this.response;
    }

    public Map<String, Object> getVariables() {
        return this.variableMap;
    }

    public Object getVariable(String var1) {
        return this.variableMap.get(var1);
    }

    public void removeVariable(String var1) {
        this.variableMap.remove(var1);
    }

    public void addVariable(String var1, Object var2) {
        this.variableMap.put(var1, var2);
    }

    public void setVariableMap(Map<String, Object> var1) {
        this.variableMap = var1;
    }

    public void addFlowInstance(FlowInstance var1) {
        this.flowInstances.add(var1);
    }

    public List<FlowInstance> getFlowInstances() {
        return this.flowInstances;
    }
}
