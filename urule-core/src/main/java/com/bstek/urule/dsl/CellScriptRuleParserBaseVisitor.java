//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl;

import com.bstek.urule.dsl.RuleParserParser.DecisionTableCellConditionContext;
import com.bstek.urule.dsl.RuleParserParser.MultiCellConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.ParenCellConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.SingleCellConditionContext;
import java.util.Iterator;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTree;

public class CellScriptRuleParserBaseVisitor extends RuleParserBaseVisitor<String> {
    private String a;

    public CellScriptRuleParserBaseVisitor(String var1) {
        this.a = var1;
    }

    public String visitSingleCellCondition(SingleCellConditionContext var1) {
        StringBuffer var2 = new StringBuffer();
        var2.append(this.a);
        var2.append(" ");
        String var3 = var1.op().getText();
        var2.append(var3);
        var2.append(" ");
        if (var1.complexValue() != null) {
            var2.append(var1.complexValue().getText());
        } else {
            var2.append(var1.nullValue().getText());
        }

        var2.append(" ");
        return var2.toString();
    }

    public String visitMultiCellConditions(MultiCellConditionsContext var1) {
        StringBuffer var2 = new StringBuffer();
        List var3 = var1.children;
        Iterator var4 = var3.iterator();

        while(var4.hasNext()) {
            ParseTree var5 = (ParseTree)var4.next();
            var2.append(" ");
            this.a(var2, var5);
        }

        return var2.toString();
    }

    public String visitParenCellConditions(ParenCellConditionsContext var1) {
        StringBuffer var2 = new StringBuffer();
        var2.append(" ");
        var2.append(var1.leftParen().getText());
        DecisionTableCellConditionContext var3 = var1.decisionTableCellCondition();
        this.a(var2, var3);
        var2.append(var1.rightParen().getText());
        return var2.toString();
    }

    private void a(StringBuffer var1, ParseTree var2) {
        if (var2 instanceof SingleCellConditionContext) {
            SingleCellConditionContext var3 = (SingleCellConditionContext)var2;
            var1.append(this.visitSingleCellCondition(var3));
        } else if (var2 instanceof ParenCellConditionsContext) {
            ParenCellConditionsContext var4 = (ParenCellConditionsContext)var2;
            var1.append(this.visitParenCellConditions(var4));
        } else if (var2 instanceof MultiCellConditionsContext) {
            MultiCellConditionsContext var5 = (MultiCellConditionsContext)var2;
            var1.append(this.visitMultiCellConditions(var5));
        } else {
            var1.append(var2.getText());
        }

    }
}
