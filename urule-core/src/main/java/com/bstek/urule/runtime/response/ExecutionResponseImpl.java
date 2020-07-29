//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.response;

import java.util.ArrayList;
import java.util.List;

public class ExecutionResponseImpl implements FlowExecutionResponse, RuleExecutionResponse {
    private long a;
    private String b;
    private List<RuleExecutionResponse> c = new ArrayList();
    private List<FlowExecutionResponse> d = new ArrayList();

    public ExecutionResponseImpl() {
    }

    public String getFlowId() {
        return this.b;
    }

    public void setFlowId(String var1) {
        this.b = var1;
    }

    public void addFlowExecutionResponse(FlowExecutionResponse var1) {
        this.d.add(var1);
    }

    public List<FlowExecutionResponse> getFlowExecutionResponses() {
        return this.d;
    }

    public List<RuleExecutionResponse> getRuleExecutionResponses() {
        return this.c;
    }

    public void addRuleExecutionResponse(RuleExecutionResponse var1) {
        this.c.add(var1);
    }

    public long getDuration() {
        return this.a;
    }

    public void setDuration(long var1) {
        this.a = var1;
    }
}
