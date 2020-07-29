//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.execution;

import com.bstek.urule.action.WorkingMemoryHolderAdapter;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.flow.ins.FlowContextImpl;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.response.FlowExecutionResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FlowExecution extends AbstractExecution {
    private FlowContextImpl a;

    public FlowExecution(KnowledgeSession var1, Map<String, String> var2) {
        super(var1, var2);
        this.a = new FlowContextImpl(var1, var2);
    }

    public FlowExecutionResponse startProcess(String var1, Map<String, Object> var2) {
        FlowDefinition var3 = null;
        Iterator var4 = this.knowledgeSession.getKnowledgePackageList().iterator();

        while(var4.hasNext()) {
            KnowledgePackage var5 = (KnowledgePackage)var4.next();
            Map var6 = var5.getFlowMap();
            if (var6 != null && var6.containsKey(var1)) {
                var3 = (FlowDefinition)var6.get(var1);
                break;
            }
        }

        if (var3 == null) {
            throw new RuleException("Rule flow [" + var1 + "] not exist.");
        } else {
            this.knowledgeSession.getLogManager().clean();
            Map var12 = this.factManager.buildRuntimeParameters(var2);

            FlowExecutionResponse var16;
            try {
                ConcurrentHashMap var13 = new ConcurrentHashMap(var12.size());
                Iterator var14 = var12.keySet().iterator();

                while(var14.hasNext()) {
                    String var7 = (String)var14.next();
                    Object var8 = var12.get(var7);
                    if (var8 != null) {
                        var13.put(var7, var8);
                    }
                }

                WorkingMemoryHolderAdapter.set(this.knowledgeSession);
                this.monitorManager.doMonitorInputData(var13);
                this.a.setVariableMap(var13);
                var3.newInstance(this.a);
                FlowExecutionResponse var15 = this.a.getResponse();
                this.monitorManager.setTotalDuration(var15.getDuration());
                this.monitorManager.doMonitor(var13);
                var12.putAll(var13);
                this.reset();
                var16 = var15;
            } finally {
                WorkingMemoryHolderAdapter.clean();
            }

            return var16;
        }
    }
}
