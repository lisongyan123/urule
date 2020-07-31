//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.rete;

import com.bstek.urule.model.rete.BaseReteNode;
import com.bstek.urule.model.rule.lhs.BaseCriterion;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.Criterion;
import java.util.List;

public class CriteriaBuilder extends CriterionBuilder {
    public CriteriaBuilder() {
    }

    public List<BaseReteNode> buildCriterion(BaseCriterion var1, BuildContext var2) {
        Criteria var3 = (Criteria)var1;
        return this.buildCriteria(var3, (List)null, var2);
    }

    public boolean support(Criterion var1) {
        return var1 instanceof Criteria;
    }
}
