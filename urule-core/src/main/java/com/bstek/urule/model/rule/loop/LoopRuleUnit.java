//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.loop;

import com.bstek.urule.model.rule.Other;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.lhs.Lhs;

public class LoopRuleUnit {
    private String name;
    private Lhs lhs;
    private Rhs rhs;
    private Other other;

    public LoopRuleUnit() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public Lhs getLhs() {
        return this.lhs;
    }

    public void setLhs(Lhs var1) {
        this.lhs = var1;
    }

    public Rhs getRhs() {
        return this.rhs;
    }

    public void setRhs(Rhs var1) {
        this.rhs = var1;
    }

    public Other getOther() {
        return this.other;
    }

    public void setOther(Other var1) {
        this.other = var1;
    }
}
