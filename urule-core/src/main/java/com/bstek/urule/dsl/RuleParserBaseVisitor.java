//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl;

import com.bstek.urule.dsl.RuleParserParser.ActionContext;
import com.bstek.urule.dsl.RuleParserParser.ActionParametersContext;
import com.bstek.urule.dsl.RuleParserParser.ActionsContext;
import com.bstek.urule.dsl.RuleParserParser.ActivationGroupAttributeContext;
import com.bstek.urule.dsl.RuleParserParser.AgendaGroupAttributeContext;
import com.bstek.urule.dsl.RuleParserParser.AssignActionContext;
import com.bstek.urule.dsl.RuleParserParser.AttributeContext;
import com.bstek.urule.dsl.RuleParserParser.AutoFocusAttributeContext;
import com.bstek.urule.dsl.RuleParserParser.BeanMethodContext;
import com.bstek.urule.dsl.RuleParserParser.ColonContext;
import com.bstek.urule.dsl.RuleParserParser.CommonFunctionContext;
import com.bstek.urule.dsl.RuleParserParser.ComplexValueContext;
import com.bstek.urule.dsl.RuleParserParser.ConditionLeftContext;
import com.bstek.urule.dsl.RuleParserParser.ConstantCategoryContext;
import com.bstek.urule.dsl.RuleParserParser.ConstantContext;
import com.bstek.urule.dsl.RuleParserParser.DebugAttributeContext;
import com.bstek.urule.dsl.RuleParserParser.EffectiveDateAttributeContext;
import com.bstek.urule.dsl.RuleParserParser.EnabledAttributeContext;
import com.bstek.urule.dsl.RuleParserParser.ExpiresDateAttributeContext;
import com.bstek.urule.dsl.RuleParserParser.ExprConditionContext;
import com.bstek.urule.dsl.RuleParserParser.ExpressionBodyContext;
import com.bstek.urule.dsl.RuleParserParser.FunctionDefContext;
import com.bstek.urule.dsl.RuleParserParser.FunctionImportContext;
import com.bstek.urule.dsl.RuleParserParser.FunctionInvokeContext;
import com.bstek.urule.dsl.RuleParserParser.FunctionParameterContext;
import com.bstek.urule.dsl.RuleParserParser.FunctionParametersContext;
import com.bstek.urule.dsl.RuleParserParser.ImportActionLibraryContext;
import com.bstek.urule.dsl.RuleParserParser.ImportConstantLibraryContext;
import com.bstek.urule.dsl.RuleParserParser.ImportParameterLibraryContext;
import com.bstek.urule.dsl.RuleParserParser.ImportVariableLibraryContext;
import com.bstek.urule.dsl.RuleParserParser.JoinContext;
import com.bstek.urule.dsl.RuleParserParser.LeftContext;
import com.bstek.urule.dsl.RuleParserParser.LeftParenContext;
import com.bstek.urule.dsl.RuleParserParser.LoopAttributeContext;
import com.bstek.urule.dsl.RuleParserParser.LoopEndContext;
import com.bstek.urule.dsl.RuleParserParser.LoopRuleDefContext;
import com.bstek.urule.dsl.RuleParserParser.LoopRuleUnitContext;
import com.bstek.urule.dsl.RuleParserParser.LoopStartContext;
import com.bstek.urule.dsl.RuleParserParser.LoopTargetContext;
import com.bstek.urule.dsl.RuleParserParser.MethodInvokeContext;
import com.bstek.urule.dsl.RuleParserParser.MultiCellConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.MultiConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.MultiNamedConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.NamedConditionSetContext;
import com.bstek.urule.dsl.RuleParserParser.NamedVariableCategoryContext;
import com.bstek.urule.dsl.RuleParserParser.NamedVariableContext;
import com.bstek.urule.dsl.RuleParserParser.NullValueContext;
import com.bstek.urule.dsl.RuleParserParser.OpContext;
import com.bstek.urule.dsl.RuleParserParser.OtherContext;
import com.bstek.urule.dsl.RuleParserParser.OutActionContext;
import com.bstek.urule.dsl.RuleParserParser.PackageDefContext;
import com.bstek.urule.dsl.RuleParserParser.ParameterContext;
import com.bstek.urule.dsl.RuleParserParser.ParameterNameContext;
import com.bstek.urule.dsl.RuleParserParser.ParenCellConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.ParenConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.ParenNamedConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.PercentContext;
import com.bstek.urule.dsl.RuleParserParser.PropertyContext;
import com.bstek.urule.dsl.RuleParserParser.RefNameContext;
import com.bstek.urule.dsl.RuleParserParser.RefObjectContext;
import com.bstek.urule.dsl.RuleParserParser.ResourceContext;
import com.bstek.urule.dsl.RuleParserParser.RightContext;
import com.bstek.urule.dsl.RuleParserParser.RightParenContext;
import com.bstek.urule.dsl.RuleParserParser.RuleDefContext;
import com.bstek.urule.dsl.RuleParserParser.RuleSetBodyContext;
import com.bstek.urule.dsl.RuleParserParser.RuleSetContext;
import com.bstek.urule.dsl.RuleParserParser.RuleSetHeaderContext;
import com.bstek.urule.dsl.RuleParserParser.RuleflowGroupAttributeContext;
import com.bstek.urule.dsl.RuleParserParser.RulesContext;
import com.bstek.urule.dsl.RuleParserParser.SalienceAttributeContext;
import com.bstek.urule.dsl.RuleParserParser.SingleCellConditionContext;
import com.bstek.urule.dsl.RuleParserParser.SingleConditionContext;
import com.bstek.urule.dsl.RuleParserParser.SingleNamedConditionSetContext;
import com.bstek.urule.dsl.RuleParserParser.SingleNamedConditionsContext;
import com.bstek.urule.dsl.RuleParserParser.ValueContext;
import com.bstek.urule.dsl.RuleParserParser.VariableCategoryContext;
import com.bstek.urule.dsl.RuleParserParser.VariableContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class RuleParserBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements RuleParserVisitor<T> {
    public RuleParserBaseVisitor() {
    }

    public T visitRuleSet(RuleSetContext var1) {
        return this.visitChildren(var1);
    }

    public T visitRuleSetHeader(RuleSetHeaderContext var1) {
        return this.visitChildren(var1);
    }

    public T visitRuleSetBody(RuleSetBodyContext var1) {
        return this.visitChildren(var1);
    }

    public T visitRules(RulesContext var1) {
        return this.visitChildren(var1);
    }

    public T visitFunctionImport(FunctionImportContext var1) {
        return this.visitChildren(var1);
    }

    public T visitPackageDef(PackageDefContext var1) {
        return this.visitChildren(var1);
    }

    public T visitResource(ResourceContext var1) {
        return this.visitChildren(var1);
    }

    public T visitImportParameterLibrary(ImportParameterLibraryContext var1) {
        return this.visitChildren(var1);
    }

    public T visitImportVariableLibrary(ImportVariableLibraryContext var1) {
        return this.visitChildren(var1);
    }

    public T visitImportConstantLibrary(ImportConstantLibraryContext var1) {
        return this.visitChildren(var1);
    }

    public T visitImportActionLibrary(ImportActionLibraryContext var1) {
        return this.visitChildren(var1);
    }

    public T visitFunctionDef(FunctionDefContext var1) {
        return this.visitChildren(var1);
    }

    public T visitFunctionParameters(FunctionParametersContext var1) {
        return this.visitChildren(var1);
    }

    public T visitFunctionParameter(FunctionParameterContext var1) {
        return this.visitChildren(var1);
    }

    public T visitRuleDef(RuleDefContext var1) {
        return this.visitChildren(var1);
    }

    public T visitLoopRuleDef(LoopRuleDefContext var1) {
        return this.visitChildren(var1);
    }

    public T visitLoopRuleUnit(LoopRuleUnitContext var1) {
        return this.visitChildren(var1);
    }

    public T visitLoopTarget(LoopTargetContext var1) {
        return this.visitChildren(var1);
    }

    public T visitLoopStart(LoopStartContext var1) {
        return this.visitChildren(var1);
    }

    public T visitLoopEnd(LoopEndContext var1) {
        return this.visitChildren(var1);
    }

    public T visitAttribute(AttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitLoopAttribute(LoopAttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitSalienceAttribute(SalienceAttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitEffectiveDateAttribute(EffectiveDateAttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitExpiresDateAttribute(ExpiresDateAttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitEnabledAttribute(EnabledAttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitDebugAttribute(DebugAttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitActivationGroupAttribute(ActivationGroupAttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitAgendaGroupAttribute(AgendaGroupAttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitAutoFocusAttribute(AutoFocusAttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitRuleflowGroupAttribute(RuleflowGroupAttributeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitLeft(LeftContext var1) {
        return this.visitChildren(var1);
    }

    public T visitParenConditions(ParenConditionsContext var1) {
        return this.visitChildren(var1);
    }

    public T visitMultiConditions(MultiConditionsContext var1) {
        return this.visitChildren(var1);
    }

    public T visitSingleCondition(SingleConditionContext var1) {
        return this.visitChildren(var1);
    }

    public T visitSingleNamedConditionSet(SingleNamedConditionSetContext var1) {
        return this.visitChildren(var1);
    }

    public T visitNamedConditionSet(NamedConditionSetContext var1) {
        return this.visitChildren(var1);
    }

    public T visitParenNamedConditions(ParenNamedConditionsContext var1) {
        return this.visitChildren(var1);
    }

    public T visitMultiNamedConditions(MultiNamedConditionsContext var1) {
        return this.visitChildren(var1);
    }

    public T visitSingleNamedConditions(SingleNamedConditionsContext var1) {
        return this.visitChildren(var1);
    }

    public T visitSingleCellCondition(SingleCellConditionContext var1) {
        return this.visitChildren(var1);
    }

    public T visitMultiCellConditions(MultiCellConditionsContext var1) {
        return this.visitChildren(var1);
    }

    public T visitParenCellConditions(ParenCellConditionsContext var1) {
        return this.visitChildren(var1);
    }

    public T visitRefName(RefNameContext var1) {
        return this.visitChildren(var1);
    }

    public T visitRefObject(RefObjectContext var1) {
        return this.visitChildren(var1);
    }

    public T visitNullValue(NullValueContext var1) {
        return this.visitChildren(var1);
    }

    public T visitConditionLeft(ConditionLeftContext var1) {
        return this.visitChildren(var1);
    }

    public T visitCommonFunction(CommonFunctionContext var1) {
        return this.visitChildren(var1);
    }

    public T visitExprCondition(ExprConditionContext var1) {
        return this.visitChildren(var1);
    }

    public T visitExpressionBody(ExpressionBodyContext var1) {
        return this.visitChildren(var1);
    }

    public T visitPercent(PercentContext var1) {
        return this.visitChildren(var1);
    }

    public T visitLeftParen(LeftParenContext var1) {
        return this.visitChildren(var1);
    }

    public T visitRightParen(RightParenContext var1) {
        return this.visitChildren(var1);
    }

    public T visitColon(ColonContext var1) {
        return this.visitChildren(var1);
    }

    public T visitJoin(JoinContext var1) {
        return this.visitChildren(var1);
    }

    public T visitRight(RightContext var1) {
        return this.visitChildren(var1);
    }

    public T visitOther(OtherContext var1) {
        return this.visitChildren(var1);
    }

    public T visitActions(ActionsContext var1) {
        return this.visitChildren(var1);
    }

    public T visitAction(ActionContext var1) {
        return this.visitChildren(var1);
    }

    public T visitAssignAction(AssignActionContext var1) {
        return this.visitChildren(var1);
    }

    public T visitOutAction(OutActionContext var1) {
        return this.visitChildren(var1);
    }

    public T visitMethodInvoke(MethodInvokeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitFunctionInvoke(FunctionInvokeContext var1) {
        return this.visitChildren(var1);
    }

    public T visitActionParameters(ActionParametersContext var1) {
        return this.visitChildren(var1);
    }

    public T visitBeanMethod(BeanMethodContext var1) {
        return this.visitChildren(var1);
    }

    public T visitComplexValue(ComplexValueContext var1) {
        return this.visitChildren(var1);
    }

    public T visitParameter(ParameterContext var1) {
        return this.visitChildren(var1);
    }

    public T visitParameterName(ParameterNameContext var1) {
        return this.visitChildren(var1);
    }

    public T visitConstant(ConstantContext var1) {
        return this.visitChildren(var1);
    }

    public T visitVariable(VariableContext var1) {
        return this.visitChildren(var1);
    }

    public T visitNamedVariable(NamedVariableContext var1) {
        return this.visitChildren(var1);
    }

    public T visitProperty(PropertyContext var1) {
        return this.visitChildren(var1);
    }

    public T visitVariableCategory(VariableCategoryContext var1) {
        return this.visitChildren(var1);
    }

    public T visitNamedVariableCategory(NamedVariableCategoryContext var1) {
        return this.visitChildren(var1);
    }

    public T visitConstantCategory(ConstantCategoryContext var1) {
        return this.visitChildren(var1);
    }

    public T visitValue(ValueContext var1) {
        return this.visitChildren(var1);
    }

    public T visitOp(OpContext var1) {
        return this.visitChildren(var1);
    }
}
