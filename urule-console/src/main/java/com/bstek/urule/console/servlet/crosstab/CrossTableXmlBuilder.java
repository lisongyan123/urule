package com.bstek.urule.console.servlet.crosstab;

import com.bstek.urule.console.repository.ProjectVariable;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.servlet.CellContent;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.crosstab.CrosstabDefinition;
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
import com.bstek.urule.parse.deserializer.CrosstableDeserializer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

public class CrossTableXmlBuilder {
    private CrossData a;
    private DSLRuleSetBuilder b;
    private CrosstableDeserializer c;
    private List<ProjectVariable> d;
    private List<String> e = new ArrayList();

    public CrossTableXmlBuilder(CrossData var1, CrosstableDeserializer var2, List<ProjectVariable> var3, DSLRuleSetBuilder var4) {
        this.a = var1;
        this.c = var2;
        this.d = var3;
        this.b = var4;
    }

    public CrosstabDefinition doBuild() throws Exception {
        try {
            String var1 = this.a();
            Document var2 = DocumentHelper.parseText(var1);
            return this.c.deserialize(var2.getRootElement());
        } catch (Exception var3) {
            throw new RuleException(var3);
        }
    }

    private String a() throws Exception {
        StringBuilder var1 = new StringBuilder();
        var1.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        CrossHeader var2 = this.a.getHeader();
        var1.append("<crosstab>");
        String var3 = var2.getContent();
        var3 = var3 == null ? "表头" : var3;
        var1.append("<header rowspan=\"" + var2.getRowSpan() + "\" colspan=\"" + var2.getColSpan() + "\"><![CDATA[" + var3 + "]]></header>");

        Iterator var4;
        String var6;
        String[] var7;
        Variable var8;
        String var9;
        String var10;
        for(var4 = this.a.getRows().iterator(); var4.hasNext(); var1.append("/>")) {
            CrossRow var5 = (CrossRow)var4.next();
            var1.append("<row number=\"" + var5.getNumber() + "\" type=\"" + var5.getType() + "\"");
            var6 = var5.getContent();
            if (StringUtils.isNotBlank(var6)) {
                var7 = this.a(var6);
                var8 = this.a(var7);
                var9 = var7[0];
                var10 = "variable";
                if (var9.equals("参数") || var9.equals("parameter")) {
                    var10 = "parameter";
                }

                var1.append(" bundle-data-type=\"" + var10 + "\" var-category=\"" + var9 + "\" var=\"" + var8.getName() + "\" var-label=\"" + var8.getLabel() + "\" datatype=\"" + var8.getType().name() + "\"");
            }
        }

        for(var4 = this.a.getColumns().iterator(); var4.hasNext(); var1.append("/>")) {
            CrossColumn var13 = (CrossColumn)var4.next();
            var1.append("<column number=\"" + var13.getNumber() + "\" type=\"" + var13.getType() + "\"");
            var6 = var13.getContent();
            if (StringUtils.isNotBlank(var6)) {
                var7 = this.a(var6);
                var8 = this.a(var7);
                var9 = var7[0];
                var10 = "variable";
                if (var9.equals("参数") || var9.equals("parameter")) {
                    var10 = "parameter";
                }

                var1.append(" bundle-data-type=\"" + var10 + "\" var-category=\"" + var9 + "\" var=\"" + var8.getName() + "\" var-label=\"" + var8.getLabel() + "\" datatype=\"" + var8.getType().name() + "\"");
            }
        }

        var4 = this.a.getCells().iterator();

        while(var4.hasNext()) {
            CellContent var14 = (CellContent)var4.next();
            var6 = var14.getType();
            String var16 = "condition-cell";
            if (var6.equals("value")) {
                var16 = "value-cell";
            }

            var1.append("<");
            var1.append(var16);
            int var17 = 1;
            int var18 = 1;
            boolean var19 = false;
            if (var14.getRow() <= this.a.getHeader().getRowSpan()) {
                var18 = var14.getSpan();
                var19 = true;
            }

            if (var14.getCol() <= this.a.getHeader().getColSpan()) {
                var17 = var14.getSpan();
                var19 = true;
            }

            var1.append(" row=\"" + var14.getRow() + "\" col=\"" + var14.getCol() + "\"");
            if (var19) {
                var1.append(" rowspan=\"" + var17 + "\" colspan=\"" + var18 + "\"");
            }

            var1.append(">");
            String var11 = var14.getContent();
            if (StringUtils.isNotBlank(var11)) {
                if (var19) {
                    Criterion var12 = this.b.buildCriterion(var11);
                    var1.append(this.a(var12));
                } else {
                    var11 = StringEscapeUtils.escapeXml(var11);
                    var1.append("<value content=\"" + var11 + "\" type=\"Input\"/>");
                }
            }

            var1.append("");
            var1.append("</");
            var1.append(var16);
            var1.append(">");
        }

        var4 = this.e.iterator();

        while(var4.hasNext()) {
            String var15 = (String)var4.next();
            if (var15.endsWith(FileType.VariableLibrary.toString())) {
                var1.append("<import-variable-library path=\"" + var15 + "\"/>");
            } else {
                var1.append("<import-parameter-library path=\"" + var15 + "\"/>");
            }
        }

        var1.append("");
        var1.append("");
        var1.append("");
        var1.append("");
        var1.append("");
        var1.append("</crosstab>");
        return var1.toString();
    }

    private String a(Criterion var1) {
        StringBuilder var2 = new StringBuilder();
        String var5;
        if (var1 instanceof Junction) {
            Junction var3 = (Junction)var1;
            List var4 = var3.getCriterions();
            var5 = "and";
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
                        var2.append(this.a(var9));
                        var2.append("</condition>");
                    }
                }
            }
        } else {
            var2.append("<joint type=\"and\">");
            Criteria var10 = (Criteria)var1;
            var2.append("<condition op=\"" + var10.getOp().name() + "\">");
            Value var11 = var10.getValue();
            var5 = this.a(var11);
            if (var5 != null) {
                var2.append(var5);
            }

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
        Iterator var4 = this.d.iterator();

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
