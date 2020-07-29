//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.runtime.execution.RuleExecution;
import com.bstek.urule.runtime.log.LogManager;
import com.bstek.urule.runtime.rete.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorkingMemory implements WorkingMemory {
    protected LogManager logManager;
    protected FactManager factManager;
    protected RuleExecution ruleExecution;
    protected KnowledgeSession parentSession;
    protected Map<String, Object> sessionValueMap = new HashMap();
    protected Map<String, KnowledgeSession> knowledgeSessionMap = new HashMap();

    public AbstractWorkingMemory() {
    }

    public Map<String, Object> getParameters() {
        return this.factManager.getParameters();
    }

    public boolean insert(Object var1) {
        return this.factManager.insert(var1);
    }

    public boolean update(Object var1) {
        this.ruleExecution.reevaluationRete(var1);
        return true;
    }

    public Object getParameter(String var1) {
        return this.factManager.getParameters().get(var1);
    }

    public Map<String, Object> getAllFactsMap() {
        return this.factManager.getFactMap();
    }

    public List<Object> getFactList() {
        return this.factManager.getFactList();
    }

    public KnowledgeSession getKnowledgeSession(String var1) {
        return (KnowledgeSession)this.knowledgeSessionMap.get(var1);
    }

    public void putKnowledgeSession(String var1, KnowledgeSession var2) {
        if (this.knowledgeSessionMap.containsKey(var1)) {
            this.knowledgeSessionMap.put(var1, var2);
        }

    }

    public Object getSessionValue(String var1) {
        return this.sessionValueMap.get(var1);
    }

    public void setSessionValue(String var1, Object var2) {
        this.sessionValueMap.put(var1, var2);
    }

    public Map<String, Object> getSessionValueMap() {
        return this.sessionValueMap;
    }

    public void activeRule(String var1, String var2) {
        this.ruleExecution.getAgenda().activeMutexGroupRule(var1, var2);
    }

    public void activePendedGroup(String var1) {
        this.ruleExecution.getAgenda().activePendedGroup(var1);
    }

    public void activePendedGroupAndExecute(String var1) {
        this.ruleExecution.getAgenda().activePendedGroupAndExecute(var1);
    }

    public Context getContext() {
        return this.ruleExecution.getAgenda().getContext();
    }

    public FactManager getFactManager() {
        return this.factManager;
    }

    public LogManager getLogManager() {
        return this.logManager;
    }
}
