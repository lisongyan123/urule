//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl.builder;

import com.bstek.urule.action.*;
import com.bstek.urule.dsl.RuleParserParser.*;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.model.rule.AbstractValue;
import com.bstek.urule.model.rule.Parameter;
import com.bstek.urule.model.rule.lhs.CommonFunctionParameter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.Iterator;

public class ActionContextBuilder extends AbstractContextBuilder implements ApplicationContextAware {
    private Collection<FunctionDescriptor> a;

    public ActionContextBuilder() {
    }

    public Action build(ParserRuleContext var1) {
        ActionContext var2 = (ActionContext)var1;
        if (var2.outAction() != null) {
            return this.a(var2.outAction());
        } else if (var2.assignAction() != null) {
            return this.a(var2.assignAction());
        } else if (var2.methodInvoke() != null) {
            return this.a(var2.methodInvoke());
        } else {
            return var2.commonFunction() != null ? this.a(var2.commonFunction()) : null;
        }
    }

    private ExecuteCommonFunctionAction a(CommonFunctionContext var1) {
        ExecuteCommonFunctionAction var2 = new ExecuteCommonFunctionAction();
        String var3 = var1.Identifier().getText();
        Iterator var4 = this.a.iterator();

        while(var4.hasNext()) {
            FunctionDescriptor var5 = (FunctionDescriptor)var4.next();
            if (var3.equals(var5.getName())) {
                var2.setName(var5.getName());
                var2.setLabel(var5.getLabel());
                break;
            }

            if (var3.equals(var5.getLabel())) {
                var2.setName(var5.getName());
                var2.setLabel(var5.getLabel());
                break;
            }
        }

        if (var2.getName() == null) {
            throw new RuleException("Function[" + var3 + "] not exist.");
        } else {
            ComplexValueContext var8 = var1.complexValue();
            CommonFunctionParameter var7 = new CommonFunctionParameter();
            var7.setObjectParameter(BuildUtils.buildValue(var8));
            PropertyContext var6 = var1.property();
            if (var6 != null) {
                var7.setProperty(var6.getText());
            }

            var2.setParameter(var7);
            return var2;
        }
    }

    private ExecuteMethodAction a(MethodInvokeContext var1) {
        ExecuteMethodAction var2 = new ExecuteMethodAction();
        BeanMethodContext var3 = var1.beanMethod();
        var2.setBeanLabel(var3.getChild(0).getText());
        var2.setMethodLabel(var3.getChild(2).getText());
        ActionParametersContext var4 = var1.actionParameters();
        if (var4 != null) {
            Iterator var5 = var4.complexValue().iterator();

            while(var5.hasNext()) {
                ComplexValueContext var6 = (ComplexValueContext)var5.next();
                Parameter var7 = new Parameter();
                var7.setValue(BuildUtils.buildValue(var6));
                var2.addParameter(var7);
            }
        }

        return var2;
    }

    private VariableAssignAction a(AssignActionContext var1) {
        VariableAssignAction var2 = new VariableAssignAction();
        ParameterContext var3 = var1.parameter();
        if (var3 == null) {
            var2.setVariableCategory(var1.variable().variableCategory().getText());
            var2.setVariableLabel(var1.variable().property().getText());
        } else {
            var2.setVariableCategory("参数");
            var2.setVariableLabel(var3.Identifier().getText());
        }

        var2.setValue(BuildUtils.buildValue(var1.complexValue()));
        return var2;
    }

    private ConsolePrintAction a(OutActionContext var1) {
        ConsolePrintAction var2 = new ConsolePrintAction();
        AbstractValue var3 = BuildUtils.buildValue(var1.complexValue());
        var2.setValue(var3);
        return var2;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.a = var1.getBeansOfType(FunctionDescriptor.class).values();
    }

    public boolean support(ParserRuleContext var1) {
        return var1 instanceof ActionContext;
    }
}
