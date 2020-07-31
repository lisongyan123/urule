//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.rete;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rete.BaseReteNode;
import com.bstek.urule.model.rete.OrNode;
import com.bstek.urule.model.rule.lhs.BaseCriterion;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Or;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrBuilder extends JunctionBuilder {
    public OrBuilder() {
    }

    public List<BaseReteNode> buildCriterion(BaseCriterion var1, BuildContext var2) {
        Or var3 = (Or)var1;
        List var4 = var3.getCriterions();
        if (var4 != null && var4.size() != 0) {
            ArrayList var5 = new ArrayList();
            Iterator var6 = var4.iterator();

            while(var6.hasNext()) {
                Criterion var7 = (Criterion)var6.next();
                List var8 = this.buildCriterion(var7, var2, (List)null);
                if (var8 != null) {
                    var5.addAll(var8);
                }
            }

            if (var5.size() == 0) {
                return null;
            } else if (var5.size() == 1) {
                return var5;
            } else {
                OrNode var9 = new OrNode(var2.nextId());
                Iterator var10 = var5.iterator();

                while(var10.hasNext()) {
                    BaseReteNode var12 = (BaseReteNode)var10.next();
                    var12.addLine(var9);
                }

                ArrayList var11 = new ArrayList();
                var11.add(var9);
                return var11;
            }
        } else {
            throw new RuleException("Condition join node[or] need one child at least.");
        }
    }

    public boolean support(Criterion var1) {
        return var1 instanceof Or;
    }
}
