//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.model.rete.RuleData;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReteInstance {
    private List<RuleData> a;
    private String b = UUID.randomUUID().toString();
    private List<ObjectTypeActivity> c;
    private Map<String, List<ReteInstanceUnit>> d;
    private Map<String, List<ReteInstanceUnit>> e;

    public ReteInstance(List<ObjectTypeActivity> var1, Map<String, List<ReteInstanceUnit>> var2, Map<String, List<ReteInstanceUnit>> var3, List<RuleData> var4) {
        this.c = var1;
        this.d = var2;
        this.e = var3;
        this.a = var4;
    }

    public Collection<FactTracker> enter(EvaluationContext var1, Object var2) {
        Collection var3 = null;
        Iterator var4 = this.c.iterator();

        while(var4.hasNext()) {
            ObjectTypeActivity var5 = (ObjectTypeActivity)var4.next();
            if (var5.support(var2)) {
                var3 = var5.enter(var1, var2, new FactTracker());
                break;
            }
        }

        return var3;
    }

    public void reset() {
        Iterator var1 = this.c.iterator();

        while(var1.hasNext()) {
            ObjectTypeActivity var2 = (ObjectTypeActivity)var1.next();
            List var3 = var2.getPaths();
            this.a(var3);
        }

        if (this.d != null) {
            var1 = this.d.values().iterator();

            while(var1.hasNext()) {
                List var5 = (List)var1.next();
                Iterator var6 = var5.iterator();

                while(var6.hasNext()) {
                    ReteInstanceUnit var4 = (ReteInstanceUnit)var6.next();
                    if (var4.getReteInstance() != null) {
                        var4.getReteInstance().reset();
                    }
                }
            }
        }

    }

    private void a(List<Path> var1) {
        if (var1 != null) {
            Activity var4;
            for(Iterator var2 = var1.iterator(); var2.hasNext(); this.a(var4.getPaths())) {
                Path var3 = (Path)var2.next();
                var3.setPassed(false);
                var4 = var3.getTo();
                if (var4 instanceof AbstractActivity) {
                    AbstractActivity var5 = (AbstractActivity)var4;
                    var5.reset();
                }
            }

        }
    }

    public Map<String, List<ReteInstanceUnit>> getMutexGroupReteInstancesMap() {
        return this.d;
    }

    public Map<String, List<ReteInstanceUnit>> getPendedGroupReteInstancesMap() {
        return this.e;
    }

    public List<RuleData> getAllRuleData() {
        return this.a;
    }

    public String getId() {
        return this.b;
    }
}
