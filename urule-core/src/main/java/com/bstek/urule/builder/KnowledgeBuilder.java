//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder;

import com.bstek.urule.action.Action;
import com.bstek.urule.action.TemplateAction;
import com.bstek.urule.builder.resource.Resource;
import com.bstek.urule.builder.resource.ResourceBuilder;
import com.bstek.urule.builder.resource.ResourceType;
import com.bstek.urule.builder.rete.IdGenerator;
import com.bstek.urule.builder.rete.ReteBuilder;
import com.bstek.urule.builder.table.CrosstabRulesBuilder;
import com.bstek.urule.builder.table.DecisionTableRulesBuilder;
import com.bstek.urule.builder.table.ScriptDecisionTableRulesBuilder;
import com.bstek.urule.dsl.DSLRuleSetBuilder;
import com.bstek.urule.model.crosstab.CrosstabDefinition;
import com.bstek.urule.model.decisiontree.DecisionTree;
import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.rete.Rete;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.Other;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.RuleSet;
import com.bstek.urule.model.rule.lhs.ConditionTemplateCriterion;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Junction;
import com.bstek.urule.model.rule.lhs.Lhs;
import com.bstek.urule.model.rule.loop.LoopRule;
import com.bstek.urule.model.rule.loop.LoopRuleUnit;
import com.bstek.urule.model.scorecard.runtime.ScoreRule;
import com.bstek.urule.model.table.DecisionTable;
import com.bstek.urule.model.table.ScriptDecisionTable;
import com.bstek.urule.model.template.ActionTemplateUnit;
import com.bstek.urule.model.template.ConditionTemplateUnit;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import com.bstek.urule.runtime.service.KnowledgePackageService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Element;

public class KnowledgeBuilder extends AbstractBuilder {
    private ResourceLibraryBuilder a;
    private ReteBuilder b;
    private RulesRebuilder c;
    private DecisionTreeRulesBuilder d;
    private DecisionTableRulesBuilder e;
    private ScriptDecisionTableRulesBuilder f;
    private DSLRuleSetBuilder g;
    private CrosstabRulesBuilder h;
    public static final String BEAN_ID = "urule.knowledgeBuilder";

    public KnowledgeBuilder() {
    }

    public KnowledgeBase buildKnowledgeBase(ResourceBase var1) throws IOException {
        KnowledgePackageService var2 = (KnowledgePackageService)this.applicationContext.getBean("urule.knowledgePackageService");
        ArrayList var3 = new ArrayList();
        ArrayList var4 = new ArrayList();
        HashMap var5 = new HashMap();
        HashMap var6 = new HashMap();
        ArrayList var7 = new ArrayList();
        Iterator var8 = var1.getResources().iterator();

        while(true) {
            while(var8.hasNext()) {
                Resource var9 = (Resource)var8.next();
                String var10 = var9.getPath();
                if (this.g.support(var9)) {
                    RuleSet var23 = this.g.build(var9.getContent(), var10);
                    this.a((Map)var5, (List)var23.getLibraries());
                    if (var23.getRules() != null) {
                        var3.addAll(var23.getRules());
                    }
                } else {
                    Element var11 = this.parseResource(var9.getContent());
                    Iterator var12 = this.resourceBuilders.iterator();

                    while(var12.hasNext()) {
                        ResourceBuilder var13 = (ResourceBuilder)var12.next();
                        if (var13.support(var11)) {
                            Object var14 = var13.build(var11, var10);
                            ResourceType var15 = var13.getType();
                            List var17;
                            if (!var15.equals(ResourceType.RuleSet)) {
                                RuleSet var30;
                                if (var15.equals(ResourceType.DecisionTree)) {
                                    DecisionTree var27 = (DecisionTree)var14;
                                    this.a((Map)var5, (List)var27.getLibraries());
                                    var30 = this.d.buildRules(var27, var10);
                                    this.a((Map)var5, (List)var30.getLibraries());
                                    if (var30.getRules() != null) {
                                        var3.addAll(var30.getRules());
                                    }
                                } else if (var15.equals(ResourceType.DecisionTable)) {
                                    DecisionTable var28 = (DecisionTable)var14;
                                    this.a((Map)var5, (List)var28.getLibraries());
                                    var17 = this.e.buildRules(var28, var10);
                                    var3.addAll(var17);
                                } else if (var15.equals(ResourceType.CrossDecisionTable)) {
                                    CrosstabDefinition var29 = (CrosstabDefinition)var14;
                                    this.a((Map)var5, (List)var29.getLibraries());
                                    var17 = this.h.buildRules(var29, var10);
                                    var3.addAll(var17);
                                } else if (var15.equals(ResourceType.ScriptDecisionTable)) {
                                    ScriptDecisionTable var31 = (ScriptDecisionTable)var14;
                                    var30 = this.f.buildRules(var31, var10);
                                    this.a((Map)var5, (List)var30.getLibraries());
                                    if (var30.getRules() != null) {
                                        var3.addAll(var30.getRules());
                                    }
                                } else if (var15.equals(ResourceType.Flow)) {
                                    FlowDefinition var32 = (FlowDefinition)var14;
                                    var32.setFile(var10);
                                    var32 = var32.newFlowDefinitionForSerialize(this, var2, this.g);
                                    var7.addAll(var32.getRelationResourceLibraries());
                                    this.a((Map)var5, (List)var32.getLibraries());
                                    var6.put(var32.getId(), var32);
                                } else {
                                    ScoreRule var33;
                                    if (var15.equals(ResourceType.Scorecard)) {
                                        var33 = (ScoreRule)var14;
                                        var33.setFile(var10);
                                        var3.add(var33);
                                        this.a((Map)var5, (List)var33.getLibraries());
                                    } else if (var15.equals(ResourceType.ComplexScorecard)) {
                                        var33 = (ScoreRule)var14;
                                        var33.setFile(var10);
                                        var3.add(var33);
                                        this.a((Map)var5, (List)var33.getLibraries());
                                    }
                                }
                                break;
                            }

                            RuleSet var16 = (RuleSet)var14;
                            this.a((Map)var5, (List)var16.getLibraries());
                            if (var16.getRules() == null) {
                                break;
                            }

                            var17 = var16.getRules();
                            Iterator var18 = var17.iterator();

                            while(var18.hasNext()) {
                                Rule var19 = (Rule)var18.next();
                                var19.setFile(var10);
                            }

                            this.c.convertNamedJunctions(var17);
                            if (var16.isAlone()) {
                                var4.addAll(var17);
                            } else {
                                var3.addAll(var17);
                            }
                            break;
                        }
                    }
                }
            }

            ResourceLibrary var20 = this.a.buildResourceLibrary(var5.values());
            this.a((ResourceLibrary)var20, (List)var7);
            this.c(var3, var20);
            this.c(var4, var20);
            this.a((List)var3, (ResourceLibrary)var20);
            this.a((List)var4, (ResourceLibrary)var20);
            Rete var21 = this.b.buildRete(var3, var20);
            ArrayList var22 = new ArrayList();
            Collections.sort(var4);
            Iterator var24 = var4.iterator();

            while(var24.hasNext()) {
                Rule var25 = (Rule)var24.next();
                Rete var26 = this.b.buildRete(var25, var20);
                var22.add(var26);
            }

            IdGenerator.clean();
            return new KnowledgeBase(var21, var22, var6);
        }
    }

