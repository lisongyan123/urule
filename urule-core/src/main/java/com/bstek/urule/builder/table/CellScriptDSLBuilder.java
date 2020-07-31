//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.table;

import com.bstek.urule.dsl.CellScriptRuleParserBaseVisitor;
import com.bstek.urule.dsl.RuleParserLexer;
import com.bstek.urule.dsl.RuleParserParser;
import com.bstek.urule.dsl.ScriptDecisionTableErrorListener;
import com.bstek.urule.exception.RuleException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class CellScriptDSLBuilder {
    public CellScriptDSLBuilder() {
    }

    public String buildCriteriaScript(String var1, String var2) {
        ANTLRInputStream var3 = new ANTLRInputStream(var1);
        RuleParserLexer var4 = new RuleParserLexer(var3);
        CommonTokenStream var5 = new CommonTokenStream(var4);
        RuleParserParser var6 = new RuleParserParser(var5);
        ScriptDecisionTableErrorListener var7 = new ScriptDecisionTableErrorListener();
        var6.addErrorListener(var7);
        CellScriptRuleParserBaseVisitor var8 = new CellScriptRuleParserBaseVisitor(var2);
        String var9 = (String)var8.visit(var6.decisionTableCellCondition());
        String var10 = var7.getErrorMessage();
        if (var10 != null) {
            throw new RuleException("Script Parse error:" + var10);
        } else {
            return var9;
        }
    }
}
