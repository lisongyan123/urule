//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.execution;

import com.bstek.urule.runtime.FactManager;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.agenda.Agenda;
import com.bstek.urule.runtime.log.LogManager;
import com.bstek.urule.runtime.monitor.MonitorManager;
import com.bstek.urule.runtime.rete.ReteInstance;
import com.bstek.urule.runtime.rete.ReteInstanceUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractExecution {
    protected Agenda agenda;
    protected FactManager factManager;
    protected MonitorManager monitorManager;
    protected KnowledgeSession knowledgeSession;
    protected List<ReteInstance> reteInstanceList;
    private Map<String, List<ReteInstanceUnit>> a = new HashMap();
    private Map<String, List<ReteInstanceUnit>> b = new HashMap();

    public AbstractExecution(KnowledgeSession var1, Map<String, String> var2) {
        this.knowledgeSession = var1;
        this.factManager = var1.getFactManager();
        this.agenda = new Agenda(var1, var2, this.b, this.a);
        this.monitorManager = new MonitorManager(var1);
        this.a(var1);
    }

    private void a(KnowledgeSession var1) {
        KnowledgeSession var2 = var1.getParentSession();
        if (var2 != null) {
            this.b(var2);
        }

        this.reteInstanceList = var1.getReteInstanceList();
        this.a(this.reteInstanceList);
    }

    private void b(KnowledgeSession var1) {
        KnowledgeSession var2 = var1.getParentSession();
        if (var2 != null) {
            this.b(var2);
        }

        LogManager var3 = this.knowledgeSession.getLogManager();
        List var4 = var1.getReteInstanceList();
        Iterator var5 = var4.iterator();

        while(var5.hasNext()) {
            ReteInstance var6 = (ReteInstance)var5.next();
            var3.addRuleData(var6.getAllRuleData());
            Map var7 = var6.getPendedGroupReteInstancesMap();
            if (var7 != null) {
                this.b.putAll(var7);
            }
        }

    }

    private void a(List<ReteInstance> var1) {
        LogManager var2 = this.knowledgeSession.getLogManager();
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            ReteInstance var4 = (ReteInstance)var3.next();
            var2.addRuleData(var4.getAllRuleData());
            Map var5 = var4.getPendedGroupReteInstancesMap();
            if (var5 != null) {
                this.b.putAll(var5);
            }

            Map var6 = var4.getMutexGroupReteInstancesMap();
            if (var6 != null) {
                this.a.putAll(var6);
            }
        }

    }

    public Agenda getAgenda() {
        return this.agenda;
    }

    protected void reset() {
        this.agenda.clean();
        this.factManager.clean();
    }
}
