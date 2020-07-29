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
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public interface RuleParserVisitor<T> extends ParseTreeVisitor<T> {
    T visitRuleSet(RuleSetContext var1);

    T visitRuleSetHeader(RuleSetHeaderContext var1);

    T visitRuleSetBody(RuleSetBodyContext var1);

    T visitRules(RulesContext var1);

    T visitFunctionImport(FunctionImportContext var1);

    T visitPackageDef(PackageDefContext var1);

    T visitResource(ResourceContext var1);

    T visitImportParameterLibrary(ImportParameterLibraryContext var1);

    T visitImportVariableLibrary(ImportVariableLibraryContext var1);

    T visitImportConstantLibrary(ImportConstantLibraryContext var1);

    T visitImportActionLibrary(ImportActionLibraryContext var1);

    T visitFunctionDef(FunctionDefContext var1);

    T visitFunctionParameters(FunctionParametersContext var1);

    T visitFunctionParameter(FunctionParameterContext var1);

    T visitRuleDef(RuleDefContext var1);

    T visitLoopRuleDef(LoopRuleDefContext var1);

    T visitLoopRuleUnit(LoopRuleUnitContext var1);

    T visitLoopTarget(LoopTargetContext var1);

    T visitLoopStart(LoopStartContext var1);

    T visitLoopEnd(LoopEndContext var1);

    T visitAttribute(AttributeContext var1);

    T visitLoopAttribute(LoopAttributeContext var1);

    T visitSalienceAttribute(SalienceAttributeContext var1);

    T visitEffectiveDateAttribute(EffectiveDateAttributeContext var1);

    T visitExpiresDateAttribute(ExpiresDateAttributeContext var1);

    T visitEnabledAttribute(EnabledAttributeContext var1);

    T visitDebugAttribute(DebugAttributeContext var1);

    T visitActivationGroupAttribute(ActivationGroupAttributeContext var1);

    T visitAgendaGroupAttribute(AgendaGroupAttributeContext var1);

    T visitAutoFocusAttribute(AutoFocusAttributeContext var1);

    T visitRuleflowGroupAttribute(RuleflowGroupAttributeContext var1);

    T visitLeft(LeftContext var1);

    T visitParenConditions(ParenConditionsContext var1);

    T visitMultiConditions(MultiConditionsContext var1);

    T visitSingleCondition(SingleConditionContext var1);

    T visitSingleNamedConditionSet(SingleNamedConditionSetContext var1);

    T visitNamedConditionSet(NamedConditionSetContext var1);

    T visitParenNamedConditions(ParenNamedConditionsContext var1);

    T visitMultiNamedConditions(MultiNamedConditionsContext var1);

    T visitSingleNamedConditions(SingleNamedConditionsContext var1);

    T visitSingleCellCondition(SingleCellConditionContext var1);

    T visitMultiCellConditions(MultiCellConditionsContext var1);

    T visitParenCellConditions(ParenCellConditionsContext var1);

    T visitRefName(RefNameContext var1);

    T visitRefObject(RefObjectContext var1);

    T visitNullValue(NullValueContext var1);

    T visitConditionLeft(ConditionLeftContext var1);

    T visitCommonFunction(CommonFunctionContext var1);

    T visitExprCondition(ExprConditionContext var1);

    T visitExpressionBody(ExpressionBodyContext var1);

    T visitPercent(PercentContext var1);

    T visitLeftParen(LeftParenContext var1);

    T visitRightParen(RightParenContext var1);

    T visitColon(ColonContext var1);

    T visitJoin(JoinContext var1);

    T visitRight(RightContext var1);

    T visitOther(OtherContext var1);

    T visitActions(ActionsContext var1);

    T visitAction(ActionContext var1);

    T visitAssignAction(AssignActionContext var1);

    T visitOutAction(OutActionContext var1);

    T visitMethodInvoke(MethodInvokeContext var1);

    T visitFunctionInvoke(FunctionInvokeContext var1);

    T visitActionParameters(ActionParametersContext var1);

    T visitBeanMethod(BeanMethodContext var1);

    T visitComplexValue(ComplexValueContext var1);

    T visitParameter(ParameterContext var1);

    T visitParameterName(ParameterNameContext var1);

    T visitConstant(ConstantContext var1);

    T visitVariable(VariableContext var1);

    T visitNamedVariable(NamedVariableContext var1);

    T visitProperty(PropertyContext var1);

    T visitVariableCategory(VariableCategoryContext var1);

    T visitNamedVariableCategory(NamedVariableCategoryContext var1);

    T visitConstantCategory(ConstantCategoryContext var1);

    T visitValue(ValueContext var1);

    T visitOp(OpContext var1);
}
