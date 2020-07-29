//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.rete;

import com.bstek.urule.Utils;
import com.bstek.urule.model.Node;
import com.bstek.urule.model.rete.BaseReteNode;
import com.bstek.urule.model.rete.ConditionNode;
import com.bstek.urule.model.rete.CriteriaNode;
import com.bstek.urule.model.rete.ObjectTypeNode;
import com.bstek.urule.model.rete.ReteNode;
import com.bstek.urule.model.rule.lhs.BaseCriteria;
import com.bstek.urule.model.rule.lhs.BaseCriterion;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.Criterion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class CriterionBuilder {
    public CriterionBuilder() {
    }

    public abstract List<BaseReteNode> buildCriterion(BaseCriterion var1, BuildContext var2);

    public abstract boolean support(Criterion var1);

    protected List<BaseReteNode> buildCriteria(Criteria var1, List<ConditionNode> var2, BuildContext var3) {
        ArrayList var4 = new ArrayList();
        if (Utils.isDebug() && var3.currentRule() != null) {
            var1.setFile(var3.currentRule().getFile());
        }

        List var5 = var3.getObjectType(var1);
        if (var2 != null && var2.size() > 0) {
            Iterator var12 = var2.iterator();

            while(var12.hasNext()) {
                ConditionNode var7 = (ConditionNode)var12.next();
                boolean var8 = true;
                List var9 = var3.getObjectType(var7.getCriteria());
                Iterator var10;
                if (var9.size() == var5.size()) {
                    var10 = var9.iterator();

                    while(var10.hasNext()) {
                        String var11 = (String)var10.next();
                        if (!var5.contains(var11)) {
                            var8 = false;
                            break;
                        }
                    }
                } else {
                    var8 = false;
                }

                var10 = null;
                CriteriaNode var13;
                if (var8) {
                    List var14 = var7.getChildrenNodes();
                    var13 = this.a(var1, var14);
                    if (var13 == null) {
                        var13 = new CriteriaNode(var1, var3.nextId(), var3.currentRuleIsDebug());
                        var7.addLine(var13);
                    }

                    var4.add(var13);
                } else {
                    var13 = this.a(var1, var3, var5);
                    var4.add(var13);
                }
            }
        } else {
            CriteriaNode var6 = this.a(var1, var3, var5);
            var4.add(var6);
        }

        return var4;
    }

    private CriteriaNode a(BaseCriteria var1, BuildContext var2, List<String> var3) {
        CriteriaNode var4 = null;
        ObjectTypeNode var5 = null;
        Iterator var6 = var3.iterator();

        String var7;
        while(var6.hasNext()) {
            var7 = (String)var6.next();
            if (var7.equals("*")) {
                var7 = HashMap.class.getName();
            }

            var5 = var2.buildObjectTypeNode(var7);
            if (var4 == null) {
                List var8 = var5.getChildrenNodes();
                var4 = this.a(var1, var8);
            } else {
                var5.addLine(var4);
            }
        }

        if (var4 == null) {
            var6 = var3.iterator();

            while(var6.hasNext()) {
                var7 = (String)var6.next();
                if (var7.equals("*")) {
                    var7 = HashMap.class.getName();
                }

                var5 = var2.buildObjectTypeNode(var7);
                if (var4 == null) {
                    var4 = new CriteriaNode((Criteria)var1, var2.nextId(), var2.currentRuleIsDebug());
                    var5.addLine(var4);
                } else {
                    var5.addLine(var4);
                }
            }
        }

        return var4;
    }

    private CriteriaNode a(BaseCriteria var1, List<ReteNode> var2) {
        String var3 = var1.getId();
        CriteriaNode var4 = null;
        Iterator var5 = var2.iterator();

        while(var5.hasNext()) {
            Node var6 = (Node)var5.next();
            if (var6 instanceof ConditionNode && (!(var1 instanceof Criteria) || var6 instanceof CriteriaNode)) {
                ConditionNode var7 = (ConditionNode)var6;
                String var8 = var7.getCriteriaInfo();
                if (var8.equals(var3)) {
                    var4 = (CriteriaNode)var7;
                    break;
                }
            }
        }

        return var4;
    }
}
