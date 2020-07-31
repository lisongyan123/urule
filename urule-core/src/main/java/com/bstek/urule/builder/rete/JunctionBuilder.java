//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.rete;

import com.bstek.urule.model.rete.BaseReteNode;
import com.bstek.urule.model.rete.ConditionNode;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Junction;
import java.util.List;

public abstract class JunctionBuilder extends CriterionBuilder {
    public JunctionBuilder() {
    }

    protected List<BaseReteNode> buildCriterion(Criterion var1, BuildContext var2, List<ConditionNode> var3) {
        if (var1 instanceof Junction) {
            Junction var5 = (Junction)var1;
            return ReteBuilder.buildCriterion(var2, var5);
        } else if (var1 instanceof Criteria) {
            Criteria var4 = (Criteria)var1;
            return this.buildCriteria(var4, var3, var2);
        } else {
            return null;
        }
    }
}
