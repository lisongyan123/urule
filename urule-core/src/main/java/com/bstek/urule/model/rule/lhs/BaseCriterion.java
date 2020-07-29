//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import org.codehaus.jackson.annotate.JsonIgnore;

public abstract class BaseCriterion implements Criterion {
    @JsonIgnore
    private Junction parent;

    public BaseCriterion() {
    }

    public Junction getParent() {
        return this.parent;
    }

    public void setParent(Junction var1) {
        this.parent = var1;
    }
}
