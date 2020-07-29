//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Junction extends BaseCriterion {
    private List<Criterion> criterions;

    public Junction() {
    }

    public List<Criterion> getCriterions() {
        return this.criterions;
    }

    public void addCriterion(Criterion var1) {
        if (this.criterions == null) {
            this.criterions = new ArrayList();
        }

        var1.setParent(this);
        this.criterions.add(var1);
    }

    public void setCriterions(List<Criterion> var1) {
        Iterator var2 = var1.iterator();

        while(var2.hasNext()) {
            Criterion var3 = (Criterion)var2.next();
            var3.setParent(this);
        }

        this.criterions = var1;
    }

    public abstract String getJunctionType();
}
