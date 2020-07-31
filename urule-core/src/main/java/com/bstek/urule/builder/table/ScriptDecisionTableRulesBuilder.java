//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.table;

import com.bstek.urule.dsl.DSLRuleSetBuilder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.rule.RuleSet;
import com.bstek.urule.model.table.Column;
import com.bstek.urule.model.table.ColumnType;
import com.bstek.urule.model.table.Row;
import com.bstek.urule.model.table.ScriptCell;
import com.bstek.urule.model.table.ScriptDecisionTable;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class ScriptDecisionTableRulesBuilder {
    private CellScriptDSLBuilder a;
    private DSLRuleSetBuilder b;

    public ScriptDecisionTableRulesBuilder() {
    }

    public RuleSet buildRules(ScriptDecisionTable var1, String var2) throws IOException {
        List var3 = var1.getRows();
        List var4 = var1.getColumns();
        List var5 = var1.getLibraries();
        StringBuffer var6 = this.a(var5);
        Iterator var7 = var3.iterator();

        while(var7.hasNext()) {
            Row var8 = (Row)var7.next();
            var6.append("rule \"r" + var8.getNum() + "\"");
            var6.append("\r\n");
            var6.append("if");
            var6.append("\r\n");
            StringBuffer var9 = new StringBuffer();
            StringBuffer var10 = new StringBuffer();
            Iterator var11 = var4.iterator();

            while(var11.hasNext()) {
                Column var12 = (Column)var11.next();
                ScriptCell var13 = this.a(var1, var8.getNum(), var12.getNum());
                String var14 = var13.getScript();
                if (!StringUtils.isBlank(var14)) {
                    ColumnType var15 = var12.getType();
                    String var16;
                    switch(var15) {
                        case Criteria:
                            var16 = var12.getVariableCategory() + "." + var12.getVariableLabel();
                            String var17 = this.a.buildCriteriaScript(var14, var16);
                            if (!StringUtils.isBlank(var17)) {
                                var17 = var17.trim();
                                if (var9.length() > 1) {
                                    var9.append(" and ");
                                }

                                if (!var17.startsWith("(")) {
                                    var17 = "(" + var17 + ")";
                                }

                                var9.append(var17);
                            }
                            break;
                        case ConsolePrint:
                            var10.append("out(" + var14 + ");\r\n");
                            break;
                        case Assignment:
                            var16 = var12.getVariableCategory() + "." + var12.getVariableLabel();
                            var10.append(var16 + " = " + var14 + ";\r\n");
                            break;
                        case ExecuteMethod:
                            var10.append(var14 + ";\r\n");
                    }
                }
            }

            var6.append(var9);
            var6.append("\r\n");
            var6.append("then");
            var6.append("\r\n");
            var6.append(var10);
            var6.append("\r\n");
            var6.append("end;");
            var6.append("\r\n");
        }

        RuleSet var18 = this.b.build(var6.toString(), var2);
        return var18;
    }

    private StringBuffer a(List<Library> var1) {
        StringBuffer var2 = new StringBuffer();
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            Library var4 = (Library)var3.next();
            LibraryType var5 = var4.getType();
            switch(var5) {
                case Action:
                    var2.append("importActionLibrary \"" + var4.getPath() + "\";\r\n");
                    break;
                case Constant:
                    var2.append("importConstantLibrary \"" + var4.getPath() + "\";\r\n");
                    break;
                case Parameter:
                    var2.append("importParameterLibrary \"" + var4.getPath() + "\";\r\n");
                    break;
                case Variable:
                    var2.append("importVariableLibrary \"" + var4.getPath() + "\";\r\n");
            }
        }

        return var2;
    }

    private ScriptCell a(ScriptDecisionTable var1, int var2, int var3) {
        Map var4 = var1.getCellMap();
        ScriptCell var5 = null;

        for(int var6 = var2; var6 > -1; --var6) {
            String var7 = var1.buildCellKey(var6, var3);
            if (var4.containsKey(var7)) {
                var5 = (ScriptCell)var4.get(var7);
                break;
            }
        }

        if (var5 == null) {
            throw new RuleException("Decision table cell[" + var2 + "," + var3 + "] not exist.");
        } else {
            return var5;
        }
    }

    public void setCellScriptDSLBuilder(CellScriptDSLBuilder var1) {
        this.a = var1;
    }

    public void setDslRuleSetBuilder(DSLRuleSetBuilder var1) {
        this.b = var1;
    }
}
