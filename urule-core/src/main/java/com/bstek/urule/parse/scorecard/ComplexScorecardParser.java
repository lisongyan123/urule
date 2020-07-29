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
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.scorecard.AssignTargetType;
import com.bstek.urule.model.scorecard.ComplexColumn;
import com.bstek.urule.model.scorecard.ComplexScorecardDefinition;
import com.bstek.urule.model.scorecard.ScoringType;
import com.bstek.urule.model.table.Cell;
import com.bstek.urule.model.table.Condition;
import com.bstek.urule.model.table.Row;
import com.bstek.urule.parse.Parser;
import com.bstek.urule.parse.table.CellParser;
import com.bstek.urule.parse.table.RowParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class ComplexScorecardParser implements Parser<ComplexScorecardDefinition> {
    private RowParser a;
    private ComplexColumnParser b;
    private CellParser c;
    private RulesRebuilder d;

    public ComplexScorecardParser() {
    }

    public ComplexScorecardDefinition parse(Element var1) {
        ComplexScorecardDefinition var2 = new ComplexScorecardDefinition();
        var2.setScoringType(ScoringType.valueOf(var1.attributeValue("scoring-type")));
        var2.setScoringBean(var1.attributeValue("custom-scoring-bean"));
        var2.setAssignTargetType(AssignTargetType.valueOf(var1.attributeValue("assign-target-type")));
        var2.setVariableCategory(var1.attributeValue("var-category"));
        var2.setVariableName(var1.attributeValue("var"));
        var2.setVariableLabel(var1.attributeValue("var-label"));
        var2.setKeyLabel(var1.attributeValue("key-label"));
        var2.setKeyName(var1.attributeValue("key-name"));
        String var3 = var1.attributeValue("datatype");
        if (StringUtils.isNotBlank(var3)) {
            var2.setDatatype(Datatype.valueOf(var3));
        }

        String var4 = var1.attributeValue("salience");
        if (StringUtils.isNotEmpty(var4)) {
            var2.setSalience(Integer.valueOf(var4));
        }

        String var5 = var1.attributeValue("effective-date");
        SimpleDateFormat var6 = new SimpleDateFormat(Configure.getDateFormat());
        if (StringUtils.isNotEmpty(var5)) {
            try {
                var2.setEffectiveDate(var6.parse(var5));
            } catch (ParseException var16) {
                throw new RuleException(var16);
            }
        }

        String var7 = var1.attributeValue("expires-date");
        if (StringUtils.isNotEmpty(var7)) {
            try {
                var2.setExpiresDate(var6.parse(var7));
            } catch (ParseException var15) {
                throw new RuleException(var15);
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

        Iterator var10 = var1.elements().iterator();

        while(var10.hasNext()) {
            Object var11 = var10.next();
            if (var11 != null && var11 instanceof Element) {
                Element var12 = (Element)var11;
                String var13 = var12.getName();
                if (this.a.support(var13)) {
                    var2.addRow(this.a.parse(var12));
                } else if (this.b.support(var13)) {
                    var2.addColumn(this.b.parse(var12));
                } else if (this.c.support(var13)) {
                    var2.addCell(this.c.parse(var12));
                }

                if (var13.equals("import-variable-library")) {
                    var2.addLibrary(new Library(var12.attributeValue("path"), (String)null, LibraryType.Variable));
                } else if (var13.equals("quick-test-data")) {
                    String var14 = var12.getTextTrim();
                    var2.setQuickTestData(var14);
                } else if (var13.equals("import-constant-library")) {
                    var2.addLibrary(new Library(var12.attributeValue("path"), (String)null, LibraryType.Constant));
                } else if (var13.equals("import-action-library")) {
                    var2.addLibrary(new Library(var12.attributeValue("path"), (String)null, LibraryType.Action));
                } else if (var13.equals("import-parameter-library")) {
                    var2.addLibrary(new Library(var12.attributeValue("path"), (String)null, LibraryType.Parameter));
                } else if (var13.equals("remark")) {
                    var2.setRemark(var12.getText());
                }
            }
        }

        this.a(var2);
        return var2;
    }

    private void a(ComplexScorecardDefinition var1) {
        List var2 = var1.getLibraries();
        ResourceLibrary var3 = this.d.getResourceLibraryBuilder().buildResourceLibrary(var2);
        Iterator var4 = var1.getCellMap().values().iterator();

        while(true) {
            while(var4.hasNext()) {
                Cell var5 = (Cell)var4.next();
                if (var5.getAction() != null) {
                    this.d.rebuildAction(var5.getAction(), var3, false);
                } else if (var5.getValue() != null) {
                    this.d.rebuildValue(var5.getValue(), var3, false);
                } else if (var5.getJoint() != null && var5.getJoint() != null && var5.getJoint().getJunction() != null) {
                    List var6 = var5.getJoint().getConditions();
                    if (var6 != null) {
                        Iterator var7 = var6.iterator();

                        while(var7.hasNext()) {
                            Condition var8 = (Condition)var7.next();
                            Value var9 = var8.getValue();
                            if (var9 != null) {
                                this.d.rebuildValue(var9, var3, false);
                            }
                        }
                    }
                }
            }

            Collections.sort(var1.getColumns(), new Comparator<ComplexColumn>() {
                public int compare(ComplexColumn var1, ComplexColumn var2) {
                    return var1.getNum() - var2.getNum();
                }
            });
            Collections.sort(var1.getRows(), new Comparator<Row>() {
                public int compare(Row var1, Row var2) {
                    return var1.getNum() - var2.getNum();
                }
            });
            return;
        }
    }

    public boolean support(String var1) {
        return var1.equals("complex-scorecard");
    }

    public void setColumnParser(ComplexColumnParser var1) {
        this.b = var1;
    }

    public void setRowParser(RowParser var1) {
        this.a = var1;
    }

    public void setCellParser(CellParser var1) {
        this.c = var1;
    }

    public void setRulesRebuilder(RulesRebuilder var1) {
        this.d = var1;
    }
}
