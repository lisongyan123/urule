//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.table;

import com.bstek.urule.Configure;
import com.bstek.urule.builder.RulesRebuilder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.library.variable.Variable;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.table.Cell;
import com.bstek.urule.model.table.Column;
import com.bstek.urule.model.table.Condition;
import com.bstek.urule.model.table.DecisionTable;
import com.bstek.urule.model.table.Row;
import com.bstek.urule.parse.Parser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class DecisionTableParser implements Parser<DecisionTable> {
    private RowParser a;
    private ColumnParser b;
    private CellParser c;
    private RulesRebuilder d;

    public DecisionTableParser() {
    }

    public DecisionTable parse(Element var1) {
        DecisionTable var2 = new DecisionTable();
        String var3 = var1.attributeValue("salience");
        if (StringUtils.isNotEmpty(var3)) {
            var2.setSalience(Integer.valueOf(var3));
        }

        String var4 = var1.attributeValue("effective-date");
        SimpleDateFormat var5 = new SimpleDateFormat(Configure.getDateFormat());
        if (StringUtils.isNotEmpty(var4)) {
            try {
                var2.setEffectiveDate(var5.parse(var4));
            } catch (ParseException var15) {
                throw new RuleException(var15);
            }
        }

        String var6 = var1.attributeValue("expires-date");
        if (StringUtils.isNotEmpty(var6)) {
            try {
                var2.setExpiresDate(var5.parse(var6));
            } catch (ParseException var14) {
                throw new RuleException(var14);
            }
        }

        String var7 = var1.attributeValue("enabled");
        if (StringUtils.isNotEmpty(var7)) {
            var2.setEnabled(Boolean.valueOf(var7));
        }

        String var8 = var1.attributeValue("debug");
        if (StringUtils.isNotEmpty(var8)) {
            var2.setDebug(Boolean.valueOf(var8));
        }

        Iterator var9 = var1.elements().iterator();

        while(var9.hasNext()) {
            Object var10 = var9.next();
            if (var10 != null && var10 instanceof Element) {
                Element var11 = (Element)var10;
                String var12 = var11.getName();
                if (this.a.support(var12)) {
                    var2.addRow(this.a.parse(var11));
                } else if (var12.equals("quick-test-data")) {
                    String var13 = var11.getTextTrim();
                    var2.setQuickTestData(var13);
                } else if (this.b.support(var12)) {
                    var2.addColumn(this.b.parse(var11));
                } else if (this.c.support(var12)) {
                    var2.addCell(this.c.parse(var11));
                }

                if (var12.equals("import-variable-library")) {
                    var2.addLibrary(new Library(var11.attributeValue("path"), (String)null, LibraryType.Variable));
                } else if (var12.equals("import-constant-library")) {
                    var2.addLibrary(new Library(var11.attributeValue("path"), (String)null, LibraryType.Constant));
                } else if (var12.equals("import-action-library")) {
                    var2.addLibrary(new Library(var11.attributeValue("path"), (String)null, LibraryType.Action));
                } else if (var12.equals("import-parameter-library")) {
                    var2.addLibrary(new Library(var11.attributeValue("path"), (String)null, LibraryType.Parameter));
                } else if (var12.equals("remark")) {
                    var2.setRemark(var11.getText());
                }
            }
        }

        this.a(var2);
        return var2;
    }

    private void a(DecisionTable var1) {
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

            var4 = var1.getColumns().iterator();

            while(var4.hasNext()) {
                Column var10 = (Column)var4.next();
                String var11 = var10.getVariableCategory();
                String var12 = var10.getVariableName();
                if (!StringUtils.isBlank(var11) && !StringUtils.isBlank(var12) && StringUtils.isBlank(var10.getKeyLabel())) {
                    Variable var13 = this.d.getVariableByName(var3.getVariableCategories(), var11, var12);
                    var10.setDatatype(var13.getType());
                    var10.setVariableLabel(var13.getLabel());
                }
            }

            Collections.sort(var1.getColumns(), new Comparator<Column>() {
                public int compare(Column var1, Column var2) {
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
        return var1.equals("decision-table");
    }

    public void setColumnParser(ColumnParser var1) {
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
