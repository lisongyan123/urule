//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.runtime.agenda.AgendaFilter;
import com.bstek.urule.runtime.execution.RuleExecution;
import com.bstek.urule.runtime.log.LogManager;
import com.bstek.urule.runtime.response.FlowExecutionResponse;
import com.bstek.urule.runtime.response.RuleExecutionResponse;
import com.bstek.urule.runtime.rete.ReteInstance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnowledgeSessionImpl extends AbstractWorkingMemory implements KnowledgeSession {
    private List<ReteInstance> a;
    private List<KnowledgePackage> b;

    public KnowledgeSessionImpl(KnowledgePackage var1) {
        this((KnowledgePackage[])(new KnowledgePackage[]{var1}), (KnowledgeSession)null);
    }

    public KnowledgeSessionImpl(KnowledgePackage var1, KnowledgeSession var2) {
        this(new KnowledgePackage[]{var1}, var2);
    }

    public KnowledgeSessionImpl(KnowledgePackage[] var1, KnowledgeSession var2) {
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.logManager = new LogManager(var2);
        this.factManager = new FactManager(var2);
        HashMap var3 = new HashMap();
        KnowledgePackage[] var4 = var1;
        int var5 = var1.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            KnowledgePackage var7 = var4[var6];
            this.a(var7);
            var3.putAll(var7.getVariableCateogoryMap());
        }

        this.initFromParentSession(var2);
        this.ruleExecution = new RuleExecution(this, var3);
    }

    private void a(KnowledgePackage var1) {
        this.b.add(var1);
        this.a.addAll(var1.newAloneReteInstances());
        this.a.add(var1.newReteInstance());
        this.factManager.initKnowledgePackageParameters(var1);
    }

    public void initFromParentSession(KnowledgeSession var1) {
        if (var1 != null) {
            this.parentSession = var1;
            this.knowledgeSessionMap = var1.getKnowledgeSessionMap();
            this.sessionValueMap.putAll(var1.getSessionValueMap());
        }
    }

    public RuleExecutionResponse fireRules() {
        return this.a((AgendaFilter)null, (Map)null, 2147483647);
    }

    public RuleExecutionResponse fireRules(int var1) {
        return this.a((AgendaFilter)null, (Map)null, var1);
    }

    public RuleExecutionResponse fireRules(AgendaFilter var1) {
        return this.a(var1, (Map)null, 2147483647);
    }

    public RuleExecutionResponse fireRules(AgendaFilter var1, int var2) {
        return this.a(var1, (Map)null, var2);
    }

    public RuleExecutionResponse fireRules(Map<String, Object> var1) {
        return this.a((AgendaFilter)null, var1, 2147483647);
    }

    public RuleExecutionResponse fireRules(Map<String, Object> var1, AgendaFilter var2) {
        return this.a(var2, var1, 2147483647);
    }

    public RuleExecutionResponse fireRules(Map<String, Object> var1, AgendaFilter var2, int var3) {
        return this.a(var2, var1, var3);
    }

    public RuleExecutionResponse fireRules(Map<String, Object> var1, int var2) {
        return this.a((AgendaFilter)null, var1, var2);
    }

    public FlowExecutionResponse startProcess(String var1) {
        return this.startProcess(var1, (Map)null);
    }

    public FlowExecutionResponse startProcess(String var1, Map<String, Object> var2) {
        return this.ruleExecution.startProcess(var1, var2);
    }

    private RuleExecutionResponse a(AgendaFilter var1, Map<String, Object> var2, int var3) {
        return this.ruleExecution.fireRules(var1, var2, var3);
    }

    public List<KnowledgePackage> getKnowledgePackageList() {
        return this.b;
    }

    public void writeLogFile() throws IOException {
        this.logManager.writeLog();
    }

    public Map<String, KnowledgeSession> getKnowledgeSessionMap() {
        return this.knowledgeSessionMap;
    }

    public KnowledgeSession getParentSession() {
        return this.parentSession;
    }

    public List<ReteInstance> getReteInstanceList() {
        return this.a;
    }
}
