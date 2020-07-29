//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.Utils;
import com.bstek.urule.action.ActionValue;
import com.bstek.urule.action.ExecuteMethodAction;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.ComplexArithmetic;
import com.bstek.urule.model.rule.Op;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.runtime.rete.EvaluationContext;
import com.bstek.urule.runtime.rete.ValueCompute;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Criteria extends BaseCriterion implements BaseCriteria {
    private static Logger log = Logger.getGlobal();
    @JsonIgnore
    private String id;
    private Op op;
    private Left left;
    private Value value;
    private String file;
    private Set<String> necessaryClassList = new HashSet();

    public Criteria() {
    }

    public EvaluateResponse evaluate(EvaluationContext var1, Map<String, Object> var2) {
        Datatype var3 = null;
        Object var4 = null;
        var1.cleanTipMsg();
        if (this.file != null) {
            var1.addTipMsg("计算条件(" + this.file + ")：" + this.getId());
        } else {
            var1.addTipMsg("计算条件：" + this.getId());
        }

        ValueCompute var5 = var1.getValueCompute();
        LeftPart var6 = this.left.getLeftPart();
        if (var6 instanceof AccumulateLeftPart) {
            AccumulateLeftPart var14 = (AccumulateLeftPart)var6;
            return var14.evaluate(var1, var2);
        } else {
            String var7 = this.left.getId();
            var1.addTipMsg("左值：" + var7);
            Object var8 = null;
            Object var11;
            String var12;
            if (var6 instanceof VariableLeftPart) {
                VariableLeftPart var9 = (VariableLeftPart)var6;
                String var10 = var1.getVariableCategoryClass(var9.getVariableCategory());
                var11 = var1.getValueCompute().findObject(var10, var2, var1);
                var3 = var9.getDatatype();
                if (var9.getVariableName() != null) {
                    if (var11 != null) {
                        var12 = var9.getKeyName();
                        if (StringUtils.isNotBlank(var12)) {
                            Object var13 = Utils.getObjectProperty(var11, var12);
                            if (var13 == null) {
                                throw new RuleException("[参数]中的[" + var9.getKeyLabel() + "]不存在！");
                            }

                            var8 = Utils.getObjectProperty(var13, var9.getVariableName());
                        } else {
                            var8 = Utils.getObjectProperty(var11, var9.getVariableName());
                        }
                    } else {
                        log.warning("Object [" + var10 + "] not exist.");
                        var8 = var11;
                    }
                } else {
                    var8 = var11;
                }
            } else if (var6 instanceof MethodLeftPart) {
                MethodLeftPart var15 = (MethodLeftPart)var6;
                ExecuteMethodAction var18 = new ExecuteMethodAction();
                var18.setBeanId(var15.getBeanId());
                var18.setBeanLabel(var15.getBeanLabel());
                var18.setMethodLabel(var15.getMethodLabel());
                var18.setMethodName(var15.getMethodName());
                var18.setParameters(var15.getParameters());
                ActionValue var20 = var18.execute(var1, var2);
                if (var20 == null) {
                    var8 = null;
                } else {
                    var8 = var20.getValue();
                }
            } else if (var6 instanceof CommonFunctionLeftPart) {
                CommonFunctionLeftPart var16 = (CommonFunctionLeftPart)var6;
                var8 = var16.evaluate(var1, var2);
            }

            var4 = var8;
            ComplexArithmetic var17 = this.left.getArithmetic();
            if (var17 != null) {
                var4 = var5.complexArithmeticCompute(var1, var2, var17, var8);
            }

            EvaluateResponse var19 = new EvaluateResponse();
            var19.setLeftResult(var4);
            var11 = null;
            if (this.value != null) {
                var12 = this.value.getId();
                var1.addTipMsg("右值：" + var12);
                var11 = var5.complexValueCompute(this.value, var1, var2);
                var19.setRightResult(var11);
            }

            if (var3 == null) {
                var3 = Utils.getDatatype(var4);
            }

            var1.addTipMsg("执行比较：" + this.op.toString());
            boolean var21 = var1.getAssertorEvaluator().evaluate(var4, var11, var3, this.op);
            var19.setResult(var21);
            var1.cleanTipMsg();
            return var19;
        }
    }

    public boolean necessaryClassEval(Set<String> var1) {
        Iterator var2 = this.necessaryClassList.iterator();

        String var3;
        do {
            if (!var2.hasNext()) {
                return true;
            }

            var3 = (String)var2.next();
        } while(var3.equals("*") || var1.contains(var3));

        return false;
    }

    public void addNecessaryClass(String var1) {
        this.necessaryClassList.add(var1);
    }

    public void addNecessaryClasses(List<String> var1) {
        this.necessaryClassList.addAll(var1);
    }

    public Set<String> getNecessaryClassList() {
        return this.necessaryClassList;
    }

    public String getId() {
        if (this.id == null) {
            this.id = this.left.getId();
            if (this.op != null) {
                this.id = this.id + "【" + this.op.toString() + "】";
            }

            if (this.value != null) {
                this.id = this.id + this.value.getId();
            }
        }

        return this.id;
    }

    public void setId(String var1) {
        this.id = var1;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String var1) {
        this.file = var1;
    }

    public Op getOp() {
        return this.op;
    }

    public void setOp(Op var1) {
        this.op = var1;
    }

    public Left getLeft() {
        return this.left;
    }

    public void setLeft(Left var1) {
        this.left = var1;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }
}
