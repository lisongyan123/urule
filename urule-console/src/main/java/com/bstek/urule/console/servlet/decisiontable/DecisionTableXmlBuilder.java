package com.bstek.urule.console.servlet.decisiontable;

import com.bstek.urule.console.repository.ProjectVariable;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.servlet.CellContent;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.variable.Variable;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.rule.ArithmeticType;
import com.bstek.urule.model.rule.ComplexArithmetic;
import com.bstek.urule.model.rule.SimpleValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.VariableCategoryValue;
import com.bstek.urule.model.rule.VariableValue;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Junction;
import com.bstek.urule.model.rule.lhs.Or;
import com.bstek.urule.model.table.DecisionTable;
import com.bstek.urule.parse.deserializer.DecisionTableDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

public class DecisionTableXmlBuilder {
    private TableData a;
    private DSLRuleSetBuilder b;
    private List<ProjectVariable> c;
    private DecisionTableDeserializer d;
    private List<String> e = new ArrayList();

    public DecisionTableXmlBuilder(TableData var1, DecisionTableDeserializer var2, List<ProjectVariable> var3, DSLRuleSetBuilder var4) {
        this.a = var1;
        this.d = var2;
        this.c = var3;
        this.b = var4;
    }

    public DecisionTable buildTable() {
        try {
            String var1 = this.a();
            Document var2 = DocumentHelper.parseText(var1);
            return this.d.deserialize(var2.getRootElement());
        } catch (Exception var3) {
            throw new RuleException(var3);
        }
    }

    private String a() throws IOException {
        StringBuilder var1 = new StringBuilder();
        var1.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        var1.append("<decision-table>");
        List var2 = this.a.getHeaders();

        for(int var3 = 0; var3 < var2.size(); ++var3) {
            Header var4 = (Header)var2.get(var3);
            HeaderType var5 = var4.getType();
            String var6 = var4.getName();
            String[] var7;
            Variable var8;
            if (var5.equals(HeaderType.assign)) {
                var7 = this.a(var6);
                var8 = this.a(var7);
                var1.append("<col num=\"" + var3 + "\" width=\"180\" type=\"Assignment\" var-category=\"" + var7[0] + "\" var-label=\"" + var7[1] + "\" var=\"" + var8.getName() + "\" datatype=\"" + var8.getType().name() + "\"/>");
            } else if (var5.equals(HeaderType.condition)) {
                var7 = this.a(var6);
                var8 = this.a(var7);
                var1.append("<col num=\"" + var3 + "\" width=\"180\" type=\"Criteria\" var-category=\"" + var7[0] + "\" var-label=\"" + var7[1] + "\" var=\"" + var8.getName() + "\" datatype=\"" + var8.getType().name() + "\"/>");
            } else if (var5.equals(HeaderType.out)) {
                var1.append("<col num=\"" + var3 + "\" width=\"180\" type=\"ConsolePrint\"/>");
            }
        }

        List var14 = this.a.getRows();

        int var15;
        for(var15 = 0; var15 < var14.size(); ++var15) {
            var1.append("<row num=\"" + var15 + "\" height=\"40\"/>");
        }

        for(var15 = 0; var15 < var14.size(); ++var15) {
            ContentRow var16 = (ContentRow)var14.get(var15);
            List var19 = var16.getContents();

            for(Iterator var20 = var19.iterator(); var20.hasNext(); var1.append("</cell>")) {
                CellContent var21 = (CellContent)var20.next();
                Header var9 = var21.getHeader();
                HeaderType var10 = var9.getType();
                String var11 = var21.getContent();
                int var12 = var21.getSpan();
                if (var12 == 0) {
                    var12 = 1;
                }

                var1.append("<cell row=\"" + var21.getRow() + "\" col=\"" + var21.getCol() + "\" rowspan=\"" + var12 + "\">");
                if (StringUtils.isNotBlank(var11)) {
                    if (var10.equals(HeaderType.condition)) {
                        Criterion var13 = this.b.buildCriterion(var11);
                        var1.append(this.a(var13));
                    } else {
                        var11 = StringEscapeUtils.escapeXml(var11);
                        var1.append("<value content=\"" + var11 + "\" type=\"Input\"/>");
                    }
                }
            }
        }

        Iterator var17 = this.e.iterator();

        while(var17.hasNext()) {
            String var18 = (String)var17.next();
            if (var18.endsWith(FileType.VariableLibrary.toString())) {
                var1.append("<import-variable-library path=\"" + var18 + "\"/>");
            } else {
                var1.append("<import-parameter-library path=\"" + var18 + "\"/>");
            }
        }

        var1.append("</decision-table>");
        return var1.toString();
    }

