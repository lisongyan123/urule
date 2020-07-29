//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.loop.LoopTarget;
import com.bstek.urule.model.rule.loop.LoopTargetType;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.rete.Context;
import com.bstek.urule.runtime.rete.EvaluationContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AccumulateLeftPart implements LeftPart {
    private LoopTargetType loopTargetType;
    private LoopTarget loopTarget;
    private List<ConditionItem> conditionItems;
    private List<CalculateItem> calculateItems;
    private Junction junction;
    private String id;

    public AccumulateLeftPart() {
    }

    public EvaluateResponse evaluate(EvaluationContext var1, Map<String, Object> var2) {
        List var3 = this.buildLoopTarget(var1, var2);
        if (var3 == null) {
            throw new RuleException("聚合条件 [" + this.getId() + "] 循环对象为 null.");
        } else {
            HashMap var4 = new HashMap();
            var4.putAll(var2);
            HashMap var5 = new HashMap();
            Iterator var6 = var3.iterator();

            while(true) {
                Object var7;
                boolean var9;
                do {
                    if (!var6.hasNext()) {
                        var6 = var5.values().iterator();

                        while(var6.hasNext()) {
                            CalculateData var13 = (CalculateData)var6.next();
                            var13.buildValue(var1, var2);
                        }

                        EvaluateResponse var12 = null;
                        Iterator var14 = this.conditionItems.iterator();

                        while(var14.hasNext()) {
                            ConditionItem var15 = (ConditionItem)var14.next();
                            EvaluateResponse var16 = var15.eval(var5, var1, var2);
                            if (!var16.getResult()) {
                                var12 = var16;
                                break;
                            }
                        }

                        if (var12 == null) {
                            var12 = new EvaluateResponse();
                            var12.setResult(true);
                        }

                        return var12;
                    }

                    var7 = var6.next();
                    String var8 = Utils.getClassName(var7);
                    var4.put(var8, var7);
                    var9 = this.evalCondition(var1, var4);
                } while(!var9);

                Iterator var10 = this.calculateItems.iterator();

                while(var10.hasNext()) {
                    CalculateItem var11 = (CalculateItem)var10.next();
                    var11.calculate(var1, var4, var7, var5);
                }
            }
        }
    }

    private boolean evalCondition(EvaluationContext var1, Map<String, Object> var2) {
        if (this.junction == null) {
            return true;
        } else {
            List var3 = this.junction.getCriterions();
            if (var3 != null && var3.size() != 0) {
                boolean var4 = true;
                if (this.junction instanceof Or) {
                    var4 = false;
                }

                boolean var5 = false;
                Iterator var6 = var3.iterator();

                while(var6.hasNext()) {
                    Criterion var7 = (Criterion)var6.next();
                    Criteria var8 = (Criteria)var7;
                    EvaluateResponse var9 = var8.evaluate(var1, var2);
                    if (var4) {
                        if (!var9.getResult()) {
                            var5 = false;
                            break;
                        }

                        var5 = true;
                    } else {
                        if (var9.getResult()) {
                            var5 = true;
                            break;
                        }

                        var5 = false;
                    }
                }

                return var5;
            } else {
                return true;
            }
        }
    }

    private List<Object> buildLoopTarget(Context var1, Map<String, Object> var2) {
        Object var3 = var1.getValueCompute().complexValueCompute(this.loopTarget.getValue(), var1, var2);
        if (this.loopTargetType.equals(LoopTargetType.list)) {
            if (var3 instanceof Collection) {
                ArrayList var11 = new ArrayList();
                Collection var12 = (Collection)var3;
                var11.addAll(var12);
                return var11;
            } else {
                throw new RuntimeException("循环对象必须是一个Collection类型的集合对象或一个数组对象，当前对象为：" + var3 + ".");
            }
        } else {
            KnowledgeSession var4 = (KnowledgeSession)var1.getWorkingMemory();
            List var5 = var4.getFactList();
            String var6 = Utils.getClassName(var3);
            ArrayList var7 = new ArrayList();
            Iterator var8 = var5.iterator();

            while(var8.hasNext()) {
                Object var9 = var8.next();
                String var10 = Utils.getClassName(var9);
                if (var10.equals(var6)) {
                    var7.add(var9);
                }
            }

            return var7;
        }
    }

    public LoopTargetType getLoopTargetType() {
        return this.loopTargetType;
    }

    public void setLoopTargetType(LoopTargetType var1) {
        this.loopTargetType = var1;
    }

    public LoopTarget getLoopTarget() {
        return this.loopTarget;
    }

    public void setLoopTarget(LoopTarget var1) {
        this.loopTarget = var1;
    }

    public List<ConditionItem> getConditionItems() {
        return this.conditionItems;
    }

    public void setConditionItems(List<ConditionItem> var1) {
        this.conditionItems = var1;
    }

    public List<CalculateItem> getCalculateItems() {
        return this.calculateItems;
    }

    public void setCalculateItems(List<CalculateItem> var1) {
        this.calculateItems = var1;
    }

    public Junction getJunction() {
        return this.junction;
    }

    public void setJunction(Junction var1) {
        this.junction = var1;
    }

    public String getId() {
        if (this.id != null) {
            return this.id;
        } else {
            this.id = "聚合," + this.loopTargetType.toString() + ":" + this.loopTarget.getValue().getId();
            int var1 = 0;

            Iterator var2;
            for(var2 = this.conditionItems.iterator(); var2.hasNext(); ++var1) {
                ConditionItem var3 = (ConditionItem)var2.next();
                if (var1 == 0) {
                    this.id = this.id + "；条件:";
                } else {
                    this.id = this.id + ",";
                }

                this.id = this.id + var3.getLeft() + var3.getOp() + var3.getValue().getId();
            }

            boolean var4 = false;
            var2 = this.calculateItems.iterator();

            while(var2.hasNext()) {
                CalculateItem var5 = (CalculateItem)var2.next();
                if (!var4) {
                    this.id = this.id + ";计算:";
                } else {
                    this.id = this.id + ",";
                }

                this.id = this.id + var5.getType();
                if (var5.getValue() != null) {
                    this.id = this.id + "(" + var5.getValue().getId() + ")";
                }

                if (var5.isEnableAssignment()) {
                    this.id = this.id + "赋给" + var5.getAssignTargetType() + ":" + var5.getAssignVariableCategory() + "." + var5.getAssignVariableLabel();
                    if (var5.getKeyLabel() != null) {
                        this.id = this.id + "." + var5.getKeyLabel();
                    }
                }
            }

            return this.id;
        }
    }
}
