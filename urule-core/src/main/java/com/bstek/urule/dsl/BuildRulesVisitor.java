//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl;

import com.bstek.urule.Configure;
import com.bstek.urule.action.Action;
import com.bstek.urule.dsl.RuleParserParser.ActionContext;
import com.bstek.urule.dsl.RuleParserParser.AttributeContext;
import com.bstek.urule.dsl.RuleParserParser.ComplexValueContext;
import com.bstek.urule.dsl.RuleParserParser.ExpressionBodyContext;
import com.bstek.urule.dsl.RuleParserParser.FunctionImportContext;
import com.bstek.urule.dsl.RuleParserParser.JoinContext;
import com.bstek.urule.dsl.RuleParserParser.LeftContext;
import com.bstek.urule.dsl.RuleParserParser.LoopEndContext;
import com.bstek.urule.dsl.RuleParserParser.LoopRuleDefContext;
import com.bstek.urule.dsl.RuleParserParser.LoopRuleUnitContext;
import com.bstek.urule.dsl.RuleParserParser.LoopStartContext;
import com.bstek.urule.dsl.RuleParserParser.LoopTargetContext;
import com.bstek.urule.dsl.RuleParserParser.MultiConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.OtherContext;
import com.bstek.urule.dsl.RuleParserParser.ParenConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.ResourceContext;
import com.bstek.urule.dsl.RuleParserParser.RightContext;
import com.bstek.urule.dsl.RuleParserParser.RuleDefContext;
import com.bstek.urule.dsl.RuleParserParser.RuleSetBodyContext;
import com.bstek.urule.dsl.RuleParserParser.RuleSetContext;
import com.bstek.urule.dsl.RuleParserParser.RuleSetHeaderContext;
import com.bstek.urule.dsl.RuleParserParser.RulesContext;
import com.bstek.urule.dsl.RuleParserParser.SingleConditionContext;
import com.bstek.urule.dsl.builder.BuildUtils;
import com.bstek.urule.dsl.builder.ContextBuilder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.Other;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.RuleSet;
import com.bstek.urule.model.rule.lhs.And;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Junction;
import com.bstek.urule.model.rule.lhs.Lhs;
import com.bstek.urule.model.rule.lhs.Or;
import com.bstek.urule.model.rule.loop.LoopEnd;
import com.bstek.urule.model.rule.loop.LoopRule;
import com.bstek.urule.model.rule.loop.LoopRuleUnit;
import com.bstek.urule.model.rule.loop.LoopStart;
import com.bstek.urule.model.rule.loop.LoopTarget;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;

public class BuildRulesVisitor extends RuleParserBaseVisitor<Object> {
    private Map<ParseTree, Junction> a = new HashMap();
    private Collection<ContextBuilder> b;
    private CommonTokenStream c;

    public BuildRulesVisitor(Collection<ContextBuilder> var1, CommonTokenStream var2) {
        this.b = var1;
        this.c = var2;
    }

    public RuleSet buildRuleSet(RuleSetContext var1, String var2) {
        RuleSet var3 = this.visitRuleSet(var1);
        Iterator var4 = var3.getRules().iterator();

        while(var4.hasNext()) {
            Rule var5 = (Rule)var4.next();
            var5.setFile(var2);
        }

        return var3;
    }

    public RuleSet visitRuleSet(RuleSetContext var1) {
        RuleSet var2 = new RuleSet();
        RuleSetHeaderContext var3 = var1.ruleSetHeader();
        List var4 = var3.resource();
        Iterator var5;
        if (var4 != null) {
            var5 = var4.iterator();

            while(var5.hasNext()) {
                ResourceContext var6 = (ResourceContext)var5.next();
                var2.addLibrary(this.visitResource(var6));
            }
        }

        var5 = null;
        List var17 = var3.functionImport();
        if (var17 != null) {
            StringBuffer var16 = new StringBuffer();
            Iterator var7 = var17.iterator();

            while(var7.hasNext()) {
                FunctionImportContext var8 = (FunctionImportContext)var7.next();
                var16.append("import ");
                var16.append(var8.packageDef().getText());
                var16.append(";");
            }
        }

        RuleSetBodyContext var18 = var1.ruleSetBody();
        List var19 = var18.rules();
        if (var19 != null) {
            ArrayList var9 = new ArrayList();
            var2.setRules(var9);
            Iterator var10 = var19.iterator();

            while(var10.hasNext()) {
                RulesContext var11 = (RulesContext)var10.next();
                RuleDefContext var12 = var11.ruleDef();
                if (var12 != null) {
                    Rule var13 = this.visitRuleDef(var12);
                    var9.add(var13);
                }

                LoopRuleDefContext var15 = var11.loopRuleDef();
                if (var15 != null) {
                    LoopRule var14 = this.visitLoopRuleDef(var15);
                    var9.add(var14);
                }
            }
        }

        return var2;
    }

