//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.ProcessInstance;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.response.ExecutionResponseImpl;
import com.bstek.urule.runtime.response.FlowExecutionResponse;
import com.bstek.urule.runtime.response.RuleExecutionResponse;
import java.util.Iterator;
import java.util.Map;

public abstract class BindingNode extends FlowNode {
    private KnowledgePackageWrapper knowledgePackageWrapper;

    public BindingNode() {
    }

    public BindingNode(String var1) {
        super(var1);
    }

    protected KnowledgeSession executeKnowledgePackage(FlowContext var1, ProcessInstance var2) {
        KnowledgeSession var3 = (KnowledgeSession)var1.getWorkingMemory();
        KnowledgePackage var4 = this.knowledgePackageWrapper.getKnowledgePackage();
        KnowledgeSession var5 = KnowledgeSessionFactory.newKnowledgeSession(this.knowledgePackageWrapper, var1, var3);
        if (var4.getFlowMap() != null && var4.getFlowMap().size() != 0) {
            String var14 = ((FlowDefinition)var4.getFlowMap().values().iterator().next()).getId();
            FlowExecutionResponse var7 = var5.startProcess(var14, var1.getVariables());
            ((ExecutionResponseImpl)var1.getResponse()).addFlowExecutionResponse(var7);
        } else {
            RuleExecutionResponse var6 = var5.fireRules(var1.getVariables());
            ((ExecutionResponseImpl)var1.getResponse()).addRuleExecutionResponse(var6);
        }

        var1.addRuleData(var5.getLogManager().getRuleData());
        synchronized(var1) {
            Map var15 = var5.getParameters();
            Map var8 = var1.getVariables();
            Iterator var9 = var15.keySet().iterator();

            while(var9.hasNext()) {
                String var10 = (String)var9.next();
                if (!var10.equals("return_to__")) {
                    Object var11 = var15.get(var10);
                    if (var11 != null) {
                        var8.put(var10, var11);
                    }
                }
            }

            return var5;
        }
    }

    public KnowledgePackageWrapper getKnowledgePackageWrapper() {
        return this.knowledgePackageWrapper;
    }

    public void setKnowledgePackageWrapper(KnowledgePackageWrapper var1) {
        this.knowledgePackageWrapper = var1;
    }
}
