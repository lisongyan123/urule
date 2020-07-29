//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.runtime.agenda.Activation;
import com.bstek.urule.runtime.agenda.ActivationImpl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FactTracker {
    private Path a;
    private Activation b;
    private Set<Integer> c = new HashSet();
    private Set<Criteria> d = new HashSet();
    private Map<String, Object> e = new HashMap();

    public FactTracker() {
    }

    public Activation getActivation() {
        return this.b;
    }

    public void setActivation(Activation var1) {
        ActivationImpl var2 = (ActivationImpl)var1;
        var2.setCriterias(this.d);
        var2.setFactMap(this.e);
        this.b = var1;
    }

    public void addFactMap(Map<String, Object> var1) {
        this.e.putAll(var1);
    }

    public Map<String, Object> getFactMap() {
        return this.e;
    }

    public void addCriteria(Criteria var1) {
        this.d.add(var1);
    }

    public void addCriterias(Set<Criteria> var1) {
        this.d.addAll(var1);
    }

    public Set<Criteria> getCriterias() {
        return this.d;
    }

    public Set<Integer> getTokens() {
        return this.c;
    }

    public void setToken(Integer var1) {
        this.c.clear();
        this.c.add(var1);
    }

    public void setTokens(Set<Integer> var1) {
        this.c.clear();
        this.c.addAll(var1);
    }

    public void setCurrentPath(Path var1) {
        this.a = var1;
    }

    public Path getCurrentPath() {
        return this.a;
    }

    public FactTracker newSubFactTracker() {
        FactTracker var1 = new FactTracker();
        var1.setTokens(this.c);
        var1.addCriterias(this.d);
        var1.addFactMap(this.e);
        return var1;
    }
}
