//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Utils;
import com.bstek.urule.action.ActionValue;
import com.bstek.urule.action.ExecuteMethodAction;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.function.Argument;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.model.rule.AbstractValue;
import com.bstek.urule.model.rule.ArithmeticType;
import com.bstek.urule.model.rule.CommonFunctionValue;
import com.bstek.urule.model.rule.ComplexArithmetic;
import com.bstek.urule.model.rule.ConstantValue;
import com.bstek.urule.model.rule.MathValue;
import com.bstek.urule.model.rule.MethodValue;
import com.bstek.urule.model.rule.ObjectValue;
import com.bstek.urule.model.rule.ParameterValue;
import com.bstek.urule.model.rule.ParenValue;
import com.bstek.urule.model.rule.SimpleValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import com.bstek.urule.model.rule.VariableCategoryValue;
import com.bstek.urule.model.rule.VariableValue;
import com.bstek.urule.model.rule.lhs.CommonFunctionParameter;
import com.bstek.urule.runtime.expr.ValueBuilder;
import com.bstek.urule.runtime.expr.ValueWrapper;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

public class ValueCompute {
    private static Logger a = Logger.getGlobal();
    public static final String BEAN_ID = "urule.valueCompute";
    private static final String b = "${";
    private static final String c = "}";

    public ValueCompute() {
    }

    public Object complexValueCompute(Value var1, Context var2, Map<String, Object> var3) {
        ValueWrapper var4 = this.a(var1, var2, var3);
        return var4.getValue() != null ? var4.getValue() : var4.getOriginalValue();
    }

    public Object complexArithmeticCompute(Context var1, Map<String, Object> var2, ComplexArithmetic var3, Object var4) {
        ValueBuilder var5 = new ValueBuilder();
        var5.addValue(new ValueWrapper(var4));

        AbstractValue var7;
        for(; var3 != null; var3 = var7.getArithmetic()) {
            ArithmeticType var6 = var3.getType();
            var5.addValue(var6);
            var7 = (AbstractValue)var3.getValue();
            if (var7 instanceof ParenValue) {
                ParenValue var8 = (ParenValue)var7;
                ValueWrapper var9 = this.a(var8.getValue(), var1, var2);
                var5.addValue(var9);
            } else {
                Object var11 = this.b(var7, var1, var2);
                if (var11 == null) {
                    var11 = "null";
                }

                var5.addValue(new ValueWrapper(var11));
            }
        }

        ValueWrapper var10 = var5.build();
        if (var10.getValue() != null) {
            return var10.getValue();
        } else {
            return var10.getOriginalValue();
        }
    }

    private ValueWrapper a(Value var1, Context var2, Map<String, Object> var3) {
        Object var4 = this.b(var1, var2, var3);
        ComplexArithmetic var5 = var1.getArithmetic();
        if (var5 == null) {
            return new ValueWrapper(var4);
        } else {
            ValueBuilder var6 = new ValueBuilder();
            var6.addValue(new ValueWrapper(var4));

            AbstractValue var8;
            for(; var5 != null; var5 = var8.getArithmetic()) {
                ArithmeticType var7 = var5.getType();
                var6.addValue(var7);
                var8 = (AbstractValue)var5.getValue();
                if (var8 instanceof ParenValue) {
                    ParenValue var9 = (ParenValue)var8;
                    ValueWrapper var10 = this.a(var9.getValue(), var2, var3);
                    var6.addValue(var10);
                } else {
                    Object var11 = this.b(var8, var2, var3);
                    if (var11 == null) {
                        var11 = "null";
                    }

                    var6.addValue(new ValueWrapper(var11));
                }
            }

            return var6.build();
        }
    }