    private String a(Criterion var1) {
        StringBuilder var2 = new StringBuilder();
        if (var1 instanceof Junction) {
            Junction var3 = (Junction)var1;
            List var4 = var3.getCriterions();
            String var5 = "and";
            if (var3 instanceof Or) {
                var5 = "or";
            }

            var2.append("<joint type=\"" + var5 + "\">");
            if (var4 != null) {
                Iterator var6 = var4.iterator();

                while(var6.hasNext()) {
                    Criterion var7 = (Criterion)var6.next();
                    if (var7 instanceof Criteria) {
                        Criteria var8 = (Criteria)var7;
                        var2.append("<condition op=\"" + var8.getOp().name() + "\">");
                        Value var9 = var8.getValue();
                        String var10 = this.a(var9);
                        if (var10 != null) {
                            var2.append(var10);
                        }

                        var2.append("</condition>");
                    }
                }
            }
        } else {
            var2.append("<joint type=\"and\">");
            Criteria var11 = (Criteria)var1;
            var2.append("<condition op=\"" + var11.getOp().name() + "\">");
            Value var12 = var11.getValue();
            var2.append(this.a(var12));
            var2.append("</condition>");
        }

        var2.append("</joint>");
        return var2.toString();
    }

    private String a(Value var1) {
        if (var1 == null) {
            return null;
        } else {
            StringBuilder var2 = new StringBuilder();
            String var4;
            if (var1 instanceof SimpleValue) {
                SimpleValue var3 = (SimpleValue)var1;
                var4 = StringEscapeUtils.escapeXml(var3.getContent());
                var2.append("<value content=\"" + var4 + "\" type=\"Input\">");
            } else if (var1 instanceof VariableCategoryValue) {
                VariableCategoryValue var6 = (VariableCategoryValue)var1;
                var4 = var6.getVariableCategory();
                String var5 = StringEscapeUtils.escapeXml(var4);
                var2.append("<value content=\"" + var5 + "\" type=\"Input\">");
            } else if (var1 instanceof VariableValue) {
                VariableValue var7 = (VariableValue)var1;
                var4 = var7.getVariableCategory() + "." + var7.getVariableLabel();
                var4 = StringEscapeUtils.escapeXml(var4);
                var2.append("<value content=\"" + var4 + "\" type=\"Input\">");
            } else {
                var2.append("<value content=\"\" type=\"Input\">");
            }

            ComplexArithmetic var8 = var1.getArithmetic();
            if (var8 == null) {
                var2.append("</value>");
                return var2.toString();
            } else {
                ArithmeticType var9 = var8.getType();
                var2.append("<complex-arith type=\"" + var9.name() + "\">");
                var2.append(this.a(var8.getValue()));
                var2.append("</complex-arith>");
                var2.append("</value>");
                return var2.toString();
            }
        }
    }

    private Variable a(String[] var1) {
        String var2 = var1[0];
        String var3 = var1[1];
        Iterator var4 = this.c.iterator();

        label48:
        while(var4.hasNext()) {
            ProjectVariable var5 = (ProjectVariable)var4.next();
            List var6 = var5.getVariableCategories();
            Iterator var7 = var6.iterator();

            while(true) {
                List var10;
                do {
                    VariableCategory var8;
                    do {
                        if (!var7.hasNext()) {
                            continue label48;
                        }

                        var8 = (VariableCategory)var7.next();
                    } while(!var8.getName().equals(var2));

                    String var9 = "jcr:" + var5.getPath();
                    if (!this.e.contains(var9)) {
                        this.e.add(var9);
                    }

                    var10 = var8.getVariables();
                } while(var10 == null);

                Iterator var11 = var10.iterator();

                while(var11.hasNext()) {
                    Variable var12 = (Variable)var11.next();
                    if (var12.getLabel().equals(var3) || var12.getName().equals(var3)) {
                        return var12;
                    }
                }
            }
        }

        throw new RuleException("变量[" + var2 + "." + var3 + "]在当前项目中未定义!");
    }

    private String[] a(String var1) {
        String[] var2 = var1.split("\\.");
        if (var2.length != 2) {
            throw new RuleException("表头[" + var1 + "]不合法！");
        } else {
            return var2;
        }
    }
}
