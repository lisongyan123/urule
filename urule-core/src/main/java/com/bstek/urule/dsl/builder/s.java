//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl.builder;

import com.bstek.urule.dsl.DSLUtils;
import com.bstek.urule.dsl.RuleParserParser.*;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.model.rule.Op;
import com.bstek.urule.model.rule.Parameter;
import com.bstek.urule.model.rule.lhs.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CriteriaContextBuilder extends AbstractContextBuilder implements ApplicationContextAware {
    private Collection<FunctionDescriptor> a;

    public CriteriaContextBuilder() {
    }

    public Criteria build(ParserRuleContext var1) {
        SingleConditionContext var2 = (SingleConditionContext)var1;
        ConditionLeftContext var3 = var2.conditionLeft();
        VariableContext var4 = null;
        ParameterContext var5 = null;
        FunctionInvokeContext var6 = null;
        CommonFunctionContext var7 = null;
        MethodInvokeContext var8 = null;
        if (var3 != null) {
            var4 = var3.variable();
            var5 = var3.parameter();
            var6 = var3.functionInvoke();
            var7 = var3.commonFunction();
            var8 = var3.methodInvoke();
        }

        Criteria var9 = new Criteria();
        Left var10 = new Left();
        Object var11 = null;
        String var12 = null;
        String var13 = null;
        VariableLeftPart var14;
        if (var4 != null) {
            var12 = var4.variableCategory().Identifier().getText();
            var13 = var4.property().getText();
            var14 = new VariableLeftPart();
            var14.setVariableCategory(var12);
            var14.setVariableLabel(var13);
            var10.setType(LeftType.variable);
            var11 = var14;
        } else if (var5 != null) {
            var12 = "参数";
            var13 = var5.Identifier().getText();
            var14 = new VariableLeftPart();
            var14.setVariableCategory(var12);
            var14.setVariableLabel(var13);
            var10.setType(LeftType.variable);
            var11 = var14;
        } else {
            String var15;
            if (var6 != null) {
                FunctionLeftPart var23 = new FunctionLeftPart();
                var15 = var6.Identifier().getText();
                ActionParametersContext var16 = var6.actionParameters();
                if (var16 != null) {
                    ArrayList var17 = new ArrayList();
                    Iterator var18 = var16.complexValue().iterator();

                    while(var18.hasNext()) {
                        ComplexValueContext var19 = (ComplexValueContext)var18.next();
                        Parameter var20 = new Parameter();
                        var20.setValue(BuildUtils.buildValue(var19));
                        var17.add(var20);
                    }

                    var23.setParameters(var17);
                }

                var23.setName(var15);
                var10.setType(LeftType.function);
                var11 = var23;
            } else if (var7 != null) {
                CommonFunctionLeftPart var24 = new CommonFunctionLeftPart();
                var15 = var7.Identifier().getText();
                Iterator var29 = this.a.iterator();

                while(var29.hasNext()) {
                    FunctionDescriptor var32 = (FunctionDescriptor)var29.next();
                    if (var15.equals(var32.getName())) {
                        var24.setName(var32.getName());
                        var24.setLabel(var32.getLabel());
                        break;
                    }

                    if (var15.equals(var32.getLabel())) {
                        var24.setName(var32.getName());
                        var24.setLabel(var32.getLabel());
                        break;
                    }
                }

                if (var24.getName() == null) {
                    throw new RuleException("Function[" + var15 + "] not exist.");
                }

                ComplexValueContext var30 = var7.complexValue();
                CommonFunctionParameter var33 = new CommonFunctionParameter();
                var33.setObjectParameter(BuildUtils.buildValue(var30));
                PropertyContext var35 = var7.property();
                if (var35 != null) {
                    var33.setProperty(var35.getText());
                }

                var24.setParameter(var33);
                var10.setType(LeftType.commonfunction);
                var11 = var24;
            } else if (var8 != null) {
                MethodLeftPart var25 = new MethodLeftPart();
                BeanMethodContext var27 = var8.beanMethod();
                String var31 = var27.Identifier(0).getText();
                String var34 = var27.Identifier(1).getText();
                var25.setBeanLabel(var31);
                var25.setMethodLabel(var34);
                ActionParametersContext var36 = var8.actionParameters();
                if (var36 != null) {
                    ArrayList var37 = new ArrayList();
                    Iterator var38 = var36.complexValue().iterator();

                    while(var38.hasNext()) {
                        ComplexValueContext var21 = (ComplexValueContext)var38.next();
                        Parameter var22 = new Parameter();
                        var22.setValue(BuildUtils.buildValue(var21));
                        var37.add(var22);
                    }

                    var25.setParameters(var37);
                }

                var10.setType(LeftType.method);
                var11 = var25;
            }
        }

        var10.setLeftPart((LeftPart)var11);
        var9.setLeft(var10);
        Op var26 = DSLUtils.parseOp(var2.op());
        var9.setOp(var26);
        NullValueContext var28 = var2.nullValue();
        if (var28 != null) {
            if (var26.equals(Op.Equals)) {
                var9.setOp(Op.Null);
            } else {
                if (!var26.equals(Op.NotEquals)) {
                    throw new RuleException("'null' value only support '==' or '!=' operator.");
                }

                var9.setOp(Op.NotNull);
            }
        } else {
            var9.setValue(BuildUtils.buildValue(var2.complexValue()));
        }

        return var9;
    }

    public boolean support(ParserRuleContext var1) {
        return var1 instanceof SingleConditionContext;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.a = var1.getBeansOfType(FunctionDescriptor.class).values();
    }
}
