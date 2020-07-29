//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.scorecard;

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
import com.bstek.urule.model.scorecard.ScorecardDefinition;
import com.bstek.urule.parse.deserializer.ScorecardDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

public class ScoreTableBuilder {
    private ScoreTableData a;
    private DSLRuleSetBuilder b;
    private List<ProjectVariable> c;
    private ScorecardDeserializer d;
    private List<String> e = new ArrayList();

    public ScoreTableBuilder(ScoreTableData var1, DSLRuleSetBuilder var2, List<ProjectVariable> var3, ScorecardDeserializer var4) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
    }

    public ScorecardDefinition buildTable() {
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
        List var2 = this.a.getHeaders();
        TableHeader var3 = this.a(var2, false, false);
        var1.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        var1.append("<scorecard weight-support=\"false\" name=\"从Excel中导入的评分卡\" attr-col-width=\"200\" attr-col-name=\"属性\" attr-col-category=\"" + var3.getName() + "\" condition-col-width=\"220\" condition-col-name=\"条件\" score-col-width=\"180\" score-col-name=\"分值\" scoring-type=\"sum\" assign-target-type=\"none\">");
        List var4 = this.a.getRows();
        int var5 = 2;
        Iterator var6 = var4.iterator();

        while(true) {
            CellData var9;
            do {
                RowData var7;
                List var8;
                if (!var6.hasNext()) {
                    var5 = 2;

                    for(int var15 = 0; var15 < var4.size(); ++var15) {
                        var7 = (RowData)var4.get(var15);
                        var8 = var7.getCells();
                        Iterator var17 = var8.iterator();

                        while(true) {
                            while(var17.hasNext()) {
                                CellData var19 = (CellData)var17.next();
                                TableHeader var21 = var19.getHeader();
                                String var22 = var19.getContent();
                                int var13 = var19.getSpan();
                                if (var13 == 0) {
                                    boolean var23 = true;
                                }

                                if (!var21.isCondition() && !var21.isScore()) {
                                    Variable var24 = this.a(var21.getName(), var22);
                                    var1.append("<card-cell type=\"attribute\" row=\"" + var5 + "\" col=\"" + (var19.getCol() + 1) + "\" var=\"" + var24.getName() + "\" var-label=\"" + var24.getLabel() + "\" datatype=\"" + var24.getDataType() + "\"");
                                    var1.append("/>");
                                } else if (var21.isScore()) {
                                    var1.append("<card-cell type=\"score\" row=\"" + var5 + "\" col=\"" + (var19.getCol() + 1) + "\">");
                                    var1.append("<value content=\"" + var22 + "\" type=\"Input\"/>");
                                    var1.append("</card-cell>");
                                } else {
                                    if (!var21.isCondition()) {
                                        throw new RuleException("无法识别的单元格：" + var22 + "");
                                    }

                                    var1.append("<card-cell type=\"condition\" row=\"" + var5 + "\" col=\"" + (var19.getCol() + 1) + "\">");
                                    Criterion var14 = this.b.buildCriterion(var22);
                                    var1.append(this.a(var14));
                                    var1.append("</card-cell>");
                                }
                            }

                            ++var5;
                            break;
                        }
                    }

                    var6 = this.e.iterator();

                    while(var6.hasNext()) {
                        String var16 = (String)var6.next();
                        if (var16.endsWith(FileType.VariableLibrary.toString())) {
                            var1.append("<import-variable-library path=\"" + var16 + "\"/>");
                        } else {
                            var1.append("<import-parameter-library path=\"" + var16 + "\"/>");
                        }
                    }

                    var1.append("</scorecard>");
                    return var1.toString();
                }

                var7 = (RowData)var6.next();
                var8 = var7.getCells();
                var9 = null;
                Iterator var10 = var8.iterator();

                while(var10.hasNext()) {
                    CellData var11 = (CellData)var10.next();
                    TableHeader var12 = var11.getHeader();
                    if (!var12.isCondition() && !var12.isScore()) {
                        var9 = var11;
                        break;
                    }
                }
            } while(var9 == null);

            var1.append("<attribute-row row-number=\"" + var5 + "\">");
            int var18 = var9.getSpan();

            for(int var20 = 1; var20 < var18; ++var20) {
                ++var5;
                var1.append("<condition-row row-number=\"" + var5 + "\"/>");
            }

            var1.append("</attribute-row>");
            ++var5;
        }
    }

    private TableHeader a(List<TableHeader> var1, boolean var2, boolean var3) {
        Iterator var4 = var1.iterator();

        TableHeader var5;
        do {
            if (!var4.hasNext()) {
                throw new RuleException("列定义存在问题！");
            }

            var5 = (TableHeader)var4.next();
            if (var2 && var5.isScore()) {
                return var5;
            }

            if (var3 && var5.isCondition()) {
                return var5;
            }
        } while(var2 || var3 || var5.isScore() || var5.isCondition());

        return var5;
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
