package com.bstek.urule.console.servlet.complexscorecard;

import com.bstek.urule.console.repository.ProjectVariable;
import com.bstek.urule.console.repository.model.FileType;
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
import com.bstek.urule.model.scorecard.ComplexScorecardDefinition;
import com.bstek.urule.parse.deserializer.ComplexScorecardDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

public class ComplexScoreTableBuilder {
    private ScoreTableData a;
    private DSLRuleSetBuilder b;
    private List<ProjectVariable> c;
    private ComplexScorecardDeserializer d;
    private List<String> e = new ArrayList();

    public ComplexScoreTableBuilder(ScoreTableData var1, DSLRuleSetBuilder var2, List<ProjectVariable> var3, ComplexScorecardDeserializer var4) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
    }

    public ComplexScorecardDefinition buildTable() {
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
        var1.append("<complex-scorecard scoring-type=\"sum\" assign-target-type=\"none\">");
        List var2 = this.a.getHeaders();

        String var5;
        for(int var3 = 0; var3 < var2.size(); ++var3) {
            TableHeader var4 = (TableHeader)var2.get(var3);
            var5 = var4.getName();
            if (var4.isScore()) {
                var1.append("<col num=\"" + var3 + "\" width=\"120\" type=\"Score\"/>");
            } else {
                var1.append("<col num=\"" + var3 + "\" width=\"120\" type=\"Criteria\" var-category=\"" + var5 + "\"/>");
            }
        }

        List var13 = this.a.getRows();

        int var14;
        for(var14 = 0; var14 < var13.size(); ++var14) {
            var1.append("<row num=\"" + var14 + "\" height=\"40\"/>");
        }

        for(var14 = 0; var14 < var13.size(); ++var14) {
            RowData var15 = (RowData)var13.get(var14);
            List var6 = var15.getCells();

            for(Iterator var7 = var6.iterator(); var7.hasNext(); var1.append("</cell>")) {
                CellData var8 = (CellData)var7.next();
                TableHeader var9 = var8.getHeader();
                String var10 = var8.getContent();
                int var11 = var8.getSpan();
                if (var11 == 0) {
                    var11 = 1;
                }

                var1.append("<cell row=\"" + var8.getRow() + "\" col=\"" + var8.getCol() + "\" rowspan=\"" + var11 + "\"");
                if (var9.isScore()) {
                    var1.append(">");
                } else {
                    Variable var12 = this.a(var9.getName(), var8.getProperty());
                    var1.append(" var-label=\"" + var12.getLabel() + "\" var=\"" + var12.getName() + "\" datatype=\"" + var12.getDataType() + "\">");
                }

                if (StringUtils.isNotBlank(var10)) {
                    if (var9.isScore()) {
                        var10 = StringEscapeUtils.escapeXml(var10);
                        var1.append("<value content=\"" + var10 + "\" type=\"Input\"/>");
                    } else {
                        Criterion var17 = this.b.buildCriterion(var10);
                        var1.append(this.a(var17));
                    }
                }
            }
        }

        Iterator var16 = this.e.iterator();

        while(var16.hasNext()) {
            var5 = (String)var16.next();
            if (var5.endsWith(FileType.VariableLibrary.toString())) {
                var1.append("<import-variable-library path=\"" + var5 + "\"/>");
            } else {
                var1.append("<import-parameter-library path=\"" + var5 + "\"/>");
            }
        }

        var1.append("</complex-scorecard>");
        return var1.toString();
    }

    private Variable a(String var1, String var2) {
        Iterator var3 = this.c.iterator();

        label48:
        while(var3.hasNext()) {
            ProjectVariable var4 = (ProjectVariable)var3.next();
            List var5 = var4.getVariableCategories();
            Iterator var6 = var5.iterator();

            while(true) {
                List var9;
                do {
                    VariableCategory var7;
                    do {
                        if (!var6.hasNext()) {
                            continue label48;
                        }

                        var7 = (VariableCategory)var6.next();
                    } while(!var7.getName().equals(var1));

                    String var8 = "jcr:" + var4.getPath();
                    if (!this.e.contains(var8)) {
                        this.e.add(var8);
                    }

                    var9 = var7.getVariables();
                } while(var9 == null);

                Iterator var10 = var9.iterator();

                while(var10.hasNext()) {
                    Variable var11 = (Variable)var10.next();
                    if (var11.getLabel().equals(var2) || var11.getName().equals(var2)) {
                        return var11;
                    }
                }
            }
        }

        throw new RuleException("变量[" + var1 + "." + var2 + "]在当前项目中未定义!");
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
}