    private String a(ExpressionBodyContext var1) {
        StringBuffer var2 = new StringBuffer();
        Iterator var3 = var1.children.iterator();

        while(var3.hasNext()) {
            ParseTree var4 = (ParseTree)var3.next();
            Interval var5 = var4.getSourceInterval();
            int var6 = var5.a;
            List var7 = this.c.getHiddenTokensToLeft(var6);
            if (var7 != null) {
                Token var8 = (Token)var7.get(0);
                String var9 = var8.getText();
                var2.append(var9);
            }

            var2.append(var4.getText());
            List var11 = this.c.getHiddenTokensToRight(var6);
            if (var11 != null) {
                Token var12 = (Token)var11.get(0);
                String var10 = var12.getText();
                var2.append(var10);
            }
        }

        return var2.toString();
    }

    public Library visitResource(ResourceContext var1) {
        return (Library)this.a((ParserRuleContext)var1);
    }

    public LoopRule visitLoopRuleDef(LoopRuleDefContext var1) {
        SimpleDateFormat var2 = new SimpleDateFormat(Configure.getDateFormat());
        LoopRule var3 = new LoopRule();
        String var4 = var1.STRING().getText();
        var4 = var4.substring(1, var4.length() - 1);
        var3.setName(var4);
        LoopTargetContext var5 = var1.loopTarget();
        ComplexValueContext var6 = var5.complexValue();
        LoopTarget var7 = new LoopTarget();
        var7.setValue(BuildUtils.buildValue(var6));
        var3.setLoopTarget(var7);
        LoopStartContext var8 = var1.loopStart();
        if (var8 != null) {
            List var9 = var8.action();
            if (var9 != null) {
                LoopStart var10 = new LoopStart();
                var10.setActions(this.a(var9));
                var3.setLoopStart(var10);
            }
        }

        LoopEndContext var24 = var1.loopEnd();
        List var25;
        if (var24 != null) {
            var25 = var24.action();
            if (var25 != null) {
                LoopEnd var11 = new LoopEnd();
                var11.setActions(this.a(var25));
                var3.setLoopEnd(var11);
            }
        }

        var25 = var1.attribute();
        if (var25 != null) {
            Iterator var26 = var25.iterator();

            while(var26.hasNext()) {
                AttributeContext var12 = (AttributeContext)var26.next();
                if (var12.salienceAttribute() != null) {
                    var3.setSalience(Integer.valueOf(var12.salienceAttribute().NUMBER().getText()));
                } else if (var12.loopAttribute() != null) {
                    var3.setLoop(Boolean.valueOf(var12.loopAttribute().Boolean().getText()));
                } else {
                    String var13;
                    if (var12.effectiveDateAttribute() != null) {
                        try {
                            var13 = var12.effectiveDateAttribute().STRING().getText();
                            var13 = var13.substring(1, var13.length() - 1);
                            var3.setEffectiveDate(var2.parse(var13));
                        } catch (ParseException var23) {
                            throw new RuleException(var23);
                        }
                    } else if (var12.expiresDateAttribute() != null) {
                        try {
                            var13 = var12.expiresDateAttribute().STRING().getText();
                            var13 = var13.substring(1, var13.length() - 1);
                            var3.setExpiresDate(var2.parse(var13));
                        } catch (ParseException var22) {
                            throw new RuleException(var22);
                        }
                    } else if (var12.enabledAttribute() != null) {
                        var3.setEnabled(Boolean.valueOf(var12.enabledAttribute().Boolean().getText()));
                    } else if (var12.debugAttribute() != null) {
                        var3.setDebug(Boolean.valueOf(var12.debugAttribute().Boolean().getText()));
                    } else if (var12.activationGroupAttribute() != null) {
                        var13 = var12.activationGroupAttribute().STRING().getText();
                        var13 = var13.substring(1, var13.length() - 1);
                        var3.setMutexGroup(var13);
                    } else if (var12.agendaGroupAttribute() != null) {
                        var13 = var12.agendaGroupAttribute().STRING().getText();
                        var13 = var13.substring(1, var13.length() - 1);
                        var3.setPendedGroup(var13);
                    } else if (var12.autoFocusAttribute() != null) {
                        var3.setAutoFocus(Boolean.valueOf(var12.autoFocusAttribute().Boolean().getText()));
                    }
                }
            }
        }

        ArrayList var27 = new ArrayList();
        var3.setUnits(var27);
        List var28 = var1.loopRuleUnit();
        Iterator var29 = var28.iterator();

        while(var29.hasNext()) {
            LoopRuleUnitContext var14 = (LoopRuleUnitContext)var29.next();
            LoopRuleUnit var15 = new LoopRuleUnit();
            var27.add(var15);
            if (var14.STRING() != null) {
                String var16 = var14.STRING().getText();
                if (var16.length() > 2) {
                    var16 = var16.substring(1, var4.length() - 1);
                    var15.setName(var16);
                } else {
                    var15.setName("");
                }
            }

            LeftContext var30 = var14.left();
            ParseTree var17 = var30.getChild(1);
            Lhs var18 = new Lhs();
            var15.setLhs(var18);
            Criterion var19 = this.buildCriterion(var17);
            var18.setCriterion(var19);
            Rhs var20 = new Rhs();
            var20.setActions(this.visitRight(var14.right()));
            var15.setRhs(var20);
            Other var21 = new Other();
            var21.setActions(this.visitOther(var14.other()));
            var15.setOther(var21);
        }

        return var3;
    }