    private Object b(Value var1, Context var2, Map<String, Object> var3) {
        Object var4 = null;
        ValueType var5 = var1.getValueType();
        if (var5.equals(ValueType.Input)) {
            var4 = ((SimpleValue)var1).getContent();
        } else if (var5.equals(ValueType.Constant)) {
            ConstantValue var6 = (ConstantValue)var1;
            var4 = this.a(var6.getConstantName());
            if (var6.getDatatype() != null) {
                var4 = var6.getDatatype().convert(var4);
            }
        } else {
            String var7;
            String var8;
            if (var5.equals(ValueType.VariableCategory)) {
                VariableCategoryValue var23 = (VariableCategoryValue)var1;
                var7 = var23.getVariableCategory();
                var8 = var2.getVariableCategoryClass(var7);
                return this.findObject(var8, var3, var2);
            }

            Object var9;
            String var10;
            if (var5.equals(ValueType.Parameter)) {
                ParameterValue var13 = (ParameterValue)var1;
                var7 = "参数";
                var8 = var2.getVariableCategoryClass(var7);
                var9 = this.findObject(var8, var3, var2);
                if (var9 == null) {
                    return null;
                }

                var10 = var13.getVariableName();
                String var11 = var13.getKeyName();
                if (StringUtils.isNotBlank(var11)) {
                    Object var12 = Utils.getObjectProperty(var9, var11);
                    if (var12 == null && var10 != null) {
                        throw new RuleException("参数中定义的对象[" + var13.getKeyLabel() + "]不存在！");
                    }

                    var4 = Utils.getObjectProperty(var12, var10);
                } else {
                    var4 = Utils.getObjectProperty(var9, var10);
                }
            } else if (var5.equals(ValueType.Method)) {
                MethodValue var14 = (MethodValue)var1;
                ExecuteMethodAction var16 = new ExecuteMethodAction();
                var16.setBeanId(var14.getBeanId());
                var16.setBeanLabel(var14.getBeanLabel());
                var16.setMethodName(var14.getMethodName());
                var16.setMethodLabel(var14.getMethodLabel());
                var16.setParameters(var14.getParameters());
                ActionValue var21 = var16.execute(var2, var3);
                if (var21 != null) {
                    var4 = var21.getValue();
                } else {
                    var4 = null;
                }
            } else if (var5.equals(ValueType.CommonFunction)) {
                CommonFunctionValue var15 = (CommonFunctionValue)var1;
                CommonFunctionParameter var18 = var15.getParameter();
                Value var24 = var18.getObjectParameter();
                var9 = this.complexValueCompute(var24, var2, var3);
                FunctionDescriptor var25 = Utils.findFunctionDescriptor(var15.getName());
                Argument var26 = var25.getArgument();
                String var27 = null;
                if (var26.isNeedProperty()) {
                    var27 = var18.getProperty();
                }

                var4 = var25.doFunction(var9, var27, var2.getWorkingMemory());
            } else if (var5.equals(ValueType.Paren)) {
                ParenValue var17 = (ParenValue)var1;
                var4 = this.a(var17.getValue(), var2, var3);
            } else if (var5.equals(ValueType.Math)) {
                MathValue var19 = (MathValue)var1;
                var4 = var19.getMathSign().calculate(var2, var3);
            } else {
                if (var5.equals(ValueType.SignI)) {
                    return var2.getWorkingMemory().getParameter("__math_sigma_step_index_");
                }

                if (var5.equals(ValueType.Object)) {
                    ObjectValue var20 = (ObjectValue)var1;
                    var4 = var20.getObject();
                } else {
                    VariableValue var22 = (VariableValue)var1;
                    var7 = var22.getVariableCategory();
                    var8 = var2.getVariableCategoryClass(var7);
                    var9 = this.findObject(var8, var3, var2);
                    if (var9 == null) {
                        a.warning("Object [" + var7 + "] not exist.");
                        return null;
                    }

                    var10 = var22.getVariableName();
                    var4 = Utils.getObjectProperty(var9, var10);
                }
            }
        }

        return var4;
    }

    private String a(String var1) {
        if (var1.startsWith("${") && var1.endsWith("}")) {
            String var2 = var1.substring(2, var1.length() - 1);
            return PropertyConfigurer.getProperty(var2);
        } else {
            return var1;
        }
    }

    public Object findObject(String var1, Map<String, Object> var2, Context var3) {
        if (var2.containsKey(var1)) {
            return var2.get(var1);
        } else {
            Map var4 = var3.getWorkingMemory().getAllFactsMap();
            return var4.get(var1);
        }
    }
}
