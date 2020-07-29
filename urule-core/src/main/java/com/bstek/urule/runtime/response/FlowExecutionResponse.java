//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.response;

import java.util.List;

public interface FlowExecutionResponse extends ExecutionResponse {
    String getFlowId();

    List<RuleExecutionResponse> getRuleExecutionResponses();

    List<FlowExecutionResponse> getFlowExecutionResponses();
}
