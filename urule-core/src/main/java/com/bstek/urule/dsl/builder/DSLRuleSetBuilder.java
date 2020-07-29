//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl.builder;

import com.bstek.urule.Utils;
import com.bstek.urule.dsl.RuleParserParser.*;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.model.rule.*;
import com.bstek.urule.model.rule.lhs.CommonFunctionParameter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BuildUtils {
    public BuildUtils() {
    }

    public static AbstractValue buildValue(ComplexValueContext var0) {
        Object var1 = null;
        List var19;
        if (var0.leftParen() != null) {
            ParenValue var2 = new ParenValue();
            List var3 = var0.complexValue();
            AbstractValue var4 = buildValue((ComplexValueContext)var3.get(0));
            var2.setValue(var4);
            var1 = var2;
        } else if (var0.value() != null) {
            var1 = a(var0.value());
        } else if (var0.variable() != null) {
            var1 = a(var0.variable());
        } else if (var0.constant() != null) {
            var1 = a(var0.constant());
        } else if (var0.variableCategory() != null) {
            VariableCategoryContext var13 = var0.variableCategory();
            String var16 = var13.Identifier().getText();
            var1 = new VariableCategoryValue(var16);
        } else if (var0.parameter() != null) {
            ParameterContext var14 = var0.parameter();
            ParameterValue var18 = new ParameterValue();
            var18.setVariableLabel(var14.Identifier().getText());
            var1 = var18;
        } else {
            String var5;
            if (var0.methodInvoke() != null) {
                MethodInvokeContext var15 = var0.methodInvoke();
                MethodValue var20 = new MethodValue();
                BeanMethodContext var21 = var15.beanMethod();
                var5 = var21.Identifier(0).getText();
                String var6 = var21.Identifier(1).getText();
                var20.setBeanLabel(var5);
                var20.setMethodLabel(var6);
                ActionParametersContext var7 = var15.actionParameters();
                if (var7 != null && var7.complexValue() != null) {
                    List var8 = var7.complexValue();
                    ArrayList var9 = new ArrayList();
                    Iterator var10 = var8.iterator();

                    while(var10.hasNext()) {
                        ComplexValueContext var11 = (ComplexValueContext)var10.next();
                        Parameter var12 = new Parameter();
                        var12.setValue(buildValue(var11));
                        var9.add(var12);
                    }

                    var20.setParameters(var9);
                }

                var1 = var20;
            } else if (var0.commonFunction() != null) {
                CommonFunctionContext var17 = var0.commonFunction();
                Collection var22 = Utils.getApplicationContext().getBeansOfType(FunctionDescriptor.class).values();
                CommonFunctionValue var24 = new CommonFunctionValue();
                var5 = var17.Identifier().getText();
                Iterator var27 = var22.iterator();

                while(var27.hasNext()) {
                    FunctionDescriptor var29 = (FunctionDescriptor)var27.next();
                    if (var5.equals(var29.getName())) {
                        var24.setName(var29.getName());
                        var24.setLabel(var29.getLabel());
                        break;
                    }

                    if (var5.equals(var29.getLabel())) {
                        var24.setName(var29.getName());
                        var24.setLabel(var29.getLabel());
                        break;
                    }
                }

                if (var24.getName() == null) {
                    throw new RuleException("Function[" + var5 + "] not exist.");
                }

                ComplexValueContext var28 = var17.complexValue();
                CommonFunctionParameter var30 = new CommonFunctionParameter();
                var30.setObjectParameter(buildValue(var28));
                PropertyContext var31 = var17.property();
                if (var31 != null) {
                    var30.setProperty(var31.getText());
                }

                var24.setParameter(var30);
                var1 = var24;
            } else if (var0.complexValue() != null) {
                var19 = var0.complexValue();
                var1 = buildValue((ComplexValueContext)var19.get(0));
            }
        }

        var19 = var0.ARITH();
        if (var19 != null && var19.size() > 0) {
            TerminalNode var23 = (TerminalNode)var19.get(0);
            ComplexArithmetic var25 = new ComplexArithmetic();
            var25.setType(ArithmeticType.parse(var23.getText()));
            ParseTree var26 = var0.getChild(2);
            var25.setValue(buildValue((ComplexValueContext)var26));
            ((AbstractValue)var1).setArithmetic(var25);
        }

        return (AbstractValue)var1;
    }

    private static ConstantValue a(ConstantContext var0) {
        ConstantValue var1 = new ConstantValue();
        var1.setConstantCategory(var0.constantCategory().Identifier().getText());
        var1.setConstantLabel(var0.property().getText());
        return var1;
    }

    private static VariableValue a(VariableContext var0) {
        VariableValue var1 = new VariableValue();
        var1.setVariableCategory(var0.variableCategory().getText());
        var1.setVariableLabel(var0.property().getText());
        return var1;
    }

    private static SimpleValue a(ValueContext var0) {
        SimpleValue var1 = new SimpleValue();
        if (var0.STRING() != null) {
            var1.setContent(getSTRINGContent(var0.STRING()));
        } else if (var0.Boolean() != null) {
            var1.setContent(var0.Boolean().getText());
        } else if (var0.NUMBER() != null) {
            var1.setContent(var0.NUMBER().getText());
        }

        return var1;
    }

    public static String getSTRINGContent(TerminalNode var0) {
        String var1 = var0.getText();
        return var1.substring(1, var1.length() - 1);
    }
}
