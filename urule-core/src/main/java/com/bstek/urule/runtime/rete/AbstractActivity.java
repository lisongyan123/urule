//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class AbstractActivity implements Activity {
    private List<Path> a;
    protected Set<Integer> tokens = new HashSet();

    public AbstractActivity() {
    }

    public List<Path> getPaths() {
        return this.a;
    }

    public void addPath(Path var1) {
        if (this.a == null) {
            this.a = new ArrayList();
        }

        this.a.add(var1);
    }

    protected List<FactTracker> visitPahs(EvaluationContext var1, Object var2, FactTracker var3) {
        ArrayList var4 = new ArrayList();
        if (this.a != null && this.a.size() != 0) {
            int var5 = this.a.size();
            Iterator var6 = this.a.iterator();

            while(var6.hasNext()) {
                Path var7 = (Path)var6.next();
                Collection var8 = null;
                AbstractActivity var9 = (AbstractActivity)var7.getTo();
                var7.setPassed(true);
                boolean var10 = var9.orNodeTokensExist(var3.getTokens());
                if (!var10) {
                    if (var5 > 1) {
                        FactTracker var11 = var3.newSubFactTracker();
                        var11.setCurrentPath(var7);
                        var8 = var9.enter(var1, var2, var11);
                    } else {
                        var3.setCurrentPath(var7);
                        var8 = var9.enter(var1, var2, var3);
                    }

                    if (var8 != null) {
                        var4.addAll(var8);
                    }
                }
            }

            return var4;
        } else {
            return var4;
        }
    }

    public boolean orNodeTokensExist(Set<Integer> var1) {
        List var2 = this.getPaths();
        if (var2 != null && var2.size() == 1) {
            Path var3 = (Path)var2.get(0);
            AbstractActivity var4 = (AbstractActivity)var3.getTo();
            return var4.orNodeTokensExist(var1);
        } else {
            return false;
        }
    }

    public void reset() {
        this.tokens.clear();
    }
}
