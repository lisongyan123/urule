//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.percent;

import com.bstek.urule.model.flow.Branch;
import com.bstek.urule.model.flow.DecisionItem;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PercentUnit {
    private long a;
    private String b;
    private String c;
    private Map<String, Branch> d = new ConcurrentHashMap();

    public PercentUnit() {
    }

    public Branch getBranch(DecisionItem var1) {
        String var2 = this.b + "." + this.c + "." + var1.getTo();
        if (this.d.containsKey(var2)) {
            return (Branch)this.d.get(var2);
        } else {
            Branch var3 = new Branch();
            var3.setName(var1.getTo());
            var3.setPercent(var1.getPercent());
            var3.setTotal(0L);
            this.d.put(var2, var3);
            return var3;
        }
    }

    public long getTotal() {
        return this.a;
    }

    public void setTotal(long var1) {
        this.a = var1;
    }

    public String getFlowId() {
        return this.b;
    }

    public void setFlowId(String var1) {
        this.b = var1;
    }

    public String getDecisionNodeName() {
        return this.c;
    }

    public void setDecisionNodeName(String var1) {
        this.c = var1;
    }

    public Collection<Branch> getBranchs() {
        return this.d.values();
    }
}