    private void a(ResourceLibrary var1, List<ResourceLibrary> var2) {
        List var3 = var1.getVariableCategories();
        Iterator var4 = var2.iterator();

        while(var4.hasNext()) {
            ResourceLibrary var5 = (ResourceLibrary)var4.next();
            Iterator var6 = var5.getVariableCategories().iterator();

            while(var6.hasNext()) {
                VariableCategory var7 = (VariableCategory)var6.next();
                this.a(var3, var7);
            }
        }

    }

    private void a(List<VariableCategory> var1, VariableCategory var2) {
        boolean var3 = false;
        Iterator var4 = var1.iterator();

        while(var4.hasNext()) {
            VariableCategory var5 = (VariableCategory)var4.next();
            if (var5.getName().equals(var2.getName())) {
                var3 = true;
                break;
            }
        }

        if (!var3) {
            var1.add(var2);
        }

    }

    private void a(List<Rule> var1, ResourceLibrary var2) {
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            Rule var4 = (Rule)var3.next();
            if (var4 instanceof LoopRule) {
                LoopRule var5 = (LoopRule)var4;
                List var6 = this.a(var5);
                Rete var7 = this.b.buildRete(var6, var2);
                KnowledgeBase var8 = new KnowledgeBase(var7);
                KnowledgePackageWrapper var9 = new KnowledgePackageWrapper(var8.getKnowledgePackage());
                var5.setKnowledgePackageWrapper(var9);
            }
        }

    }

    private List<Rule> a(LoopRule var1) {
        ArrayList var2 = new ArrayList();
        List var3 = var1.getUnits();
        Iterator var4 = var3.iterator();

        while(var4.hasNext()) {
            LoopRuleUnit var5 = (LoopRuleUnit)var4.next();
            Rule var6 = new Rule();
            var6.setFile(var1.getFile());
            var6.setDebug(var1.getDebug());
            var6.setName(var1.getName() + "->" + var5.getName());
            var6.setLhs(var5.getLhs());
            var6.setRhs(var5.getRhs());
            var6.setOther(var5.getOther());
            var2.add(var6);
        }

        var1.setUnits((List)null);
        return var2;
    }

    public KnowledgeBase buildKnowledgeBase(RuleSet var1) {
        ArrayList var2 = new ArrayList();
        HashMap var3 = new HashMap();
        this.a((Map)var3, (List)var1.getLibraries());
        if (var1.getRules() != null) {
            var2.addAll(var1.getRules());
        }

        ResourceLibrary var4 = this.a.buildResourceLibrary(var3.values());
        Rete var5 = this.b.buildRete(var2, var4);
        return new KnowledgeBase(var5, (List)null, (Map)null);
    }

    private void a(Map<String, Library> var1, List<Library> var2) {
        if (var2 != null) {
            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
                Library var4 = (Library)var3.next();
                String var5 = var4.getPath();
                if (!var1.containsKey(var5)) {
                    var1.put(var5, var4);
                }
            }

        }
    }

    private List<Action> b(List<Action> var1, ResourceLibrary var2) {
        ArrayList var3 = new ArrayList();
        if (var1 == null) {
            return var3;
        } else {
            Iterator var4 = var1.iterator();

            while(var4.hasNext()) {
                Action var5 = (Action)var4.next();
                if (!(var5 instanceof TemplateAction)) {
                    var3.add(var5);
                } else {
                    TemplateAction var6 = (TemplateAction)var5;
                    String var7 = var6.getId();
                    ActionTemplateUnit var8 = var2.getActionTemplateUnit(var7);
                    List var9 = var8.getActions();
                    var3.addAll(var9);
                }
            }

            return var3;
        }
    }

    private void c(List<Rule> var1, ResourceLibrary var2) {
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            Rule var4 = (Rule)var3.next();
            if (var4 instanceof LoopRule) {
                this.a((LoopRule)var4, var2);
            } else {
                Lhs var5 = var4.getLhs();
                if (var5 != null) {
                    Criterion var6 = var5.getCriterion();
                    if (var6 != null) {
                        Criterion var7 = this.a(var6, var2);
                        if (var7 != null) {
                            var5.setCriterion(var7);
                        } else if (var6 instanceof Junction) {
                            this.a((Junction)var6, var2);
                        }
                    }
                }

                Rhs var9 = var4.getRhs();
                if (var9 != null) {
                    List var10 = this.b(var9.getActions(), var2);
                    var9.setActions(var10);
                }

                if (var4.getOther() != null) {
                    Other var11 = var4.getOther();
                    List var8 = this.b(var11.getActions(), var2);
                    var11.setActions(var8);
                }
            }
        }

    }

    private void a(LoopRule var1, ResourceLibrary var2) {
        List var3 = var1.getUnits();
        if (var3 != null) {
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
                LoopRuleUnit var5 = (LoopRuleUnit)var4.next();
                Lhs var6 = var5.getLhs();
                if (var6 != null) {
                    Criterion var7 = var6.getCriterion();
                    if (var7 != null) {
                        Criterion var8 = this.a(var7, var2);
                        if (var8 != null) {
                            var6.setCriterion(var8);
                        } else if (var7 instanceof Junction) {
                            this.a((Junction)var7, var2);
                        }
                    }
                }

                Rhs var10 = var5.getRhs();
                if (var10 != null) {
                    List var11 = this.b(var10.getActions(), var2);
                    var10.setActions(var11);
                }

                if (var5.getOther() != null) {
                    Other var12 = var5.getOther();
                    List var9 = this.b(var12.getActions(), var2);
                    var12.setActions(var9);
                }
            }

        }
    }

    private void a(Junction var1, ResourceLibrary var2) {
        List var3 = var1.getCriterions();
        int var4 = 0;

        for(int var5 = var3.size(); var4 < var5; ++var4) {
            Criterion var6 = (Criterion)var3.get(var4);
            Criterion var7 = this.a(var6, var2);
            if (var7 != null) {
                var3.set(var4, var7);
            } else if (var6 instanceof Junction) {
                this.a((Junction)var6, var2);
            }
        }

    }

    private Criterion a(Criterion var1, ResourceLibrary var2) {
        if (var1 instanceof ConditionTemplateCriterion) {
            ConditionTemplateCriterion var3 = (ConditionTemplateCriterion)var1;
            String var4 = var3.getId();
            ConditionTemplateUnit var5 = var2.getConditionTemplateUnit(var4);
            return var5.getCriterion();
        } else {
            return null;
        }
    }

    public void setRulesRebuilder(RulesRebuilder var1) {
        this.c = var1;
    }

    public void setReteBuilder(ReteBuilder var1) {
        this.b = var1;
    }

    public void setDecisionTableRulesBuilder(DecisionTableRulesBuilder var1) {
        this.e = var1;
    }

    public void setScriptDecisionTableRulesBuilder(ScriptDecisionTableRulesBuilder var1) {
        this.f = var1;
    }

    public void setDslRuleSetBuilder(DSLRuleSetBuilder var1) {
        this.g = var1;
    }

    public void setResourceLibraryBuilder(ResourceLibraryBuilder var1) {
        this.a = var1;
    }

    public void setDecisionTreeRulesBuilder(DecisionTreeRulesBuilder var1) {
        this.d = var1;
    }

    public void setCrosstabRulesBuilder(CrosstabRulesBuilder var1) {
        this.h = var1;
    }
}
