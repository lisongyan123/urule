//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.table;

import com.bstek.urule.model.rule.lhs.And;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Junction;
import com.bstek.urule.model.rule.lhs.Left;
import com.bstek.urule.model.rule.lhs.LeftType;
import com.bstek.urule.model.rule.lhs.Or;
import com.bstek.urule.model.rule.lhs.VariableLeftPart;
import com.bstek.urule.model.scorecard.ComplexColumn;
import com.bstek.urule.model.table.Cell;
import com.bstek.urule.model.table.Column;
import com.bstek.urule.model.table.Condition;
import com.bstek.urule.model.table.Joint;
import com.bstek.urule.model.table.JointType;
import java.util.Iterator;
import java.util.List;

public class CellContentBuilder {
    public CellContentBuilder() {
    }

    public Criterion buildCriterion(Cell var1, Column var2) {
        Joint var3 = var1.getJoint();
        if (var3 == null) {
            return null;
        } else {
            List var4 = var3.getConditions();
            List var5 = var3.getJoints();
            if ((var4 == null || var4.size() == 0) && (var5 == null || var5.size() == 0)) {
                return null;
            } else {
                Object var6 = null;
                if (var4.size() == 1) {
                    return this.a(var2, (Condition)var4.get(0));
                } else {
                    if (var3.getType().equals(JointType.and)) {
                        var6 = new And();
                    } else {
                        var6 = new Or();
                    }

                    this.a((List)var4, (Junction)var6, (Column)var2);
                    this.a((List)var5, (Column)var2, (Junction)var6);
                    return (Criterion)var6;
                }
            }
        }
    }

    public Criterion buildCriterion(Cell var1, ComplexColumn var2) {
        Joint var3 = var1.getJoint();
        if (var3 == null) {
            return null;
        } else {
            List var4 = var3.getConditions();
            List var5 = var3.getJoints();
            if ((var4 == null || var4.size() == 0) && (var5 == null || var5.size() == 0)) {
                return null;
            } else {
                Object var6 = null;
                if (var4.size() == 1) {
                    return this.a(var1, var2, (Condition)var4.get(0));
                } else {
                    if (var3.getType().equals(JointType.and)) {
                        var6 = new And();
                    } else {
                        var6 = new Or();
                    }

                    this.a(var1, var4, (Junction)var6, (ComplexColumn)var2);
                    this.a(var1, var5, (ComplexColumn)var2, (Junction)var6);
                    return (Criterion)var6;
                }
            }
        }
    }

    private void a(List<Joint> var1, Column var2, Junction var3) {
        if (var1 != null && var1.size() != 0) {
            Iterator var4 = var1.iterator();

            while(var4.hasNext()) {
                Joint var5 = (Joint)var4.next();
                Junction var6 = var5.getJunction();
                List var7 = var5.getConditions();
                this.a(var7, var6, var2);
                List var8 = var5.getJoints();
                this.a(var8, var2, var6);
                var3.addCriterion(var6);
            }

        }
    }

    private void a(Cell var1, List<Joint> var2, ComplexColumn var3, Junction var4) {
        if (var2 != null && var2.size() != 0) {
            Iterator var5 = var2.iterator();

            while(var5.hasNext()) {
                Joint var6 = (Joint)var5.next();
                Junction var7 = var6.getJunction();
                List var8 = var6.getConditions();
                this.a(var1, var8, var7, var3);
                List var9 = var6.getJoints();
                this.a(var1, var9, var3, var7);
                var4.addCriterion(var7);
            }

        }
    }

    private void a(List<Condition> var1, Junction var2, Column var3) {
        if (var1 != null && var1.size() != 0) {
            Iterator var4 = var1.iterator();

            while(var4.hasNext()) {
                Condition var5 = (Condition)var4.next();
                Criteria var6 = this.a(var3, var5);
                var2.addCriterion(var6);
            }

        }
    }

    private void a(Cell var1, List<Condition> var2, Junction var3, ComplexColumn var4) {
        if (var2 != null && var2.size() != 0) {
            Iterator var5 = var2.iterator();

            while(var5.hasNext()) {
                Condition var6 = (Condition)var5.next();
                Criteria var7 = this.a(var1, var4, var6);
                var3.addCriterion(var7);
            }

        }
    }

    private Criteria a(Column var1, Condition var2) {
        Criteria var3 = new Criteria();
        Left var4 = new Left();
        VariableLeftPart var5 = new VariableLeftPart();
        var5.setVariableCategory(var1.getVariableCategory());
        var5.setVariableName(var1.getVariableName());
        var5.setVariableLabel(var1.getVariableLabel());
        var5.setKeyLabel(var1.getKeyLabel());
        var5.setKeyName(var1.getKeyName());
        var5.setDatatype(var1.getDatatype());
        var4.setLeftPart(var5);
        var4.setType(LeftType.variable);
        var3.setLeft(var4);
        var3.setOp(var2.getOp());
        var3.setValue(var2.getValue());
        return var3;
    }

    private Criteria a(Cell var1, ComplexColumn var2, Condition var3) {
        Criteria var4 = new Criteria();
        Left var5 = new Left();
        VariableLeftPart var6 = new VariableLeftPart();
        var6.setVariableCategory(var2.getVariableCategory());
        var6.setVariableLabel(var1.getVariableLabel());
        var6.setVariableName(var1.getVariableName());
        var6.setDatatype(var1.getDatatype());
        var6.setKeyLabel(var1.getKeyLabel());
        var6.setKeyName(var1.getKeyName());
        var5.setLeftPart(var6);
        var5.setType(LeftType.variable);
        var4.setLeft(var5);
        var4.setOp(var3.getOp());
        var4.setValue(var3.getValue());
        return var4;
    }
}
