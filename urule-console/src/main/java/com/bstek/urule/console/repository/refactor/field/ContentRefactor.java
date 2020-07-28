package com.bstek.urule.console.repository.refactor.field;

import com.bstek.urule.console.repository.refactor.BeanItem;
import com.bstek.urule.console.repository.refactor.BeanMethodItem;
import com.bstek.urule.console.repository.refactor.ConstCategoryItem;
import com.bstek.urule.console.repository.refactor.ConstItem;
import com.bstek.urule.console.repository.refactor.Item;
import com.bstek.urule.console.repository.refactor.Refactor;
import com.bstek.urule.console.repository.refactor.VariableCategoryItem;
import com.bstek.urule.console.repository.refactor.VariableItem;

public abstract class ContentRefactor implements Refactor {
    public ContentRefactor() {
    }

    public abstract String doRefactor(String var1, String var2, Item var3);

    protected String doXmlContentRefactor(String var1, String var2, Item var3) {
        String var4 = "jcr:" + this.perfectPath(var1);
        boolean var5 = var2.contains(var4);
        if (!var5) {
            return null;
        } else {
            String var7;
            String var8;
            if (var3 instanceof VariableItem) {
                VariableItem var15 = (VariableItem)var3;
                var7 = "var-category=\"" + var15.getCategory() + "\" var=\"" + var15.getOldName() + "\" var-label=\"" + var15.getOldLabel() + "\" datatype=\"" + var15.getOldDatatype() + "\"";
                var8 = "var-category=\"" + var15.getCategory() + "\" var=\"" + var15.getNewName() + "\" var-label=\"" + var15.getNewLabel() + "\" datatype=\"" + var15.getNewDatatype() + "\"";
                var2 = var2.replaceAll(var7, var8);
                var7 = "var-category=\"" + var15.getCategory() + "\" var=\"" + var15.getOldName() + "\" var-label=\"" + var15.getOldLabel() + "\"";
                var8 = "var-category=\"" + var15.getCategory() + "\" var=\"" + var15.getNewName() + "\" var-label=\"" + var15.getNewLabel() + "\"";
                var2 = var2.replaceAll(var7, var8);
                if (this instanceof ComplexScorecardContentRefactor) {
                    var7 = "var-label=\"" + var15.getOldLabel() + "\" var=\"" + var15.getOldName() + "\"";
                    var8 = "var-label=\"" + var15.getNewLabel() + "\" var=\"" + var15.getNewName() + "\"";
                    var2 = var2.replaceAll(var7, var8);
                }

                var7 = "var-category=\"" + var15.getCategory() + "\" var-label=\"" + var15.getOldLabel() + "\" var=\"" + var15.getOldName() + "\" datatype=\"" + var15.getOldDatatype() + "\"";
                var8 = "var-category=\"" + var15.getCategory() + "\" var-label=\"" + var15.getNewLabel() + "\" var=\"" + var15.getNewName() + "\" datatype=\"" + var15.getNewDatatype() + "\"";
                var2 = var2.replaceAll(var7, var8);
                var7 = "var-category=\"" + var15.getCategory() + "\" var-label=\"" + var15.getOldLabel() + "\" var=\"" + var15.getOldName() + "\"";
                var8 = "var-category=\"" + var15.getCategory() + "\" var-label=\"" + var15.getNewLabel() + "\" var=\"" + var15.getNewName() + "\"";
                var2 = var2.replaceAll(var7, var8);
                return var2;
            } else if (var3 instanceof VariableCategoryItem) {
                VariableCategoryItem var14 = (VariableCategoryItem)var3;
                var7 = "var-category=\"" + var14.getOldCategory() + "\"";
                var8 = "var-category=\"" + var14.getNewCategory() + "\"";
                var2 = var2.replaceAll(var7, var8);
                if (this instanceof ScorecardContentRefactor) {
                    var7 = "attr-col-category=\"" + var14.getOldCategory() + "\"";
                    var8 = "attr-col-category=\"" + var14.getNewCategory() + "\"";
                    var2 = var2.replaceAll(var7, var8);
                }

                return var2;
            } else if (var3 instanceof ConstItem) {
                ConstItem var13 = (ConstItem)var3;
                var7 = var13.getOldName();
                var7 = var7.replaceAll("\\$", "\\\\\\$");
                var7 = var7.replaceAll("\\{", "\\\\\\{");
                var7 = var7.replaceAll("\\}", "\\\\\\}");
                var8 = var13.getNewName();
                var8 = var8.replaceAll("\\$", "\\\\\\$");
                var8 = var8.replaceAll("\\{", "\\\\\\{");
                var8 = var8.replaceAll("\\}", "\\\\\\}");
                String var9 = "const=\"" + var7 + "\" const-label=\"" + var13.getOldLabel() + "\" type=\"Constant\"";
                String var10 = "const=\"" + var8 + "\" const-label=\"" + var13.getNewLabel() + "\" type=\"Constant\"";
                var2 = var2.replaceAll(var9, var10);
                if (var13.getOldDatatype() != null && var13.getNewDatatype() != null) {
                    var9 = "const=\"" + var7 + "\" const-label=\"" + var13.getOldLabel() + "\" data-type=\"" + var13.getOldDatatype() + "\" type=\"Constant\"";
                    var10 = "const=\"" + var8 + "\" const-label=\"" + var13.getNewLabel() + "\" data-type=\"" + var13.getNewDatatype() + "\" type=\"Constant\"";
                    var2 = var2.replaceAll(var9, var10);
                }

                return var2;
            } else if (var3 instanceof ConstCategoryItem) {
                ConstCategoryItem var12 = (ConstCategoryItem)var3;
                var7 = "const-category=\"" + var12.getOldCategory() + "\"";
                var8 = "const-category=\"" + var12.getNewCategory() + "\"";
                return var2.replaceAll(var7, var8);
            } else if (var3 instanceof BeanItem) {
                BeanItem var11 = (BeanItem)var3;
                var7 = "bean=\"" + var11.getOldBeanId() + "\" bean-label=\"" + var11.getOldBeanLabel() + "\"";
                var8 = "bean=\"" + var11.getNewBeanId() + "\" bean-label=\"" + var11.getNewBeanLabel() + "\"";
                var2 = var2.replaceAll(var7, var8);
                var7 = "bean-name=\"" + var11.getOldBeanId() + "\" bean-label=\"" + var11.getOldBeanLabel() + "\"";
                var8 = "bean-name=\"" + var11.getNewBeanId() + "\" bean-label=\"" + var11.getNewBeanLabel() + "\"";
                return var2.replaceAll(var7, var8);
            } else if (var3 instanceof BeanMethodItem) {
                BeanMethodItem var6 = (BeanMethodItem)var3;
                var7 = "bean=\"" + var6.getBeanId() + "\" bean-label=\"" + var6.getBeanLabel() + "\" method-label=\"" + var6.getOldMethodLabel() + "\" method-name=\"" + var6.getOldMethodName() + "\"";
                var8 = "bean=\"" + var6.getBeanId() + "\" bean-label=\"" + var6.getBeanLabel() + "\" method-label=\"" + var6.getNewMethodLabel() + "\" method-name=\"" + var6.getNewMethodName() + "\"";
                var2 = var2.replaceAll(var7, var8);
                var7 = "bean-name=\"" + var6.getBeanId() + "\" bean-label=\"" + var6.getBeanLabel() + "\" method-name=\"" + var6.getOldMethodName() + "\" method-label=\"" + var6.getOldMethodLabel() + "\"";
                var8 = "bean-name=\"" + var6.getBeanId() + "\" bean-label=\"" + var6.getBeanLabel() + "\" method-name=\"" + var6.getNewMethodName() + "\" method-label=\"" + var6.getNewMethodLabel() + "\"";
                return var2.replaceAll(var7, var8);
            } else {
                return null;
            }
        }
    }

