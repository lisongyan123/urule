//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.decisiontree;

import com.bstek.urule.Configure;
import com.bstek.urule.action.Action;
import com.bstek.urule.builder.RulesRebuilder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.decisiontree.ActionTreeNode;
import com.bstek.urule.model.decisiontree.ConditionTreeNode;
import com.bstek.urule.model.decisiontree.DecisionTree;
import com.bstek.urule.model.decisiontree.TreeNode;
import com.bstek.urule.model.decisiontree.VariableTreeNode;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.library.variable.Variable;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.lhs.Left;
import com.bstek.urule.model.rule.lhs.LeftPart;
import com.bstek.urule.model.rule.lhs.VariableLeftPart;
import com.bstek.urule.parse.Parser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class DecisionTreeParser implements Parser<DecisionTree> {
    private VariableTreeNodeParser a;
    private RulesRebuilder b;

    public DecisionTreeParser() {
    }

    public DecisionTree parse(Element var1) {
        DecisionTree var2 = new DecisionTree();
        String var3 = var1.attributeValue("salience");
        if (StringUtils.isNotEmpty(var3)) {
            var2.setSalience(Integer.valueOf(var3));
        }

        String var4 = var1.attributeValue("effective-date");
        SimpleDateFormat var5 = new SimpleDateFormat(Configure.getDateFormat());
        if (StringUtils.isNotEmpty(var4)) {
            try {
                var2.setEffectiveDate(var5.parse(var4));
            } catch (ParseException var16) {
                throw new RuleException(var16);
            }
        }

        String var6 = var1.attributeValue("expires-date");
        if (StringUtils.isNotEmpty(var6)) {
            try {
                var2.setExpiresDate(var5.parse(var6));
            } catch (ParseException var15) {
                throw new RuleException(var15);
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

        ArrayList var9 = new ArrayList();
        Iterator var10 = var1.elements().iterator();

        while(var10.hasNext()) {
            Object var11 = var10.next();
            if (var11 != null && var11 instanceof Element) {
                Element var12 = (Element)var11;
                String var13 = var12.getName();
                if (this.a.support(var13)) {
                    var2.setVariableTreeNode(this.a.parse(var12));
                }

                if (var13.equals("import-variable-library")) {
                    var9.add(new Library(var12.attributeValue("path"), (String)null, LibraryType.Variable));
                } else if (var13.equals("quick-test-data")) {
                    String var14 = var12.getTextTrim();
                    var2.setQuickTestData(var14);
                } else if (var13.equals("import-constant-library")) {
                    var9.add(new Library(var12.attributeValue("path"), (String)null, LibraryType.Constant));
                } else if (var13.equals("import-action-library")) {
                    var9.add(new Library(var12.attributeValue("path"), (String)null, LibraryType.Action));
                } else if (var13.equals("import-parameter-library")) {
                    var9.add(new Library(var12.attributeValue("path"), (String)null, LibraryType.Parameter));
                } else if (var13.equals("remark")) {
                    var2.setRemark(var12.getText());
                }
            }
        }

        var2.setLibraries(var9);
        ResourceLibrary var17 = this.b.getResourceLibraryBuilder().buildResourceLibrary(var9);
        this.a(var17, var2.getVariableTreeNode());
        return var2;
    }

    private void a(ResourceLibrary var1, TreeNode var2) {
        if (var2 != null) {
            List var14;
            Iterator var16;
            if (var2 instanceof VariableTreeNode) {
                VariableTreeNode var3 = (VariableTreeNode)var2;
                Left var4 = var3.getLeft();
                if (var4 != null) {
                    LeftPart var5 = var4.getLeftPart();
                    if (var5 != null && var5 instanceof VariableLeftPart) {
                        VariableLeftPart var6 = (VariableLeftPart)var5;
                        String var7 = var6.getVariableCategory();
                        String var8 = var6.getVariableName();
                        if (StringUtils.isNotBlank(var7) && StringUtils.isNotBlank(var8) && StringUtils.isBlank(var6.getKeyLabel())) {
                            Variable var9 = this.b.getVariableByName(var1.getVariableCategories(), var7, var8);
                            var6.setDatatype(var9.getType());
                            var6.setVariableLabel(var9.getLabel());
                        }
                    }
                }

                var14 = var3.getConditionTreeNodes();
                if (var14 != null) {
                    var16 = var14.iterator();

                    while(var16.hasNext()) {
                        ConditionTreeNode var18 = (ConditionTreeNode)var16.next();
                        this.a(var1, var18);
                    }
                }
            } else if (var2 instanceof ConditionTreeNode) {
                ConditionTreeNode var10 = (ConditionTreeNode)var2;
                Value var12 = var10.getValue();
                if (var12 != null) {
                    this.b.rebuildValue(var12, var1, false);
                }

                var14 = var10.getActionTreeNodes();
                if (var14 != null) {
                    var16 = var14.iterator();

                    while(var16.hasNext()) {
                        ActionTreeNode var20 = (ActionTreeNode)var16.next();
                        this.a(var1, var20);
                    }
                }

                List var17 = var10.getConditionTreeNodes();
                if (var17 != null) {
                    Iterator var21 = var17.iterator();

                    while(var21.hasNext()) {
                        ConditionTreeNode var23 = (ConditionTreeNode)var21.next();
                        this.a(var1, var23);
                    }
                }

                List var22 = var10.getVariableTreeNodes();
                if (var22 != null) {
                    Iterator var24 = var22.iterator();

                    while(var24.hasNext()) {
                        VariableTreeNode var25 = (VariableTreeNode)var24.next();
                        this.a(var1, var25);
                    }
                }
            } else if (var2 instanceof ActionTreeNode) {
                ActionTreeNode var11 = (ActionTreeNode)var2;
                List var13 = var11.getActions();
                if (var13 != null) {
                    Iterator var15 = var13.iterator();

                    while(var15.hasNext()) {
                        Action var19 = (Action)var15.next();
                        if (var19 != null) {
                            this.b.rebuildAction(var19, var1, false);
                        }
                    }
                }
            }

        }
    }

    public void setVariableTreeNodeParser(VariableTreeNodeParser var1) {
        this.a = var1;
    }

    public boolean support(String var1) {
        return var1.equals("decision-tree");
    }

    public void setRulesRebuilder(RulesRebuilder var1) {
        this.b = var1;
    }
}
