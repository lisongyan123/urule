//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.resource;

import com.bstek.urule.runtime.log.LogManager;
import com.bstek.urule.runtime.rete.Context;

import java.util.List;
import java.util.Map;

public interface WorkingMemory {
    boolean insert(Object var1);

    boolean update(Object var1);

    Object getParameter(String var1);

    Map<String, Object> getParameters();

    Map<String, Object> getAllFactsMap();

    List<Object> getFactList();

    KnowledgeSession getKnowledgeSession(String var1);

    void putKnowledgeSession(String var1, KnowledgeSession var2);

    void setSessionValue(String var1, Object var2);

    Object getSessionValue(String var1);

    Map<String, Object> getSessionValueMap();

    void activeRule(String var1, String var2);

    void activePendedGroup(String var1);

    void activePendedGroupAndExecute(String var1);

    Context getContext();

    LogManager getLogManager();

    FactManager getFactManager();
}
