//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.model.rule.lhs.Criteria;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AndActivity extends JoinActivity {
    private Map<Path, Set<Criteria>> a = new HashMap();
    private Map<Path, List<Map<String, Object>>> b = new HashMap();

    public AndActivity() {
    }

    public Collection<FactTracker> enter(EvaluationContext var1, Object var2, FactTracker var3) {
        this.tokens.addAll(var3.getTokens());
        this.a(var3);
        if (!this.passed && !this.allPassed()) {
            return null;
        } else {
            this.passed = true;
            Set var4 = this.c(var3);
            List var5 = this.b(var3);
            ArrayList var6 = new ArrayList();
            Iterator var7 = var5.iterator();

            while(var7.hasNext()) {
                Map var8 = (Map)var7.next();
                FactTracker var9 = new FactTracker();
                var9.setTokens(this.tokens);
                var9.addFactMap(var8);
                var9.addCriterias(var4);
                List var10 = this.visitPahs(var1, var2, var9);
                if (var10 != null) {
                    var6.addAll(var10);
                }
            }

            return var6;
        }
    }

    private void a(FactTracker var1) {
        Path var2 = var1.getCurrentPath();
        this.a.put(var2, var1.getCriterias());
        Map var3 = var1.getFactMap();
        if (var3.size() > 0) {
            Object var4 = null;
            if (this.b.containsKey(var2)) {
                var4 = (List)this.b.get(var2);
            } else {
                var4 = new ArrayList();
                this.b.put(var2, (List<Map<String, Object>>) var4);
            }

            ((List)var4).add(var3);
        }

    }

    private List<Map<String, Object>> b(FactTracker var1) {
        Path var2 = var1.getCurrentPath();
        Object var3 = new ArrayList();
        ((List)var3).add(var1.getFactMap());

        for(Iterator var4 = this.b.keySet().iterator(); var4.hasNext() && ((List)var3).size() != 0; var3 = this.a(var2, var4, (List)var3)) {
        }

        return (List)var3;
    }

    private List<Map<String, Object>> a(Path var1, Iterator<Path> var2, List<Map<String, Object>> var3) {
        Path var4 = (Path)var2.next();
        if (var4 == var1) {
            return var3;
        } else {
            ArrayList var5 = new ArrayList();
            List var6 = (List)this.b.get(var4);
            Iterator var7 = var3.iterator();

            while(var7.hasNext()) {
                Map var8 = (Map)var7.next();
                Iterator var9 = var6.iterator();

                while(var9.hasNext()) {
                    Map var10 = (Map)var9.next();
                    boolean var11 = this.a(var8, var10);
                    if (var11) {
                        HashMap var12 = new HashMap();
                        var12.putAll(var8);
                        var12.putAll(var10);
                        var5.add(var12);
                    }
                }
            }

            return var5;
        }
    }

    private boolean a(Map<String, Object> var1, Map<String, Object> var2) {
        boolean var3 = true;
        Iterator var4 = var2.keySet().iterator();

        while(var4.hasNext()) {
            String var5 = (String)var4.next();
            if (var1.containsKey(var5)) {
                Object var6 = var1.get(var5);
                Object var7 = var2.get(var5);
                if (var6 != var7) {
                    var3 = false;
                    break;
                }
            }
        }

        return var3;
    }

    private Set<Criteria> c(FactTracker var1) {
        HashSet var2 = new HashSet();
        var2.addAll(var1.getCriterias());
        Path var3 = var1.getCurrentPath();
        Iterator var4 = this.a.keySet().iterator();

        while(var4.hasNext()) {
            Path var5 = (Path)var4.next();
            if (var5 != var3) {
                var2.addAll((Collection)this.a.get(var5));
            }
        }

        return var2;
    }

    public void reset() {
        super.reset();
        this.passed = false;
        this.b.clear();
        this.a.clear();
    }
}
