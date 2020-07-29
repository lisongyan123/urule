//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.percent;

import com.bstek.urule.model.flow.ProcessDefinition;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryPercentDataStore implements PercentDataStore {
    private Map<String, PercentUnit> a = new ConcurrentHashMap();

    public MemoryPercentDataStore() {
    }

    public PercentUnit getDecisionNodePercent(ProcessDefinition var1, String var2) {
        String var3 = var1.getId() + "." + var2;
        if (this.a.containsKey(var3)) {
            return (PercentUnit)this.a.get(var3);
        } else {
            PercentUnit var4 = new PercentUnit();
            var4.setDecisionNodeName(var2);
            var4.setFlowId(var1.getId());
            var4.setTotal(0L);
            this.a.put(var3, var4);
            return var4;
        }
    }
}
