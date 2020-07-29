//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.runtime.rete.Activity;
import com.bstek.urule.runtime.rete.CriteriaActivity;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonIgnore;

public class CriteriaNode extends BaseReteNode implements ConditionNode {
    @JsonIgnore
    private String criteriaInfo;
    private boolean debug;
    private Criteria criteria;
    private NodeType nodeType;

    public CriteriaNode() {
        super(0);
        this.nodeType = NodeType.criteria;
    }

    public CriteriaNode(Criteria var1, int var2, boolean var3) {
        super(var2);
        this.nodeType = NodeType.criteria;
        this.criteria = var1;
        this.setCriteriaInfo(var1.getId());
        this.debug = var3;
    }

    public NodeType getNodeType() {
        return this.nodeType;
    }

    public Criteria getCriteria() {
        return this.criteria;
    }

    public void setCriteria(Criteria var1) {
        this.criteria = var1;
    }

    public String getCriteriaInfo() {
        return this.criteriaInfo;
    }

    public void setCriteriaInfo(String var1) {
        this.criteriaInfo = var1;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean var1) {
        this.debug = var1;
    }

    public Activity newActivity(Map<Object, Object> var1) {
        if (var1.containsKey(this)) {
            return (CriteriaActivity)var1.get(this);
        } else {
            CriteriaActivity var2 = new CriteriaActivity(this.criteria, this.debug);
            Iterator var3 = this.lines.iterator();

            while(var3.hasNext()) {
                Line var4 = (Line)var3.next();
                var2.addPath(var4.newPath(var1));
            }

            var1.put(this, var2);
            return var2;
        }
    }
}
