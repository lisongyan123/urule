//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.runtime.agenda.ActivationImpl;
import java.util.ArrayList;
import java.util.Collection;

public class TerminalActivity extends AbstractActivity {
    private Rule a;

    public TerminalActivity(Rule var1) {
        this.a = var1;
    }

    public Collection<FactTracker> enter(EvaluationContext var1, Object var2, FactTracker var3) {
        ArrayList var4 = new ArrayList();
        ActivationImpl var5 = new ActivationImpl(this.a);
        var3.setActivation(var5);
        var4.add(var3);
        if (this.a.getDebug() != null && this.a.getDebug() && !"__*__".equals(var2)) {
            var1.getLogger().logMatchRule(this.a, var3.getCriterias());
        }

        return var4;
    }

    public void reset() {
    }
}
