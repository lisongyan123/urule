//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.scorecard;

import com.bstek.urule.Configure;
import com.bstek.urule.builder.RulesRebuilder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.library.variable.Variable;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.scorecard.AssignTargetType;
import com.bstek.urule.model.scorecard.CardCell;
import com.bstek.urule.model.scorecard.ScorecardDefinition;
import com.bstek.urule.model.scorecard.ScoringType;
import com.bstek.urule.model.table.Condition;
import com.bstek.urule.model.table.Joint;
import com.bstek.urule.parse.Parser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class ScorecardParser implements Parser<ScorecardDefinition> {
    private CardCellParser a;
    private AttributeRowParser b = new AttributeRowParser();
    private CustomColParser c = new CustomColParser();
    private RulesRebuilder d;

    public ScorecardParser() {
    }

    public ScorecardDefinition parse(Element var1) {
        ScorecardDefinition var2 = new ScorecardDefinition();
        var2.setName(var1.attributeValue("name"));
        var2.setScoringType(ScoringType.valueOf(var1.attributeValue("scoring-type")));
        var2.setAssignTargetType(AssignTargetType.valueOf(var1.attributeValue("assign-target-type")));
        var2.setVariableCategory(var1.attributeValue("var-category"));
        var2.setVariableName(var1.attributeValue("var"));
        var2.setVariableLabel(var1.attributeValue("var-label"));
        String var3 = var1.attributeValue("datatype");
        if (StringUtils.isNotBlank(var3)) {
            var2.setDatatype(Datatype.valueOf(var3));
        }

        var2.setKeyLabel(var1.attributeValue("key-label"));
        var2.setKeyName(var1.attributeValue("key-name"));
        var2.setScoringBean(var1.attributeValue("custom-scoring-bean"));
        String var4 = var1.attributeValue("salience");
        if (StringUtils.isNotEmpty(var4)) {
            var2.setSalience(Integer.valueOf(var4));
        }

        String var5 = var1.attributeValue("effective-date");
        SimpleDateFormat var6 = new SimpleDateFormat(Configure.getDateFormat());
        if (StringUtils.isNotEmpty(var5)) {
            try {
                var2.setEffectiveDate(var6.parse(var5));
            } catch (ParseException var20) {
                throw new RuleException(var20);
            }
        }

        String var7 = var1.attributeValue("expires-date");
        if (StringUtils.isNotEmpty(var7)) {
            try {
                var2.setExpiresDate(var6.parse(var7));
            } catch (ParseException var19) {
                throw new RuleException(var19);
            }
        }

        String var8 = var1.attributeValue("enabled");
        if (StringUtils.isNotEmpty(var8)) {
            var2.setEnabled(Boolean.valueOf(var8));
        }

        String var9 = var1.attributeValue("debug");
        if (StringUtils.isNotEmpty(var9)) {
            var2.setDebug(Boolean.valueOf(var9));
        }

        var2.setAttributeColWidth(var1.attributeValue("attr-col-width"));
        var2.setAttributeColName(var1.attributeValue("attr-col-name"));
        var2.setAttributeColVariableCategory(var1.attributeValue("attr-col-category"));
        var2.setConditionColName(var1.attributeValue("condition-col-name"));
        var2.setConditionColWidth(var1.attributeValue("condition-col-width"));
        var2.setScoreColName(var1.attributeValue("score-col-name"));
        var2.setScoreColWidth(var1.attributeValue("score-col-width"));
        String var10 = var1.attributeValue("weight-support");
        if (StringUtils.isNotBlank(var10)) {
            var2.setWeightSupport(Boolean.valueOf(var10));
        }

        ArrayList var11 = new ArrayList();
        ArrayList var12 = new ArrayList();
        ArrayList var13 = new ArrayList();
        var2.setCells(var11);
        var2.setRows(var12);
        var2.setCustomCols(var13);
        Iterator var14 = var1.elements().iterator();

        while(var14.hasNext()) {
            Object var15 = var14.next();
            if (var15 != null && var15 instanceof Element) {
                Element var16 = (Element)var15;
                String var17 = var16.getName();
                if (this.a.support(var17)) {
                    var11.add(this.a.parse(var16));
                } else if (this.b.support(var17)) {
                    var12.add(this.b.parse(var16));
                } else if (this.c.support(var17)) {
                    var13.add(this.c.parse(var16));
                } else if (var17.equals("quick-test-data")) {
                    String var18 = var16.getTextTrim();
                    var2.setQuickTestData(var18);
                } else if (var17.equals("import-variable-library")) {
                    var2.addLibrary(new Library(var16.attributeValue("path"), (String)null, LibraryType.Variable));
                } else if (var17.equals("import-constant-library")) {
                    var2.addLibrary(new Library(var16.attributeValue("path"), (String)null, LibraryType.Constant));
                } else if (var17.equals("import-action-library")) {
                    var2.addLibrary(new Library(var16.attributeValue("path"), (String)null, LibraryType.Action));
                } else if (var17.equals("import-parameter-library")) {
                    var2.addLibrary(new Library(var16.attributeValue("path"), (String)null, LibraryType.Parameter));
                } else if (var17.equals("remark")) {
                    var2.setRemark(var16.getText());
                }
            }
        }

        this.a(var2);
        return var2;
    }

    private void a(ScorecardDefinition var1) {
        ResourceLibrary var2 = this.d.getResourceLibraryBuilder().buildResourceLibrary(var1.getLibraries());
        String var3 = var1.getVariableCategory();
        String var4 = var1.getVariableName();
        if (StringUtils.isNotBlank(var3) && StringUtils.isNotBlank(var4) && !var3.equals("参数")) {
            Variable var5 = this.d.getVariableByName(var2.getVariableCategories(), var3, var4);
            var1.setVariableLabel(var5.getLabel());
            var1.setDatatype(var5.getType());
        }

        String var13 = var1.getAttributeColVariableCategory();
        List var6 = var1.getCells();
        if (var6 != null) {
            Iterator var7 = var6.iterator();

            while(var7.hasNext()) {
                CardCell var8 = (CardCell)var7.next();
                Joint var9 = var8.getJoint();
                if (var9 != null && var9.getConditions() != null) {
                    Iterator var10 = var9.getConditions().iterator();

                    while(var10.hasNext()) {
                        Condition var11 = (Condition)var10.next();
                        if (var11 != null) {
                            Value var12 = var11.getValue();
                            if (var12 != null) {
                                this.d.rebuildValue(var12, var2, false);
                            }
                        }
                    }
                }

                Value var14 = var8.getValue();
                if (var14 != null) {
                    this.d.rebuildValue(var14, var2, false);
                }

                String var15 = var8.getVariableName();
                if (StringUtils.isNotBlank(var15) && !var13.equals("参数")) {
                    Variable var16 = this.d.getVariableByName(var2.getVariableCategories(), var13, var15);
                    var8.setDatatype(var16.getType());
                    var8.setVariableLabel(var16.getLabel());
                }
            }

        }
    }

    public void setCardCellParser(CardCellParser var1) {
        this.a = var1;
    }

    public void setRulesRebuilder(RulesRebuilder var1) {
        this.d = var1;
    }

    public boolean support(String var1) {
        return var1.equals("scorecard");
    }
}