    public static void main(String[] var0) throws Exception {
        String var1 = "${ututl.test.aaa}";
        System.out.println(var1.replaceAll("\\$", "\\\\\\$"));
    }

    public String doScriptContentRefactor(String var1, String var2, Item var3) {
        String var4 = "jcr:" + this.perfectPath(var1);
        boolean var5 = var2.contains(var4);
        if (!var5) {
            return null;
        } else {
            String var7;
            String var8;
            if (var3 instanceof VariableItem) {
                VariableItem var13 = (VariableItem)var3;
                var7 = var13.getCategory() + "." + var13.getOldLabel();
                var8 = var13.getCategory() + "." + var13.getNewLabel();
                return var2.replaceAll(var7, var8);
            } else if (var3 instanceof VariableCategoryItem) {
                VariableCategoryItem var12 = (VariableCategoryItem)var3;
                var7 = var12.getOldCategory() + "\\.";
                var8 = var12.getNewCategory() + "\\.";
                return var2.replaceAll(var7, var8);
            } else if (var3 instanceof ConstItem) {
                ConstItem var11 = (ConstItem)var3;
                var7 = "\\$" + var11.getCategory() + "." + var11.getOldLabel();
                var8 = "\\$" + var11.getCategory() + "." + var11.getNewLabel();
                return var2.replaceAll(var7, var8);
            } else if (var3 instanceof ConstCategoryItem) {
                ConstCategoryItem var10 = (ConstCategoryItem)var3;
                var7 = "\\$" + var10.getOldCategory() + ".";
                var8 = "\\$" + var10.getNewCategory() + ".";
                return var2.replaceAll(var7, var8);
            } else if (var3 instanceof BeanItem) {
                BeanItem var9 = (BeanItem)var3;
                var7 = "" + var9.getOldBeanLabel() + "\\.";
                var8 = "" + var9.getNewBeanLabel() + "\\.";
                return var2.replaceAll(var7, var8);
            } else if (var3 instanceof BeanMethodItem) {
                BeanMethodItem var6 = (BeanMethodItem)var3;
                var7 = "" + var6.getBeanLabel() + "." + var6.getOldMethodLabel() + "";
                var8 = "" + var6.getBeanLabel() + "." + var6.getNewMethodLabel() + "";
                return var2.replaceAll(var7, var8);
            } else {
                return null;
            }
        }
    }

    protected String perfectPath(String var1) {
        if (!var1.startsWith("/")) {
            var1 = "/" + var1;
        }

        return var1;
    }
}
