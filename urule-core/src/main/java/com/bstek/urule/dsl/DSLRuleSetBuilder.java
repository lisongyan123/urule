//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl;

import com.bstek.urule.builder.RulesRebuilder;
import com.bstek.urule.builder.resource.Resource;
import com.bstek.urule.dsl.builder.BuildUtils;
import com.bstek.urule.dsl.builder.ContextBuilder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.AbstractValue;
import com.bstek.urule.model.rule.RuleSet;
import com.bstek.urule.model.rule.lhs.Criterion;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DSLRuleSetBuilder implements ApplicationContextAware {
    public static final String BEAN_ID = "urule.dslRuleSetBuilder";
    private Collection<ContextBuilder> a;
    private RulesRebuilder b;

    public DSLRuleSetBuilder() {
    }

    public RuleSet build(String var1, String var2) throws IOException {
        ANTLRInputStream var3 = new ANTLRInputStream(var1);
        RuleParserLexer var4 = new RuleParserLexer(var3);
        CommonTokenStream var5 = new CommonTokenStream(var4);
        RuleParserParser var6 = new RuleParserParser(var5);
        ScriptDecisionTableErrorListener var7 = new ScriptDecisionTableErrorListener();
        var6.addErrorListener(var7);
        BuildRulesVisitor var8 = new BuildRulesVisitor(this.a, var5);
        RuleSet var9 = var8.buildRuleSet(var6.ruleSet(), var2);
        this.a(var9);
        String var10 = var7.getErrorMessage();
        if (var10 != null) {
            throw new RuleException("Script parse error:" + var10);
        } else {
            return var9;
        }
    }

    public Criterion buildCriterion(String var1) throws IOException {
        ANTLRInputStream var2 = new ANTLRInputStream(var1);
        RuleParserLexer var3 = new RuleParserLexer(var2);
        CommonTokenStream var4 = new CommonTokenStream(var3);
        RuleParserParser var5 = new RuleParserParser(var4);
        ScriptDecisionTableErrorListener var6 = new ScriptDecisionTableErrorListener();
        var5.addErrorListener(var6);
        BuildRulesVisitor var7 = new BuildRulesVisitor(this.a, var4);
        Criterion var8 = var7.buildCriterion(var5.condition());
        String var9 = var6.getErrorMessage();
        if (var9 != null) {
            throw new RuleException("Script parse error:" + var9);
        } else {
            return var8;
        }
    }

    public AbstractValue buildValue(String var1) throws IOException {
        ANTLRInputStream var2 = new ANTLRInputStream(var1);
        RuleParserLexer var3 = new RuleParserLexer(var2);
        CommonTokenStream var4 = new CommonTokenStream(var3);
        RuleParserParser var5 = new RuleParserParser(var4);
        ScriptDecisionTableErrorListener var6 = new ScriptDecisionTableErrorListener();
        var5.addErrorListener(var6);
        AbstractValue var7 = BuildUtils.buildValue(var5.complexValue());
        String var8 = var6.getErrorMessage();
        if (var8 != null) {
            throw new RuleException("Script parse error:" + var8);
        } else {
            return var7;
        }
    }

    private void a(RuleSet var1) {
        List var2 = var1.getLibraries();
        List var3 = var1.getRules();
        this.b.rebuildRulesForDSL(var2, var3);
    }

    public void setRulesRebuilder(RulesRebuilder var1) {
        this.b = var1;
    }

    public boolean support(Resource var1) {
        String var2 = var1.getPath();
        return var2.toLowerCase().endsWith(".ul");
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.a = var1.getBeansOfType(ContextBuilder.class).values();
    }
}
