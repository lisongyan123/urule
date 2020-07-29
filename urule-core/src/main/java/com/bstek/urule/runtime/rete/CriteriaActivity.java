//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.Utils;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.EvaluateResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CriteriaActivity extends AbstractActivity {
    private boolean a;
    private boolean b;
    private Criteria c;
    private Set<String> d = new HashSet();
    private List<Map<String, Object>> e = new ArrayList();

    public CriteriaActivity(Criteria var1, boolean var2) {
        this.c = var1;
        this.a = var2;
    }

    public List<FactTracker> enter(EvaluationContext var1, Object var2, FactTracker var3) {
        this.tokens.addAll(var3.getTokens());
        this.a(var2, var3);
        if (!this.b && !this.c.necessaryClassEval(this.d)) {
            return null;
        } else {
            this.b = true;
            var3.setTokens(this.tokens);
            ArrayList var4 = new ArrayList();
            Iterator var5 = this.e.iterator();

            while(var5.hasNext()) {
                Map var6 = (Map)var5.next();
                EvaluateResponse var7 = this.c.evaluate(var1, var6);
                if (this.a) {
                    var1.getLogger().logCriteria(this.c, var7);
                }

                if (var7.getResult()) {
                    FactTracker var8 = var3.newSubFactTracker();
                    var8.addCriteria(this.c);
                    var8.addFactMap(var6);
                    List var9 = this.visitPahs(var1, var2, var8);
                    if (var9 != null) {
                        var4.addAll(var9);
                    }
                }
            }

            return var4;
        }
    }

    private void a(Object var1, FactTracker var2) {
        Map var3 = var2.getFactMap();
        if (var3.size() == 0) {
            String var4 = Utils.getClassName(var1);
            HashMap var5;
            if (!this.b && this.d.contains(var4)) {
                var5 = new HashMap();
                var5.putAll((Map)this.e.get(0));
                var5.put(var4, var1);
                this.e.add(var5);
            } else {
                this.d.add(var4);
                if (this.e.size() == 0) {
                    var5 = new HashMap();
                    var5.put(var4, var1);
                    this.e.add(var5);
                } else {
                    Iterator var10 = this.e.iterator();

                    while(var10.hasNext()) {
                        Map var6 = (Map)var10.next();
                        var6.put(var4, var1);
                    }
                }
            }
        } else if (this.d.size() == 0) {
            this.d.addAll(var3.keySet());
            HashMap var8 = new HashMap();
            var8.putAll(var3);
            this.e.add(var8);
        } else {
            Iterator var9 = var3.keySet().iterator();

            while(var9.hasNext()) {
                String var11 = (String)var9.next();
                Iterator var12 = this.e.iterator();

                while(var12.hasNext()) {
                    Map var7 = (Map)var12.next();
                    var7.put(var11, var3.get(var11));
                }
            }
        }

    }

    public void reset() {
        super.reset();
        this.b = false;
        this.d.clear();
        this.e.clear();
    }
}