    public Rule visitRuleDef(RuleDefContext var1) {
        SimpleDateFormat var2 = new SimpleDateFormat(Configure.getDateFormat());
        Rule var3 = new Rule();
        String var4 = var1.STRING().getText();
        var4 = var4.substring(1, var4.length() - 1);
        var3.setName(var4);
        List var5 = var1.attribute();
        if (var5 != null) {
            Iterator var6 = var5.iterator();

            while(var6.hasNext()) {
                AttributeContext var7 = (AttributeContext)var6.next();
                if (var7.salienceAttribute() != null) {
                    var3.setSalience(Integer.valueOf(var7.salienceAttribute().NUMBER().getText()));
                } else if (var7.loopAttribute() != null) {
                    var3.setLoop(Boolean.valueOf(var7.loopAttribute().Boolean().getText()));
                } else {
                    String var8;
                    if (var7.effectiveDateAttribute() != null) {
                        try {
                            var8 = var7.effectiveDateAttribute().STRING().getText();
                            var8 = var8.substring(1, var8.length() - 1);
                            var3.setEffectiveDate(var2.parse(var8));
                        } catch (ParseException var13) {
                            throw new RuleException(var13);
                        }
                    } else if (var7.expiresDateAttribute() != null) {
                        try {
                            var8 = var7.expiresDateAttribute().STRING().getText();
                            var8 = var8.substring(1, var8.length() - 1);
                            var3.setExpiresDate(var2.parse(var8));
                        } catch (ParseException var12) {
                            throw new RuleException(var12);
                        }
                    } else if (var7.enabledAttribute() != null) {
                        var3.setEnabled(Boolean.valueOf(var7.enabledAttribute().Boolean().getText()));
                    } else if (var7.debugAttribute() != null) {
                        var3.setDebug(Boolean.valueOf(var7.debugAttribute().Boolean().getText()));
                    } else if (var7.activationGroupAttribute() != null) {
                        var8 = var7.activationGroupAttribute().STRING().getText();
                        var8 = var8.substring(1, var8.length() - 1);
                        var3.setMutexGroup(var8);
                    } else if (var7.agendaGroupAttribute() != null) {
                        var8 = var7.agendaGroupAttribute().STRING().getText();
                        var8 = var8.substring(1, var8.length() - 1);
                        var3.setPendedGroup(var8);
                    } else if (var7.autoFocusAttribute() != null) {
                        var3.setAutoFocus(Boolean.valueOf(var7.autoFocusAttribute().Boolean().getText()));
                    }
                }
            }
        }

        LeftContext var14 = var1.left();
        ParseTree var15 = var14.getChild(1);
        Lhs var16 = new Lhs();
        var3.setLhs(var16);
        Criterion var9 = this.buildCriterion(var15);
        var16.setCriterion(var9);
        Rhs var10 = new Rhs();
        var10.setActions(this.visitRight(var1.right()));
        var3.setRhs(var10);
        Other var11 = new Other();
        var11.setActions(this.visitOther(var1.other()));
        var3.setOther(var11);
        return var3;
    }

