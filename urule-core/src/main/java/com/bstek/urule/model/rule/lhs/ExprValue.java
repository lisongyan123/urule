//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import java.util.List;

public class ExprValue {
    private int total = 0;
    private int match = 0;
    private int notMatch = 0;
    private List<Object> facts;

    public ExprValue() {
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int var1) {
        this.total = var1;
    }

    public int getMatch() {
        return this.match;
    }

    public void setMatch(int var1) {
        this.match = var1;
    }

    public int getNotMatch() {
        return this.notMatch;
    }

    public void setNotMatch(int var1) {
        this.notMatch = var1;
    }

    public List<Object> getFacts() {
        return this.facts;
    }

    public void setFacts(List<Object> var1) {
        this.facts = var1;
    }
}
