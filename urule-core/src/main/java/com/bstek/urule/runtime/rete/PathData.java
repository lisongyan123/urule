//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.model.rule.lhs.Criteria;
import java.util.Map;
import java.util.Set;

public class PathData {
    private Set<Criteria> a;
    private Map<String, Object> b;

    public PathData(Set<Criteria> var1, Map<String, Object> var2) {
        this.a = var1;
        this.b = var2;
    }

    public Set<Criteria> getCriterias() {
        return this.a;
    }

    public void addCriterias(Set<Criteria> var1) {
        this.a.addAll(var1);
    }

    public void setCriterias(Set<Criteria> var1) {
        this.a = var1;
    }

    public Map<String, Object> getFactMap() {
        return this.b;
    }

    public void setFactMap(Map<String, Object> var1) {
        this.b = var1;
    }
}