    public Criteria visitSingleCondition(SingleConditionContext var1) {
        return (Criteria)this.a((ParserRuleContext)var1);
    }

    public Criterion visitParenConditions(ParenConditionsContext var1) {
        ParseTree var2 = var1.getChild(1);
        return this.buildCriterion(var2);
    }

    public Criterion visitMultiConditions(MultiConditionsContext var1) {
        Object var2 = null;
        Criterion var3 = null;
        Object var4 = (Junction)this.a.get(var1);
        int var5 = var1.getChildCount();

        for(int var6 = 0; var6 < var5; ++var6) {
            ParseTree var7 = var1.getChild(var6);
            if (var7 instanceof JoinContext) {
                JoinContext var8 = (JoinContext)var7;
                if (var8.AND() != null) {
                    if (var4 == null) {
                        var4 = new And();
                        var2 = var4;
                        ((Junction)var4).addCriterion(var3);
                    } else if (!(var4 instanceof And)) {
                        And var9 = new And();
                        ((Junction)var4).addCriterion(var9);
                        var4 = var9;
                    }
                } else if (var4 == null) {
                    var4 = new Or();
                    var2 = var4;
                    ((Junction)var4).addCriterion(var3);
                } else if (!(var4 instanceof Or)) {
                    Or var11 = new Or();
                    ((Junction)var4).addCriterion(var11);
                    var4 = var11;
                }
            } else {
                boolean var10 = false;
                if (var7 instanceof MultiConditionsContext) {
                    var10 = true;
                }

                if (var4 != null && var10) {
                    this.a.put(var7, (Junction) var4);
                }

                var3 = this.buildCriterion(var7);
                if (var4 != null && !var10) {
                    ((Junction)var4).addCriterion(var3);
                }
            }
        }

        if (var2 != null) {
            return (Criterion)var2;
        } else {
            return var3;
        }
    }

    public List<Action> visitRight(RightContext var1) {
        if (var1 != null && var1.action() != null) {
            List var2 = var1.action();
            return this.a(var2);
        } else {
            return null;
        }
    }

    private List<Action> a(List<ActionContext> var1) {
        ArrayList var2 = new ArrayList();
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            ActionContext var4 = (ActionContext)var3.next();
            Action var5 = (Action)this.a((ParserRuleContext)var4);
            var2.add(var5);
        }

        return var2;
    }

    public List<Action> visitOther(OtherContext var1) {
        if (var1 != null && var1.action() != null) {
            ArrayList var2 = new ArrayList();
            Iterator var3 = var1.action().iterator();

            while(var3.hasNext()) {
                ActionContext var4 = (ActionContext)var3.next();
                Action var5 = (Action)this.a((ParserRuleContext)var4);
                var2.add(var5);
            }

            return var2;
        } else {
            return null;
        }
    }

    public Criterion buildCriterion(ParseTree var1) {
        Object var2 = null;
        if (var1 instanceof ParenConditionsContext) {
            var2 = this.visitParenConditions((ParenConditionsContext)var1);
        } else if (var1 instanceof SingleConditionContext) {
            var2 = this.visitSingleCondition((SingleConditionContext)var1);
        } else if (var1 instanceof MultiConditionsContext) {
            var2 = this.visitMultiConditions((MultiConditionsContext)var1);
        }

        return (Criterion)var2;
    }

    private Object a(ParserRuleContext var1) {
        Iterator var2 = this.b.iterator();

        ContextBuilder var3;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (ContextBuilder)var2.next();
        } while(!var3.support(var1));

        return var3.build(var1);
    }
}
