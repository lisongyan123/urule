//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder;

import com.bstek.urule.action.Action;
import com.bstek.urule.action.ConsolePrintAction;
import com.bstek.urule.action.ExecuteMethodAction;
import com.bstek.urule.action.TemplateAction;
import com.bstek.urule.action.VariableAssignAction;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.library.action.ActionLibrary;
import com.bstek.urule.model.library.action.Method;
import com.bstek.urule.model.library.action.SpringBean;
import com.bstek.urule.model.library.constant.Constant;
import com.bstek.urule.model.library.constant.ConstantCategory;
import com.bstek.urule.model.library.variable.Variable;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.rule.CommonFunctionValue;
import com.bstek.urule.model.rule.ComplexArithmetic;
import com.bstek.urule.model.rule.ConstantValue;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.MethodValue;
import com.bstek.urule.model.rule.Other;
import com.bstek.urule.model.rule.Parameter;
import com.bstek.urule.model.rule.ParameterValue;
import com.bstek.urule.model.rule.ParenValue;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.VariableCategoryValue;
import com.bstek.urule.model.rule.VariableValue;
import com.bstek.urule.model.rule.lhs.AbstractLeftPart;
import com.bstek.urule.model.rule.lhs.CommonFunctionLeftPart;
import com.bstek.urule.model.rule.lhs.CommonFunctionParameter;
import com.bstek.urule.model.rule.lhs.ConditionTemplateCriterion;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.FunctionLeftPart;
import com.bstek.urule.model.rule.lhs.Junction;
import com.bstek.urule.model.rule.lhs.Left;
import com.bstek.urule.model.rule.lhs.LeftPart;
import com.bstek.urule.model.rule.lhs.LeftType;
import com.bstek.urule.model.rule.lhs.Lhs;
import com.bstek.urule.model.rule.lhs.MethodLeftPart;
import com.bstek.urule.model.rule.lhs.VariableLeftPart;
import com.bstek.urule.model.rule.loop.LoopEnd;
import com.bstek.urule.model.rule.loop.LoopRule;
import com.bstek.urule.model.rule.loop.LoopRuleUnit;
import com.bstek.urule.model.rule.loop.LoopStart;
import com.bstek.urule.model.rule.loop.LoopTarget;
import com.bstek.urule.model.template.ActionTemplateUnit;
import com.bstek.urule.model.template.ConditionTemplateUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class RulesRebuilder {
    private ResourceLibraryBuilder a;

    public RulesRebuilder() {
    }

    public void rebuildRules(List<Library> var1, List<Rule> var2) {
        if (var1 != null) {
            if (var2 != null) {
                ResourceLibrary var3 = this.a.buildResourceLibrary(var1);
                Iterator var4 = var2.iterator();

                label140:
                while(true) {
                    Rule var5;
                    List var7;
                    Rhs var19;
                    Other var20;
                    do {
                        if (!var4.hasNext()) {
                            return;
                        }

                        var5 = (Rule)var4.next();
                        if (var5.getLhs() != null) {
                            Criterion var6 = var5.getLhs().getCriterion();
                            this.rebuildCriterion(var6, var3, false);
                        }

                        var19 = var5.getRhs();
                        var7 = null;
                        if (var19 != null) {
                            var7 = var19.getActions();
                        }

                        if (var7 != null) {
                            Iterator var8 = var7.iterator();

                            while(var8.hasNext()) {
                                Action var9 = (Action)var8.next();
                                this.rebuildAction(var9, var3, false);
                                this.a(var9, var3);
                            }
                        }

                        var20 = var5.getOther();
                        if (var20 != null) {
                            List var21 = var20.getActions();
                            if (var21 != null) {
                                Iterator var10 = var21.iterator();

                                while(var10.hasNext()) {
                                    Action var11 = (Action)var10.next();
                                    this.rebuildAction(var11, var3, false);
                                    this.a(var11, var3);
                                }
                            }
                        }
                    } while(!(var5 instanceof LoopRule));

                    LoopRule var22 = (LoopRule)var5;
                    LoopTarget var23 = var22.getLoopTarget();
                    if (var23 != null) {
                        Value var24 = var23.getValue();
                        this.rebuildValue(var24, var3, false);
                    }

                    LoopStart var25 = var22.getLoopStart();
                    if (var25 != null && var25.getActions() != null) {
                        Iterator var12 = var25.getActions().iterator();

                        while(var12.hasNext()) {
                            Action var13 = (Action)var12.next();
                            this.rebuildAction(var13, var3, false);
                            this.a(var13, var3);
                        }
                    }

                    LoopEnd var26 = var22.getLoopEnd();
                    if (var26 != null && var26.getActions() != null) {
                        Iterator var27 = var26.getActions().iterator();

                        while(var27.hasNext()) {
                            Action var14 = (Action)var27.next();
                            this.rebuildAction(var14, var3, false);
                            this.a(var14, var3);
                        }
                    }

                    List var28 = ((LoopRule)var5).getUnits();
                    Iterator var29 = var28.iterator();

                    while(true) {
                        List var31;
                        do {
                            do {
                                if (!var29.hasNext()) {
                                    continue label140;
                                }

                                LoopRuleUnit var15 = (LoopRuleUnit)var29.next();
                                if (var15.getLhs() != null) {
                                    Criterion var16 = var15.getLhs().getCriterion();
                                    this.rebuildCriterion(var16, var3, false);
                                }

                                var19 = var15.getRhs();
                                if (var19 != null) {
                                    var7 = var19.getActions();
                                }

                                if (var7 != null) {
                                    Iterator var30 = var7.iterator();

                                    while(var30.hasNext()) {
                                        Action var17 = (Action)var30.next();
                                        this.rebuildAction(var17, var3, false);
                                        this.a(var17, var3);
                                    }
                                }

                                var20 = var15.getOther();
                            } while(var20 == null);

                            var31 = var20.getActions();
                        } while(var31 == null);

                        Iterator var32 = var31.iterator();

                        while(var32.hasNext()) {
                            Action var18 = (Action)var32.next();
                            this.rebuildAction(var18, var3, false);
                            this.a(var18, var3);
                        }
                    }
                }
            }
        }
    }

    public void rebuildRulesForDSL(List<Library> var1, List<Rule> var2) {
        if (var1 != null) {
            if (var2 != null) {
                ResourceLibrary var3 = this.a.buildResourceLibrary(var1);
                Iterator var4 = var2.iterator();

                while(var4.hasNext()) {
                    Rule var5 = (Rule)var4.next();
                    if (var5.getLhs() != null) {
                        Criterion var6 = var5.getLhs().getCriterion();
                        this.rebuildCriterion(var6, var3, true);
                    }

                    Rhs var11 = var5.getRhs();
                    if (var11 != null && var11.getActions() != null) {
                        List var7 = var11.getActions();
                        Iterator var8 = var7.iterator();

                        while(var8.hasNext()) {
                            Action var9 = (Action)var8.next();
                            this.rebuildAction(var9, var3, true);
                        }
                    }

                    Other var12 = var5.getOther();
                    if (var12 != null) {
                        List var13 = var12.getActions();
                        if (var13 != null) {
                            Iterator var14 = var13.iterator();

                            while(var14.hasNext()) {
                                Action var10 = (Action)var14.next();
                                this.rebuildAction(var10, var3, true);
                            }
                        }
                    }

                    if (var5 instanceof LoopRule) {
                        this.a(var3, var5);
                    }
                }

            }
        }
    }

    private void a(Action var1, ResourceLibrary var2) {
        if (var1 instanceof TemplateAction) {
            TemplateAction var3 = (TemplateAction)var1;
            ActionTemplateUnit var4 = var2.getActionTemplateUnit(var3.getId());
            if (var4 == null) {
                throw new RuleException("动作模版【" + var3.getName() + "】不存在！");
            } else {
                var3.setName(var4.getName());
            }
        }
    }

    private void a(ResourceLibrary var1, Rule var2) {
        LoopRule var3 = (LoopRule)var2;
        LoopTarget var4 = var3.getLoopTarget();
        if (var4 != null) {
            Value var5 = var4.getValue();
            this.rebuildValue(var5, var1, true);
        }

        LoopStart var16 = var3.getLoopStart();
        if (var16 != null && var16.getActions() != null) {
            Iterator var6 = var16.getActions().iterator();

            while(var6.hasNext()) {
                Action var7 = (Action)var6.next();
                this.rebuildAction(var7, var1, true);
            }
        }

        LoopEnd var17 = var3.getLoopEnd();
        if (var17 != null && var17.getActions() != null) {
            Iterator var18 = var17.getActions().iterator();

            while(var18.hasNext()) {
                Action var8 = (Action)var18.next();
                this.rebuildAction(var8, var1, true);
            }
        }

        List var19 = var3.getUnits();
        Iterator var20 = var19.iterator();

        while(true) {
            List var23;
            do {
                Other var22;
                do {
                    if (!var20.hasNext()) {
                        return;
                    }

                    LoopRuleUnit var9 = (LoopRuleUnit)var20.next();
                    Lhs var10 = var9.getLhs();
                    if (var10 != null) {
                        Criterion var11 = var10.getCriterion();
                        this.rebuildCriterion(var11, var1, true);
                    }

                    Rhs var21 = var9.getRhs();
                    if (var21 != null) {
                        List var12 = var21.getActions();
                        Iterator var13 = var12.iterator();

                        while(var13.hasNext()) {
                            Action var14 = (Action)var13.next();
                            this.rebuildAction(var14, var1, true);
                        }
                    }

                    var22 = var9.getOther();
                } while(var22 == null);

                var23 = var22.getActions();
            } while(var23 == null);

            Iterator var24 = var23.iterator();

            while(var24.hasNext()) {
                Action var15 = (Action)var24.next();
                this.rebuildAction(var15, var1, true);
            }
        }
    }

    public void convertNamedJunctions(List<Rule> var1) {
        Iterator var2 = var1.iterator();

        while(var2.hasNext()) {
            Rule var3 = (Rule)var2.next();
            if (var3.getLhs() != null) {
                Criterion var4 = var3.getLhs().getCriterion();
                Criterion var5 = this.a(var4);
                var3.getLhs().setCriterion(var5);
            }
        }

    }

    private Criterion a(Criterion var1) {
        if (!(var1 instanceof Junction)) {
            return var1;
        } else {
            if (var1 instanceof Junction) {
                this.a((Junction)var1);
            }

            return var1;
        }
    }

    private void a(Junction var1) {
        List var2 = var1.getCriterions();
        ArrayList var3 = new ArrayList();

        Criterion var5;
        for(Iterator var4 = var2.iterator(); var4.hasNext(); var3.add(var5)) {
            var5 = (Criterion)var4.next();
            if (var5 instanceof Junction) {
                this.a((Junction)var5);
            }
        }

        var1.setCriterions(var3);
    }

    public void rebuildAction(Action var1, ResourceLibrary var2, boolean var3) {
        if (var1 != null) {
            List var4;
            String var7;
            if (var1 instanceof VariableAssignAction) {
                var4 = var2.getVariableCategories();
                if (var4 == null) {
                    return;
                }

                VariableAssignAction var5 = (VariableAssignAction)var1;
                LeftType var6 = var5.getType();
                if (var6 == null) {
                    var7 = var5.getVariableCategory();
                    String var8 = var5.getVariableLabel();
                    if (var8 == null) {
                        throw new RuleException("赋值只能针对具体变量或参数，请检查规则中变量赋值操作中是否存在直接对对象赋值的操作.");
                    }

                    if (var8.equals("return_value__")) {
                        var5.setVariableName(var8);
                        var5.setDatatype(Datatype.Boolean);
                    } else if (var8.equals("return_to__")) {
                        var5.setVariableName(var8);
                        var5.setDatatype(Datatype.String);
                    } else {
                        String var9 = var5.getVariableName();
                        Variable var10;
                        if (var3) {
                            var10 = this.getVariableByLabel(var4, var7, var8);
                            var5.setVariableName(var10.getName());
                            var5.setDatatype(var10.getType());
                        } else if (StringUtils.isNotBlank(var9)) {
                            var10 = this.getVariableByName(var4, var7, var9);
                            var5.setVariableLabel(var10.getLabel());
                            var5.setDatatype(var10.getType());
                        } else {
                            var10 = this.getVariableByLabel(var4, var7, var8);
                            var5.setVariableName(var10.getName());
                            var5.setDatatype(var10.getType());
                        }
                    }
                }

                Value var18 = ((VariableAssignAction)var1).getValue();
                this.rebuildValue(var18, var2, var3);
            } else if (var1 instanceof ConsolePrintAction) {
                ConsolePrintAction var14 = (ConsolePrintAction)var1;
                Value var15 = var14.getValue();
                this.rebuildValue(var15, var2, var3);
            } else if (var1 instanceof ExecuteMethodAction) {
                var4 = var2.getActionLibraries();
                if (var4 == null) {
                    return;
                }

                ExecuteMethodAction var16 = (ExecuteMethodAction)var1;
                String var17 = var16.getBeanLabel();
                var7 = var16.getMethodLabel();
                SpringBean var19 = null;
                Iterator var20 = var4.iterator();

                label100:
                do {
                    List var11;
                    do {
                        if (!var20.hasNext()) {
                            break label100;
                        }

                        ActionLibrary var22 = (ActionLibrary)var20.next();
                        var11 = var22.getSpringBeans();
                    } while(var11 == null);

                    Iterator var12 = var11.iterator();

                    while(var12.hasNext()) {
                        SpringBean var13 = (SpringBean)var12.next();
                        if (var17.equals(var13.getName())) {
                            var19 = var13;
                            break;
                        }
                    }
                } while(var19 == null);

                Method var21 = null;
                List var23;
                if (var19 != null) {
                    var16.setBeanId(var19.getId());
                    var23 = var19.getMethods();
                    if (var23 == null) {
                        throw new RuleException("Bean [" + var17 + "] not define methods.");
                    }

                    Iterator var24 = var23.iterator();

                    while(var24.hasNext()) {
                        Method var25 = (Method)var24.next();
                        if (var25.getName().equals(var7)) {
                            var21 = var25;
                            break;
                        }
                    }

                    if (var21 == null) {
                        throw new RuleException("Bean [" + var17 + "] method[" + var7 + "] not define.");
                    }

                    var16.setMethodName(var21.getMethodName());
                }

                if (var21 == null) {
                    throw new RuleException("Bean [" + var17 + "] method[" + var7 + "] not define.");
                }

                var23 = var16.getParameters();
                this.a(var2, var23, var21.getParameters(), var3);
            }

        }
    }

    private void a(CommonFunctionParameter var1, ResourceLibrary var2, boolean var3) {
        String var4 = var1.getProperty();
        if (!StringUtils.isEmpty(var4)) {
            Value var5 = var1.getObjectParameter();
            this.rebuildValue(var5, var2, var3);
            String var6 = null;
            if (var5 instanceof VariableValue) {
                VariableValue var7 = (VariableValue)var5;
                var6 = var7.getVariableCategory();
            } else {
                if (!(var5 instanceof VariableCategoryValue)) {
                    throw new RuleException("Function parameter is invalid.");
                }

                VariableCategoryValue var12 = (VariableCategoryValue)var5;
                var6 = var12.getVariableCategory();
            }

            List var13 = var2.getVariableCategories();
            Iterator var8 = var13.iterator();

            while(true) {
                while(true) {
                    VariableCategory var9;
                    do {
                        if (!var8.hasNext()) {
                            return;
                        }

                        var9 = (VariableCategory)var8.next();
                    } while(!var6.equals(var9.getName()));

                    Iterator var10 = var9.getVariables().iterator();

                    while(var10.hasNext()) {
                        Variable var11 = (Variable)var10.next();
                        if (var11.getName().equals(var4) || var11.getLabel().equals(var4)) {
                            var1.setProperty(var11.getName());
                            var1.setPropertyLabel(var11.getLabel());
                            break;
                        }
                    }
                }
            }
        }
    }

    private void a(ResourceLibrary var1, List<Parameter> var2, List<com.bstek.urule.model.library.action.Parameter> var3, boolean var4) {
        if (var2 != null && var3 != null) {
            for(int var5 = 0; var5 < var2.size() && var5 <= var3.size() - 1; ++var5) {
                Parameter var6 = (Parameter)var2.get(var5);
                com.bstek.urule.model.library.action.Parameter var7 = (com.bstek.urule.model.library.action.Parameter)var3.get(var5);
                var6.setType(var7.getType());
                Value var8 = var6.getValue();
                this.rebuildValue(var8, var1, var4);
            }
        }

    }

    public void rebuildCriterion(Criterion var1, ResourceLibrary var2, boolean var3) {
        if (var1 != null) {
            if (var1 instanceof Criteria) {
                Criteria var4 = (Criteria)var1;
                this.a(var2, var4, var3);
            } else if (var1 instanceof Junction) {
                Junction var8 = (Junction)var1;
                List var5 = var8.getCriterions();
                if (var5 != null) {
                    Iterator var6 = var5.iterator();

                    while(var6.hasNext()) {
                        Criterion var7 = (Criterion)var6.next();
                        this.rebuildCriterion(var7, var2, var3);
                    }
                }
            } else if (var1 instanceof ConditionTemplateCriterion) {
                ConditionTemplateCriterion var9 = (ConditionTemplateCriterion)var1;
                ConditionTemplateUnit var10 = var2.getConditionTemplateUnit(var9.getId());
                var9.setName(var10.getName());
            }

        }
    }

    private void a(ResourceLibrary var1, Criteria var2, boolean var3) {
        List var4 = var1.getVariableCategories();
        Left var5 = var2.getLeft();
        LeftPart var6 = var5.getLeftPart();
        String var8;
        String var9;
        String var10;
        Variable var11;
        if (var6 instanceof VariableLeftPart) {
            VariableLeftPart var7 = (VariableLeftPart)var6;
            var8 = var7.getVariableLabel();
            var9 = var7.getVariableName();
            if (StringUtils.isNotBlank(var8) && StringUtils.isBlank(var7.getKeyLabel())) {
                var10 = var7.getVariableCategory();
                if (var3) {
                    var11 = this.getVariableByLabel(var4, var10, var8);
                    var7.setVariableName(var11.getName());
                    var7.setDatatype(var11.getType());
                } else if (StringUtils.isBlank(var7.getKeyLabel())) {
                    var11 = this.getVariableByName(var4, var10, var9);
                    var7.setVariableLabel(var11.getLabel());
                    var7.setDatatype(var11.getType());
                }
            }
        } else if (var6 instanceof AbstractLeftPart) {
            AbstractLeftPart var17 = (AbstractLeftPart)var6;
            var8 = var17.getVariableCategory();
            var9 = var17.getVariableLabel();
            var10 = var17.getVariableName();
            if (var3) {
                var11 = this.getVariableByLabel(var4, var8, var9);
                var17.setVariableName(var11.getName());
            } else {
                var11 = this.getVariableByName(var4, var8, var10);
                var17.setVariableLabel(var11.getLabel());
            }
        } else if (var6 instanceof CommonFunctionLeftPart) {
            CommonFunctionLeftPart var18 = (CommonFunctionLeftPart)var6;
            CommonFunctionParameter var22 = var18.getParameter();
            this.a(var22, var1, var3);
        } else if (var6 instanceof MethodLeftPart) {
            MethodLeftPart var19 = (MethodLeftPart)var6;
            var8 = var19.getBeanLabel();
            var9 = var19.getMethodLabel();
            List var26 = var1.getActionLibraries();
            SpringBean var28 = null;
            Iterator var12 = var26.iterator();

            label87:
            do {
                List var14;
                do {
                    if (!var12.hasNext()) {
                        break label87;
                    }

                    ActionLibrary var13 = (ActionLibrary)var12.next();
                    var14 = var13.getSpringBeans();
                } while(var14 == null);

                Iterator var15 = var14.iterator();

                while(var15.hasNext()) {
                    SpringBean var16 = (SpringBean)var15.next();
                    if (var8.equals(var16.getName())) {
                        var19.setBeanId(var16.getId());
                        var28 = var16;
                        break;
                    }
                }
            } while(var28 == null);

            if (var28 == null) {
                throw new RuleException("Bean[" + var8 + "] not exist.");
            }

            Method var29 = null;
            Iterator var30 = var28.getMethods().iterator();

            while(var30.hasNext()) {
                Method var33 = (Method)var30.next();
                if (var9.equals(var33.getName())) {
                    var29 = var33;
                    var19.setMethodName(var33.getMethodName());
                    break;
                }
            }

            if (var29 == null) {
                throw new RuleException("Bean[" + var8 + "] method[" + var29 + "] not exist.");
            }

            List var32 = var19.getParameters();
            this.a(var1, var32, var29.getParameters(), var3);
        } else if (var6 instanceof FunctionLeftPart) {
            FunctionLeftPart var20 = (FunctionLeftPart)var6;
            List var23 = var20.getParameters();
            if (var23 != null && var23.size() > 0) {
                Iterator var25 = var23.iterator();

                while(var25.hasNext()) {
                    Parameter var27 = (Parameter)var25.next();
                    Value var31 = var27.getValue();
                    if (var31 != null) {
                        this.rebuildValue(var31, var1, var3);
                    }
                }
            }
        }

        ComplexArithmetic var21 = var5.getArithmetic();
        Value var24;
        if (var21 != null) {
            var24 = var21.getValue();
            this.rebuildValue(var24, var1, var3);
        }

        var24 = var2.getValue();
        this.rebuildValue(var24, var1, var3);
    }

    public void rebuildValue(Value var1, ResourceLibrary var2, boolean var3) {
        if (var1 != null) {
            Value var5;
            if (var1 instanceof ParenValue) {
                ParenValue var4 = (ParenValue)var1;
                var5 = var4.getValue();
                this.rebuildValue(var5, var2, var3);
            } else {
                String var6;
                String var17;
                if (var1 instanceof ConstantValue) {
                    ConstantValue var14 = (ConstantValue)var1;
                    var17 = var14.getConstantCategory();
                    if (var3) {
                        var6 = var14.getConstantLabel();
                        Constant var7 = this.a(var2.getConstantCategories(), var17, var6);
                        var14.setConstantName(var7.getName());
                        var14.setDatatype(var7.getType());
                    } else {
                        var6 = var14.getConstantName();
                        String var24 = var14.getConstantLabel();
                        Constant var8 = this.a(var2.getConstantCategories(), var17, var6, var24);
                        var14.setConstantLabel(var8.getLabel());
                        var14.setConstantName(var8.getName());
                        var14.setDatatype(var8.getType());
                    }
                } else if (var1 instanceof VariableValue) {
                    VariableValue var15 = (VariableValue)var1;
                    Variable var19;
                    if (var3) {
                        if (StringUtils.isNotBlank(var15.getVariableLabel())) {
                            var19 = this.getVariableByLabel(var2.getVariableCategories(), var15.getVariableCategory(), var15.getVariableLabel());
                            var15.setVariableName(var19.getName());
                            var15.setDatatype(var19.getType());
                        }
                    } else if (StringUtils.isNotBlank(var15.getVariableName())) {
                        var19 = this.getVariableByName(var2.getVariableCategories(), var15.getVariableCategory(), var15.getVariableName());
                        var15.setVariableLabel(var19.getLabel());
                        var15.setDatatype(var19.getType());
                    }
                } else if (var1 instanceof ParameterValue) {
                    ParameterValue var16 = (ParameterValue)var1;
                    Variable var22;
                    if (var3) {
                        var17 = var16.getVariableLabel();
                        var22 = this.getVariableByLabel(var2.getVariableCategories(), "参数", var17);
                        var16.setVariableName(var22.getName());
                    } else {
                        var17 = var16.getVariableName();
                        if (StringUtils.isBlank(var16.getKeyName())) {
                            var22 = this.getVariableByName(var2.getVariableCategories(), "参数", var17);
                            var16.setVariableLabel(var22.getLabel());
                        }
                    }
                } else if (var1 instanceof CommonFunctionValue) {
                    CommonFunctionValue var18 = (CommonFunctionValue)var1;
                    CommonFunctionParameter var23 = var18.getParameter();
                    this.a(var23, var2, var3);
                } else if (var1 instanceof MethodValue) {
                    MethodValue var20 = (MethodValue)var1;
                    var17 = var20.getBeanLabel();
                    var6 = var20.getMethodLabel();
                    List var25 = var2.getActionLibraries();
                    SpringBean var26 = null;
                    Iterator var9 = var25.iterator();

                    while(var9.hasNext()) {
                        ActionLibrary var10 = (ActionLibrary)var9.next();
                        List var11 = var10.getSpringBeans();
                        Iterator var12 = var11.iterator();

                        while(var12.hasNext()) {
                            SpringBean var13 = (SpringBean)var12.next();
                            if (var17.equals(var13.getName())) {
                                var20.setBeanId(var13.getId());
                                var26 = var13;
                                break;
                            }
                        }

                        if (var26 != null) {
                            break;
                        }
                    }

                    if (var26 == null) {
                        throw new RuleException("Bean[" + var17 + "] not exist.");
                    }

                    Method var27 = null;
                    Iterator var28 = var26.getMethods().iterator();

                    while(var28.hasNext()) {
                        Method var30 = (Method)var28.next();
                        if (var6.equals(var30.getName())) {
                            var27 = var30;
                            var20.setMethodName(var30.getMethodName());
                            break;
                        }
                    }

                    if (var27 == null) {
                        throw new RuleException("Bean[" + var17 + "] method[" + var27 + "] not exist.");
                    }

                    List var29 = var20.getParameters();
                    this.a(var2, var29, var27.getParameters(), var3);
                }
            }

            ComplexArithmetic var21 = var1.getArithmetic();
            if (var21 != null) {
                var5 = var21.getValue();
                this.rebuildValue(var5, var2, var3);
            }
        }
    }

    private Constant a(List<ConstantCategory> var1, String var2, String var3, String var4) {
        Iterator var5 = var1.iterator();

        while(true) {
            ConstantCategory var6;
            do {
                if (!var5.hasNext()) {
                    throw new RuleException("Constant [" + var2 + "." + var3 + "] was not found.");
                }

                var6 = (ConstantCategory)var5.next();
            } while(!var6.getLabel().equals(var2));

            Iterator var7 = var6.getConstants().iterator();

            while(var7.hasNext()) {
                Constant var8 = (Constant)var7.next();
                if (var8.getName().equals(var3) || var8.getLabel().equals(var4)) {
                    return var8;
                }
            }
        }
    }

    private Constant a(List<ConstantCategory> var1, String var2, String var3) {
        Iterator var4 = var1.iterator();

        while(true) {
            ConstantCategory var5;
            do {
                if (!var4.hasNext()) {
                    throw new RuleException("Constant [" + var2 + "." + var3 + "] was not found.");
                }

                var5 = (ConstantCategory)var4.next();
            } while(!var5.getLabel().equals(var2));

            Iterator var6 = var5.getConstants().iterator();

            while(var6.hasNext()) {
                Constant var7 = (Constant)var6.next();
                if (var7.getLabel().equals(var3)) {
                    return var7;
                }
            }
        }
    }

    public Variable getVariableByName(List<VariableCategory> var1, String var2, String var3) {
        Iterator var4 = var1.iterator();

        while(true) {
            VariableCategory var5;
            do {
                if (!var4.hasNext()) {
                    throw new RuleException("Variable [" + var2 + "." + var3 + "] was not found.");
                }

                var5 = (VariableCategory)var4.next();
            } while(!var5.getName().equals(var2));

            Iterator var6 = var5.getVariables().iterator();

            while(var6.hasNext()) {
                Variable var7 = (Variable)var6.next();
                if (var7.getName().equals(var3)) {
                    return var7;
                }
            }
        }
    }

    public Variable getVariableByLabel(List<VariableCategory> var1, String var2, String var3) {
        Iterator var4 = var1.iterator();

        while(true) {
            VariableCategory var5;
            do {
                if (!var4.hasNext()) {
                    throw new RuleException("Variable [" + var2 + "." + var3 + "] was not found.");
                }

                var5 = (VariableCategory)var4.next();
            } while(!var5.getName().equals(var2));

            Iterator var6 = var5.getVariables().iterator();

            while(var6.hasNext()) {
                Variable var7 = (Variable)var6.next();
                if (var7.getLabel().equals(var3)) {
                    return var7;
                }
            }
        }
    }

    public void setResourceLibraryBuilder(ResourceLibraryBuilder var1) {
        this.a = var1;
    }

    public ResourceLibraryBuilder getResourceLibraryBuilder() {
        return this.a;
    }
}
