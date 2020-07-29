//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.rete;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.rete.ObjectTypeNode;
import com.bstek.urule.model.rule.CommonFunctionValue;
import com.bstek.urule.model.rule.ComplexArithmetic;
import com.bstek.urule.model.rule.MethodValue;
import com.bstek.urule.model.rule.Parameter;
import com.bstek.urule.model.rule.ParameterValue;
import com.bstek.urule.model.rule.ParenValue;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.VariableCategoryValue;
import com.bstek.urule.model.rule.VariableValue;
import com.bstek.urule.model.rule.lhs.AbstractLeftPart;
import com.bstek.urule.model.rule.lhs.AccumulateLeftPart;
import com.bstek.urule.model.rule.lhs.BaseCriteria;
import com.bstek.urule.model.rule.lhs.CalculateItem;
import com.bstek.urule.model.rule.lhs.CommonFunctionLeftPart;
import com.bstek.urule.model.rule.lhs.CommonFunctionParameter;
import com.bstek.urule.model.rule.lhs.ConditionItem;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.FunctionLeftPart;
import com.bstek.urule.model.rule.lhs.LeftPart;
import com.bstek.urule.model.rule.lhs.MethodLeftPart;
import com.bstek.urule.model.rule.lhs.VariableLeftPart;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuildContextImpl implements BuildContext {
    private ResourceLibrary a;
    private List<ObjectTypeNode> b;
    private IdGenerator c;
    private Rule d;

    public BuildContextImpl(ResourceLibrary var1, List<ObjectTypeNode> var2) {
        this.a = var1;
        this.b = var2;
        this.c = new IdGenerator();
    }

    public BuildContextImpl(List<ObjectTypeNode> var1, BuildContext var2) {
        this.a = var2.getResourceLibrary();
        this.b = var1;
        this.c = var2.getIdGenerator();
    }

    public boolean assertSameType(BaseCriteria var1, BaseCriteria var2) {
        VariableCategory var3 = this.a(var1);
        VariableCategory var4 = this.a(var2);
        return var3 != null && var4 != null ? var3.getClazz().equals(var4.getClazz()) : false;
    }

    private VariableCategory a(BaseCriteria var1) {
        VariableCategory var2 = null;
        if (var1 instanceof Criteria) {
            Criteria var3 = (Criteria)var1;
            LeftPart var4 = var3.getLeft().getLeftPart();
            if (var4 instanceof VariableLeftPart) {
                VariableLeftPart var5 = (VariableLeftPart)var4;
                var2 = this.a.getVariableCategory(var5.getVariableCategory());
            }

            return var2;
        } else {
            throw new RuleException("Unknow Criteria : " + var1);
        }
    }

    public List<String> getObjectType(BaseCriteria var1) {
        ArrayList var2 = new ArrayList();
        if (!(var1 instanceof Criteria)) {
            throw new RuleException("Unknow Criteria : " + var1);
        } else {
            Criteria var3 = (Criteria)var1;
            LeftPart var4 = var3.getLeft().getLeftPart();
            if (var4 instanceof VariableLeftPart) {
                VariableLeftPart var5 = (VariableLeftPart)var4;
                String var6 = var5.getVariableCategory();
                VariableCategory var7 = this.a.getVariableCategory(var6);
                var2.add(var7.getClazz());
            } else if (var4 instanceof AbstractLeftPart) {
                AbstractLeftPart var12 = (AbstractLeftPart)var4;
                VariableCategory var15 = this.a.getVariableCategory(var12.getVariableCategory());
                var2.add(var15.getClazz());
            } else if (var4 instanceof CommonFunctionLeftPart) {
                CommonFunctionLeftPart var13 = (CommonFunctionLeftPart)var4;
                CommonFunctionParameter var17 = var13.getParameter();
                Value var21 = var17.getObjectParameter();
                this.a(var21, var2);
            } else {
                Parameter var8;
                Value var9;
                List var20;
                Iterator var22;
                if (var4 instanceof MethodLeftPart) {
                    MethodLeftPart var14 = (MethodLeftPart)var4;
                    var20 = var14.getParameters();
                    if (var20 != null) {
                        var22 = var20.iterator();

                        while(var22.hasNext()) {
                            var8 = (Parameter)var22.next();
                            var9 = var8.getValue();
                            this.a(var9, var2);
                        }
                    }
                } else if (var4 instanceof FunctionLeftPart) {
                    FunctionLeftPart var16 = (FunctionLeftPart)var4;
                    var20 = var16.getParameters();
                    if (var20 != null) {
                        var22 = var20.iterator();

                        while(var22.hasNext()) {
                            var8 = (Parameter)var22.next();
                            var9 = var8.getValue();
                            this.a(var9, var2);
                        }
                    }
                } else if (var4 instanceof AccumulateLeftPart) {
                    AccumulateLeftPart var18 = (AccumulateLeftPart)var4;
                    Value var23 = var18.getLoopTarget().getValue();
                    this.a(var23, var2);
                    var22 = var18.getConditionItems().iterator();

                    while(var22.hasNext()) {
                        ConditionItem var24 = (ConditionItem)var22.next();
                        var23 = var24.getValue();
                        if (var23 != null) {
                            this.a(var23, var2);
                        }
                    }

                    var22 = var18.getCalculateItems().iterator();

                    while(var22.hasNext()) {
                        CalculateItem var25 = (CalculateItem)var22.next();
                        if (var25.isEnableAssignment()) {
                            String var26 = var25.getAssignVariableCategory();
                            VariableCategory var10 = this.a.getVariableCategory(var26);
                            String var11 = var10.getClazz();
                            if (!var2.contains(var11)) {
                                var2.add(var11);
                            }
                        }
                    }
                }
            }

            ComplexArithmetic var19 = var3.getLeft().getArithmetic();
            if (var19 != null) {
                this.a(var19.getValue(), var2);
            }

            this.a(var3.getValue(), var2);
            if (var2.size() == 0) {
                var2.add("*");
            }

            if (var1 instanceof Criteria) {
                var3 = (Criteria)var1;
                var3.addNecessaryClasses(var2);
            }

            return var2;
        }
    }

    private void a(Value var1, List<String> var2) {
        if (var1 != null) {
            Value var16;
            if (var1 instanceof CommonFunctionValue) {
                CommonFunctionValue var3 = (CommonFunctionValue)var1;
                CommonFunctionParameter var4 = var3.getParameter();
                Value var5 = var4.getObjectParameter();
                this.a(var5, var2);
            } else if (var1 instanceof MethodValue) {
                MethodValue var8 = (MethodValue)var1;
                List var11 = var8.getParameters();
                if (var11 != null) {
                    Iterator var17 = var11.iterator();

                    while(var17.hasNext()) {
                        Parameter var6 = (Parameter)var17.next();
                        Value var7 = var6.getValue();
                        this.a(var7, var2);
                    }
                }
            } else {
                String var13;
                if (var1 instanceof ParameterValue) {
                    VariableCategory var9 = this.a.getVariableCategory("参数");
                    var13 = var9.getClazz();
                    if (!var2.contains(var13)) {
                        var2.add(var13);
                    }
                } else if (var1 instanceof ParenValue) {
                    ParenValue var10 = (ParenValue)var1;
                    var16 = var10.getValue();
                    this.a(var16, var2);
                } else {
                    VariableCategory var18;
                    String var19;
                    if (var1 instanceof VariableCategoryValue) {
                        VariableCategoryValue var12 = (VariableCategoryValue)var1;
                        var13 = var12.getVariableCategory();
                        var18 = this.a.getVariableCategory(var13);
                        var19 = var18.getClazz();
                        if (!var2.contains(var19)) {
                            var2.add(var19);
                        }
                    } else if (var1 instanceof VariableValue) {
                        VariableValue var14 = (VariableValue)var1;
                        var13 = var14.getVariableCategory();
                        var18 = this.a.getVariableCategory(var13);
                        var19 = var18.getClazz();
                        if (!var2.contains(var19)) {
                            var2.add(var19);
                        }
                    }
                }
            }

            ComplexArithmetic var15 = var1.getArithmetic();
            if (var15 != null) {
                var16 = var15.getValue();
                this.a(var16, var2);
            }

        }
    }

    public ObjectTypeNode buildObjectTypeNode(String var1) {
        ObjectTypeNode var2 = null;
        Iterator var3 = this.b.iterator();

        while(var3.hasNext()) {
            ObjectTypeNode var4 = (ObjectTypeNode)var3.next();
            if (var4.support(var1)) {
                var2 = var4;
                break;
            }
        }

        if (var2 == null) {
            var2 = new ObjectTypeNode(var1, this.nextId());
            this.b.add(var2);
        }

        return var2;
    }

    public ResourceLibrary getResourceLibrary() {
        return this.a;
    }

    public int nextId() {
        return this.c.nextId();
    }

    public IdGenerator getIdGenerator() {
        return this.c;
    }

    public void setCurrentRule(Rule var1) {
        this.d = var1;
    }

    public Rule currentRule() {
        return this.d;
    }

    public boolean currentRuleIsDebug() {
        if (this.d == null) {
            return false;
        } else {
            return this.d.getDebug() != null && this.d.getDebug();
        }
    }
}
