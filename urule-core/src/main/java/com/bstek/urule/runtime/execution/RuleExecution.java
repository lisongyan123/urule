//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.execution;

import com.bstek.urule.action.WorkingMemoryHolderAdapter;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.agenda.AgendaFilter;
import com.bstek.urule.runtime.response.ExecutionResponseImpl;
import com.bstek.urule.runtime.response.RuleExecutionResponse;
import com.bstek.urule.runtime.rete.ReteInstance;
import com.bstek.urule.runtime.rete.ReteInstanceUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RuleExecution extends FlowExecution {
    private List<String> a = new ArrayList();

    public RuleExecution(KnowledgeSession var1, Map<String, String> var2) {
        super(var1, var2);
    }

    public RuleExecutionResponse fireRules(AgendaFilter var1, Map<String, Object> var2, int var3) {
        this.agenda.getEvaluationContext().reset();
        this.knowledgeSession.getLogManager().clean();
        Map var4 = this.factManager.buildRuntimeParameters(var2);
        this.monitorManager.doMonitorInputData(var4);

        RuleExecutionResponse var6;
        try {
            WorkingMemoryHolderAdapter.set(this.knowledgeSession);
            RuleExecutionResponse var5 = this.a(var1, var3);
            this.monitorManager.setTotalDuration(var5.getDuration());
            this.monitorManager.doMonitor(var4);
            this.reset();
            var6 = var5;
        } finally {
            WorkingMemoryHolderAdapter.clean();
        }

        return var6;
    }

    public RuleExecutionResponse reevaluationRete(Object var1) {
        this.factManager.addToFactsMap(var1);
        ArrayList var2 = new ArrayList();
        var2.add(var1);
        return this.a(var2);
    }

    private RuleExecutionResponse a(AgendaFilter var1, int var2) {
        long var3 = System.currentTimeMillis();
        ExecutionResponseImpl var5 = new ExecutionResponseImpl();
        Iterator var6 = this.reteInstanceList.iterator();

        while(var6.hasNext()) {
            ReteInstance var7 = (ReteInstance)var6.next();
            List var8 = this.factManager.getFactList();
            Iterator var9 = var8.iterator();

            while(var9.hasNext()) {
                Object var10 = var9.next();
                this.agenda.doRete(var7, var10, true);
            }

            this.agenda.doRete(var7, "__*__", true);
            this.a(var8, var7);
            this.agenda.execute(var7, var1, var2);
        }

        var5.setDuration(System.currentTimeMillis() - var3);
        return var5;
    }

    private RuleExecutionResponse a(List<Object> var1) {
        long var2 = System.currentTimeMillis();
        ExecutionResponseImpl var4 = new ExecutionResponseImpl();
        Iterator var5 = this.reteInstanceList.iterator();

        while(var5.hasNext()) {
            ReteInstance var6 = (ReteInstance)var5.next();
            Iterator var7 = var1.iterator();

            while(var7.hasNext()) {
                Object var8 = var7.next();
                this.agenda.doRete(var6, var8, false);
            }

            this.agenda.doRete(var6, "__*__", false);
            this.a(var1, var6);
            this.agenda.reEvaluationExecute(var6);
        }

        var4.setDuration(System.currentTimeMillis() - var2);
        return var4;
    }

    private void a(Collection<Object> var1, ReteInstance var2) {
        Map var3 = var2.getMutexGroupReteInstancesMap();
        if (var3 != null) {
            Collection var4 = null;
            Iterator var5 = var3.keySet().iterator();

            while(true) {
                label68:
                while(true) {
                    String var6;
                    String var7;
                    do {
                        if (!var5.hasNext()) {
                            return;
                        }

                        var6 = (String)var5.next();
                        var7 = var2.getId() + var6;
                    } while(this.a.contains(var7));

                    this.agenda.getContext().cleanTipMsg();
                    this.agenda.getContext().addTipMsg("执行互斥组:" + var6 + "");
                    List var8 = (List)var3.get(var6);
                    Date var9 = new Date();
                    Iterator var10 = var8.iterator();

                    do {
                        ReteInstanceUnit var11;
                        Date var13;
                        do {
                            Date var12;
                            do {
                                if (!var10.hasNext()) {
                                    continue label68;
                                }

                                var11 = (ReteInstanceUnit)var10.next();
                                var12 = var11.getEffectiveDate();
                            } while(var12 != null && var12.compareTo(var9) > 0);

                            var13 = var11.getExpiresDate();
                        } while(var13 != null && var13.compareTo(var9) < 0);

                        ReteInstance var14 = var11.getReteInstance();
                        Iterator var15 = var1.iterator();

                        while(var15.hasNext()) {
                            Object var16 = var15.next();
                            var4 = this.agenda.doRete(var14, var16, true);
                            if (var4 != null && var4.size() != 0) {
                                this.a.add(var7);
                                break;
                            }
                        }

                        if (var4 != null && var4.size() != 0) {
                            continue label68;
                        }

                        var4 = this.agenda.doRete(var14, "__*__", true);
                    } while(var4 == null);

                    this.a.add(var7);
                }
            }
        }
    }

    protected void reset() {
        super.reset();
        this.a.clear();
    }
}
