//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OrActivity extends JoinActivity {
    public OrActivity() {
    }

    public List<FactTracker> enter(EvaluationContext var1, Object var2, FactTracker var3) {
        this.tokens.addAll(var3.getTokens());
        return this.visitPahs(var1, var2, var3);
    }

    public boolean orNodeTokensExist(Set<Integer> var1) {
        Iterator var2 = var1.iterator();

        Integer var3;
        do {
            if (!var2.hasNext()) {
                return true;
            }

            var3 = (Integer)var2.next();
        } while(this.tokens.contains(var3));

        return false;
    }
}
