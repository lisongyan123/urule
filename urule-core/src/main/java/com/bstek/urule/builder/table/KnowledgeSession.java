//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.table;

import com.bstek.urule.action.AbstractAction;
import com.bstek.urule.action.Action;
import com.bstek.urule.action.ConsolePrintAction;
import com.bstek.urule.action.VariableAssignAction;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.lhs.And;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Lhs;
import com.bstek.urule.model.table.Cell;
import com.bstek.urule.model.table.Column;
import com.bstek.urule.model.table.ColumnType;
import com.bstek.urule.model.table.DecisionTable;
import com.bstek.urule.model.table.Row;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DecisionTableRulesBuilder {
    private CellContentBuilder a;

    public DecisionTableRulesBuilder() {
    }

    public List<Rule> buildRules(DecisionTable var1, String var2) {
        ArrayList var3 = new ArrayList();
        List var4 = var1.getRows();
        List var5 = var1.getColumns();
        Iterator var6 = var4.iterator();

        while(var6.hasNext()) {
            Row var7 = (Row)var6.next();
            Rule var8 = new Rule();
            var8.setFile(var2);
            var8.setDebug(var1.getDebug());
            var8.setSalience(var1.getSalience());
            var8.setExpiresDate(var1.getExpiresDate());
            var8.setEffectiveDate(var1.getEffectiveDate());
            var8.setEnabled(var1.getEnabled());
            var8.setName("r" + var7.getNum());
            Lhs var9 = new Lhs();
            And var10 = new And();
            var9.setCriterion(var10);
            var8.setLhs(var9);
            Rhs var11 = new Rhs();
            var8.setRhs(var11);
            var3.add(var8);
            Value var12 = null;
            Iterator var13 = var5.iterator();

            while(var13.hasNext()) {
                Column var14 = (Column)var13.next();
                Cell var15 = this.a(var1, var7.getNum(), var14.getNum());
                ColumnType var16 = var14.getType();
                switch(var16) {
                    case Criteria:
                        Criterion var17 = this.a.buildCriterion(var15, var14);
                        if (var17 != null) {
                            var10.addCriterion(var17);
                        }
                        break;
                    case ConsolePrint:
                        var12 = var15.getValue();
                        if (var12 != null) {
                            ConsolePrintAction var21 = new ConsolePrintAction();
                            var21.setPriority(1000 - var14.getNum());
                            var21.setValue(var12);
                            var11.addAction(var21);
                        }
                        break;
                    case Assignment:
                        var12 = var15.getValue();
                        if (var12 != null) {
                            VariableAssignAction var20 = new VariableAssignAction();
                            var20.setPriority(1000 - var14.getNum());
                            var20.setValue(var12);
                            var20.setDatatype(var14.getDatatype());
                            var20.setVariableName(var14.getVariableName());
                            var20.setVariableLabel(var14.getVariableLabel());
                            var20.setVariableCategory(var14.getVariableCategory());
                            var20.setKeyLabel(var14.getKeyLabel());
                            var20.setKeyName(var14.getKeyName());
                            var11.addAction(var20);
                        }
                        break;
                    case ExecuteMethod:
                        Action var18 = var15.getAction();
                        if (var18 != null) {
                            AbstractAction var19 = (AbstractAction)var18;
                            var19.setPriority(1000 - var14.getNum());
                            var11.addAction(var19);
                        }
                }
            }
        }

        return var3;
    }

    private Cell a(DecisionTable var1, int var2, int var3) {
        Map var4 = var1.getCellMap();
        Cell var5 = null;

        for(int var6 = var2; var6 > -1; --var6) {
            String var7 = var1.buildCellKey(var6, var3);
            if (var4.containsKey(var7)) {
                var5 = (Cell)var4.get(var7);
                break;
            }
        }

        if (var5 == null) {
            throw new RuleException("Decision table cell[" + var2 + "," + var3 + "] not exist.");
        } else {
            return var5;
        }
    }

    public void setCellContentBuilder(CellContentBuilder var1) {
        this.a = var1;
    }
}
