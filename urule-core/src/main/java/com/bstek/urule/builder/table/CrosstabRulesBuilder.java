//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.table;

import com.bstek.urule.action.VariableAssignAction;
import com.bstek.urule.model.crosstab.ConditionCrossCell;
import com.bstek.urule.model.crosstab.CrossCell;
import com.bstek.urule.model.crosstab.CrossColumn;
import com.bstek.urule.model.crosstab.CrossRow;
import com.bstek.urule.model.crosstab.CrosstabDefinition;
import com.bstek.urule.model.crosstab.LeftColumn;
import com.bstek.urule.model.crosstab.TopRow;
import com.bstek.urule.model.crosstab.ValueCrossCell;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.lhs.And;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Junction;
import com.bstek.urule.model.rule.lhs.Left;
import com.bstek.urule.model.rule.lhs.LeftType;
import com.bstek.urule.model.rule.lhs.Lhs;
import com.bstek.urule.model.rule.lhs.Or;
import com.bstek.urule.model.rule.lhs.VariableLeftPart;
import com.bstek.urule.model.table.Condition;
import com.bstek.urule.model.table.Joint;
import com.bstek.urule.model.table.JointType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CrosstabRulesBuilder {
    public CrosstabRulesBuilder() {
    }

    public List<Rule> buildRules(CrosstabDefinition var1, String var2) {
        ArrayList var3 = new ArrayList();
        ArrayList var4 = new ArrayList();
        ArrayList var5 = new ArrayList();
        Map var6 = this.a(var1.getCells());
        List var7 = var1.getRows();
        int var8 = var7.size();
        List var9 = var1.getColumns();
        ArrayList var10 = new ArrayList();
        int var11 = 0;

        for(int var12 = 0; var12 < var8; ++var12) {
            CrossRow var13 = (CrossRow)var7.get(var12);
            if (var13 instanceof TopRow) {
                ++var11;
            }

            for(int var14 = 0; var14 < var9.size(); ++var14) {
                CellRange var15 = null;
                CrossCell var16 = (CrossCell)var6.get(var12 + 1 + "," + (var14 + 1));
                if (var16 != null) {
                    if (var13 instanceof TopRow) {
                        TopRow var17 = (TopRow)var13;
                        this.a((CrossCell)var16, (List)var4, (TopRow)var17);
                    } else {
                        CrossColumn var28 = (CrossColumn)var9.get(var14);
                        var15 = this.a((CrossCell)var16, (List)var5, (CrossColumn)var28);
                        if (var16 instanceof ValueCrossCell) {
                            var10.add(var15);
                        }
                    }
                }
            }
        }

        Iterator var23 = var10.iterator();

        while(var23.hasNext()) {
            CellRange var24 = (CellRange)var23.next();
            CrossCell var25 = var24.getCell();
            Rule var26 = new Rule();
            var26.setFile(var2);
            var26.setDebug(var1.getDebug());
            var26.setSalience(var1.getSalience());
            var26.setExpiresDate(var1.getExpiresDate());
            var26.setEffectiveDate(var1.getEffectiveDate());
            var26.setEnabled(var1.getEnabled());
            var26.setName("rule(" + var25.getRow() + "," + var25.getCol() + ")");
            Lhs var27 = new Lhs();
            And var29 = new And();
            var27.setCriterion(var29);
            var26.setLhs(var27);
            Rhs var18 = new Rhs();
            var26.setRhs(var18);
            var3.add(var26);
            ValueCrossCell var19 = (ValueCrossCell)var25;
            Value var20 = var19.getValue();
            VariableAssignAction var21 = new VariableAssignAction();
            var21.setValue(var20);
            var21.setDatatype(var1.getAssignDatatype());
            var21.setVariableName(var1.getAssignVariable());
            var21.setVariableLabel(var1.getAssignVariableLabel());
            var21.setVariableCategory(var1.getAssignVariableCategory());
            var21.setKeyLabel(var1.getKeyLabel());
            var21.setKeyName(var1.getKeyName());
            var18.addAction(var21);
            this.a(var29, var24);
            CellRange var22 = this.a(var4, var24.getCell().getCol(), var11);
            var24.setParentRange(var22);
            this.a(var29, var24);
        }

        return var3;
    }

    private CellRange a(List<CellRange> var1, int var2, int var3) {
        CellRange var4 = null;
        Iterator var5 = var1.iterator();

        while(var5.hasNext()) {
            CellRange var6 = (CellRange)var5.next();
            if (var6.getStart() == var2 && var6.getEnd() == var2 && var6.getCell().getRow() == var3) {
                var4 = var6;
                break;
            }

            var4 = this.a(var6.getChildren(), var2, var3);
            if (var4 != null) {
                break;
            }
        }

        return var4;
    }

    private void a(And var1, CellRange var2) {
        CellRange var3 = var2.getParentRange();
        if (var3 != null) {
            ConditionCrossCell var4 = (ConditionCrossCell)var3.getCell();
            Criterion var5 = this.buildCriterion(var4, var3);
            if (var5 != null) {
                var1.addCriterion(var5);
            }

            this.a(var1, var3);
        }
    }

    public Criterion buildCriterion(ConditionCrossCell var1, CellRange var2) {
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
                    return this.a((Condition)var4.get(0), var2);
                } else {
                    if (var3.getType().equals(JointType.and)) {
                        var6 = new And();
                    } else {
                        var6 = new Or();
                    }

                    this.b(var4, (Junction)var6, var2);
                    this.a((List)var5, (Junction)var6, (CellRange)var2);
                    return (Criterion)var6;
                }
            }
        }
    }

    private void a(List<Joint> var1, Junction var2, CellRange var3) {
        if (var1 != null && var1.size() != 0) {
            Iterator var4 = var1.iterator();

            while(var4.hasNext()) {
                Joint var5 = (Joint)var4.next();
                Junction var6 = var5.getJunction();
                List var7 = var5.getConditions();
                this.b(var7, var6, var3);
                List var8 = var5.getJoints();
                this.a(var8, var6, var3);
                var2.addCriterion(var6);
            }

        }
    }

    private void b(List<Condition> var1, Junction var2, CellRange var3) {
        if (var1 != null && var1.size() != 0) {
            Iterator var4 = var1.iterator();

            while(var4.hasNext()) {
                Condition var5 = (Condition)var4.next();
                Criteria var6 = this.a(var5, var3);
                var2.addCriterion(var6);
            }

        }
    }

    private Criteria a(Condition var1, CellRange var2) {
        Criteria var3 = new Criteria();
        Left var4 = new Left();
        VariableLeftPart var5 = new VariableLeftPart();
        var5.setVariableCategory(var2.getVariableCategory());
        var5.setVariableName(var2.getVariableName());
        var5.setVariableLabel(var2.getVariableLabel());
        var5.setDatatype(var2.getDatatype());
        var5.setKeyLabel(var2.getKeyLabel());
        var5.setKeyName(var2.getKeyName());
        var4.setLeftPart(var5);
        var4.setType(LeftType.variable);
        var3.setLeft(var4);
        var3.setOp(var1.getOp());
        var3.setValue(var1.getValue());
        return var3;
    }

    private CellRange a(CrossCell var1, List<CellRange> var2, TopRow var3) {
        int var4 = var1.getCol();
        int var5 = var1.getColspan();
        if (var5 > 0) {
            --var5;
        }

        int var6 = var4 + var5;
        CellRange var7 = new CellRange();
        var7.setStart(var4);
        var7.setEnd(var6);
        var7.setCell(var1);
        var7.setVariableCategory(var3.getVariableCategory());
        var7.setVariableName(var3.getVariableName());
        var7.setVariableLabel(var3.getVariableLabel());
        var7.setDatatype(var3.getDatatype());
        var7.setKeyLabel(var3.getKeyLabel());
        var7.setKeyName(var3.getKeyName());
        if (var4 == 1) {
            var2.add(var7);
        } else {
            CellRange var8 = this.a(var4, var6, var2);
            if (var8 != null) {
                var8.addChildRange(var7);
            } else {
                var2.add(var7);
            }
        }

        return var7;
    }

    private CellRange a(CrossCell var1, List<CellRange> var2, CrossColumn var3) {
        int var4 = var1.getRow();
        int var5 = var1.getRowspan();
        if (var5 > 0) {
            --var5;
        }

        int var6 = var4 + var5;
        CellRange var7 = new CellRange();
        var7.setStart(var4);
        var7.setEnd(var6);
        var7.setCell(var1);
        if (var3 instanceof LeftColumn) {
            LeftColumn var8 = (LeftColumn)var3;
            var7.setVariableCategory(var8.getVariableCategory());
            var7.setVariableName(var8.getVariableName());
            var7.setVariableLabel(var8.getVariableLabel());
            var7.setDatatype(var8.getDatatype());
            var7.setKeyLabel(var8.getKeyLabel());
            var7.setKeyName(var8.getKeyName());
        }

        if (var4 == 1) {
            var2.add(var7);
        } else {
            CellRange var9 = this.a(var4, var6, var2);
            if (var9 != null) {
                var9.addChildRange(var7);
            } else {
                var2.add(var7);
            }
        }

        return var7;
    }

    private CellRange a(int var1, int var2, List<CellRange> var3) {
        CellRange var4 = null;
        Iterator var5 = var3.iterator();

        while(var5.hasNext()) {
            CellRange var6 = (CellRange)var5.next();
            boolean var7 = false;
            if (!var6.isValueCell() && var6.getStart() <= var1 && var6.getEnd() >= var2) {
                var7 = true;
            }

            if (var7) {
                List var8 = var6.getChildren();
                if (var8.size() > 0) {
                    var4 = this.a(var1, var2, var8);
                }

                if (var4 == null) {
                    var4 = var6;
                }
                break;
            }
        }

        return var4;
    }

    private Map<String, CrossCell> a(List<CrossCell> var1) {
        HashMap var2 = new HashMap();
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            CrossCell var4 = (CrossCell)var3.next();
            String var5 = var4.getRow() + "," + var4.getCol();
            var2.put(var5, var4);
        }

        return var2;
    }
}
