//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.resource;

import com.bstek.urule.action.Action;
import com.bstek.urule.action.ScoringAction;
import com.bstek.urule.builder.KnowledgeBase;
import com.bstek.urule.builder.ResourceLibraryBuilder;
import com.bstek.urule.builder.RulesRebuilder;
import com.bstek.urule.builder.rete.ReteBuilder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.rete.Rete;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.lhs.*;
import com.bstek.urule.model.table.Condition;
import com.bstek.urule.parse.deserializer.ScorecardDeserializer;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScorecardResourceBuilder implements ResourceBuilder<ScoreRule> {
    private ReteBuilder a;
    private ResourceLibraryBuilder b;
    private ScorecardDeserializer c;
    private RulesRebuilder d;

    public ScorecardResourceBuilder() {
    }

    public ScoreRule build(Element var1, String var2) {
        ScorecardDefinition var3 = this.c.deserialize(var1);
        ScoreRule var4 = new ScoreRule();
        var4.setName(var3.getName());
        var4.setFile(var2);
        var4.setEffectiveDate(var3.getEffectiveDate());
        var4.setExpiresDate(var3.getExpiresDate());
        var4.setEnabled(var3.getEnabled());
        var4.setSalience(var3.getSalience());
        var4.setDebug(var3.getDebug());
        var4.setScoringBean(var3.getScoringBean());
        var4.setScoringType(var3.getScoringType());
        var4.setAssignTargetType(var3.getAssignTargetType());
        var4.setKeyLabel(var3.getKeyLabel());
        var4.setKeyName(var3.getKeyName());
        var4.setDatatype(var3.getDatatype());
        var4.setVariableCategory(var3.getVariableCategory());
        var4.setVariableName(var3.getVariableName());
        var4.setVariableLabel(var3.getVariableLabel());
        var4.setLibraries(var3.getLibraries());
        List var5 = var3.getRows();
        List var6 = var3.getCells();
        List var7 = var3.getCustomCols();
        String var8 = var3.getAttributeColVariableCategory();
        ArrayList var9 = new ArrayList();
        Iterator var10 = var5.iterator();

        while(var10.hasNext()) {
            AttributeRow var11 = (AttributeRow)var10.next();
            List var12 = var11.getConditionRows();
            int var13 = var11.getRowNumber();
            Rule var14 = this.a(var6, var7, var8, var13, var13);
            var9.add(var14);
            var14.setFile(var2);
            var14.setDebug(var3.getDebug());
            Iterator var15 = var12.iterator();

            while(var15.hasNext()) {
                ConditionRow var16 = (ConditionRow)var15.next();
                int var17 = var16.getRowNumber();
                Rule var18 = this.a(var6, var7, var8, var13, var17);
                var18.setFile(var2);
                var18.setDebug(var3.getDebug());
                var9.add(var18);
            }
        }

        this.d.rebuildRules(var3.getLibraries(), var9);
        ResourceLibrary var19 = this.b.buildResourceLibrary(var3.getLibraries());
        Rete var20 = this.a.buildRete(var9, var19);
        KnowledgeBase var21 = new KnowledgeBase(var20);
        KnowledgePackageWrapper var22 = new KnowledgePackageWrapper(var21.getKnowledgePackage());
        var4.setKnowledgePackageWrapper(var22);
        return var4;
    }

    private Rule a(List<CardCell> var1, List<CustomCol> var2, String var3, int var4, int var5) {
        Rule var6 = this.a(var1, var3, var4, var5);
        var6.getRhs().getActions().addAll(this.a(var1, var2, var5));
        return var6;
    }

    private List<Action> a(List<CardCell> var1, List<CustomCol> var2, int var3) {
        ArrayList var4 = new ArrayList();
        Iterator var5 = var2.iterator();

        while(var5.hasNext()) {
            CustomCol var6 = (CustomCol)var5.next();
            ScoringAction var7 = new ScoringAction(var3, var6.getName(), (String)null);
            CardCell var8 = this.a(var1, var3, var6.getColNumber());
            var7.setValue(var8.getValue());
            var4.add(var7);
        }

        return var4;
    }

    private Rule a(List<CardCell> var1, String var2, int var3, int var4) {
        CardCell var5 = this.a(var1, var3, 1);
        CardCell var6 = this.a(var1, var4, 2);
        CardCell var7 = this.a(var1, var4, 3);
        Rule var8 = this.a(var2, var5, var6, var7);
        var8.setName("sc-r" + var4);
        return var8;
    }

    private Rule a(String var1, CardCell var2, CardCell var3, CardCell var4) {
        Rule var5 = new Rule();
        Lhs var6 = new Lhs();
        var5.setLhs(var6);
        Junction var7 = var3.getJoint().getJunction();
        Iterator var8 = var3.getJoint().getConditions().iterator();

        while(var8.hasNext()) {
            Condition var9 = (Condition)var8.next();
            Criteria var10 = new Criteria();
            var10.setOp(var9.getOp());
            Left var11 = new Left();
            VariableLeftPart var12 = new VariableLeftPart();
            var12.setVariableCategory(var1);
            var12.setDatatype(var2.getDatatype());
            var12.setVariableName(var2.getVariableName());
            var12.setVariableLabel(var2.getVariableLabel());
            var12.setKeyLabel(var2.getKeyLabel());
            var12.setKeyName(var2.getKeyName());
            var11.setLeftPart(var12);
            var10.setLeft(var11);
            var11.setType(LeftType.variable);
            var10.setValue(var9.getValue());
            var7.addCriterion(var10);
        }

        if (var7.getCriterions() != null && var7.getCriterions().size() > 0) {
            var6.setCriterion(var7);
        }

        Rhs var13 = new Rhs();
        var5.setRhs(var13);
        ScoringAction var14 = new ScoringAction(var3.getRow(), "scoring_value", var2.getWeight());
        var14.setValue(var4.getValue());
        var13.addAction(var14);
        return var5;
    }

    private CardCell a(List<CardCell> var1, int var2, int var3) {
        Iterator var4 = var1.iterator();

        CardCell var5;
        do {
            if (!var4.hasNext()) {
                throw new RuleException("CardCell [" + var2 + "," + var3 + "] not exist.");
            }

            var5 = (CardCell)var4.next();
        } while(var5.getRow() != var2 || var5.getCol() != var3);

        return var5;
    }

    public ResourceType getType() {
        return ResourceType.Scorecard;
    }

    public boolean support(Element var1) {
        return this.c.support(var1);
    }

    public void setScorecardDeserializer(ScorecardDeserializer var1) {
        this.c = var1;
    }

    public void setResourceLibraryBuilder(ResourceLibraryBuilder var1) {
        this.b = var1;
    }

    public void setReteBuilder(ReteBuilder var1) {
        this.a = var1;
    }

    public void setRulesRebuilder(RulesRebuilder var1) {
        this.d = var1;
    }
}
