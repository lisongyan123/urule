//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.agenda;

import com.bstek.urule.Utils;
import com.bstek.urule.action.Action;
import com.bstek.urule.action.ActionType;
import com.bstek.urule.exception.RuleAssertException;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.loop.LoopRule;
import com.bstek.urule.model.scorecard.runtime.ScoreRule;
import com.bstek.urule.runtime.rete.Context;
import com.bstek.urule.runtime.rete.ExecutionContextImpl;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActivationImpl implements Activation {
    private Rule a;
    private Set<Criteria> b;
    private Map<String, Object> c;

    public ActivationImpl(Rule var1) {
        this.a = var1;
    }

    public void execute(Context var1) {
        try {
            if (this.a.getDebug() != null && this.a.getDebug()) {
                var1.getLogger().logExecuteRule(this.a);
            }

            var1.cleanTipMsg();
            var1.addTipMsg("执行规则[" + this.a.getName() + "(" + this.a.getFile() + ")]动作");
            ((ExecutionContextImpl)var1).setCurrentRule(this.a);
            Date var2 = new Date();
            Date var7 = this.a.getEffectiveDate();
            if (var7 == null || var7.compareTo(var2) <= 0) {
                Date var4 = this.a.getExpiresDate();
                if (var4 == null || var4.compareTo(var2) >= 0) {
                    ((ExecutionContextImpl)var1).setCurrentRuleFactMap(this.c);
                    ((ExecutionContextImpl)var1).setCurrentRuleCriterias(this.b);
                    if (this.a instanceof LoopRule) {
                        LoopRule var5 = (LoopRule)this.a;
                        var5.execute(var1, this.c);
                    } else if (this.a instanceof ScoreRule) {
                        ScoreRule var8 = (ScoreRule)this.a;
                        var8.execute(var1, this.c);
                    } else {
                        this.a(var1, this.c);
                    }

                    var1.cleanTipMsg();
                }
            }
        } catch (Exception var6) {
            String var3 = var1.getTipMsg();
            throw new RuleAssertException(var3, var6);
        }
    }

    private void a(Context var1, Map<String, Object> var2) {
        Rhs var3 = this.a.getRhs();
        if (var3 != null) {
            List var4 = var3.getActions();
            if (var4 != null) {
                int var5 = 1;

                for(Iterator var6 = var4.iterator(); var6.hasNext(); ++var5) {
                    Action var7 = (Action)var6.next();
                    if (this.a.getDebug() != null) {
                        var7.setDebug(this.a.getDebug());
                    }

                    var1.addTipMsg("动作" + var5 + "." + this.a(var7.getActionType()) + "");
                    var7.execute(var1, var2);
                }

            }
        }
    }

    private String a(ActionType var1) {
        String var2 = "未知";
        switch(var1) {
            case ConsolePrint:
                var2 = "控制台输出";
                break;
            case ExecuteCommonFunction:
                var2 = "执行函数";
                break;
            case ExecuteMethod:
                var2 = "执行方法";
                break;
            case Scoring:
                var2 = "评分卡得分计算";
                break;
            case VariableAssign:
                var2 = "变量赋值";
                break;
            case TemplateAction:
                throw new RuleException("Unsupport action type:" + ActionType.TemplateAction);
        }

        return var2;
    }

    public Rule convertToElseRule() {
        this.a = Utils.buildElseRule(this.a);
        return this.a;
    }

    public void setCriterias(Set<Criteria> var1) {
        this.b = var1;
    }

    public void setFactMap(Map<String, Object> var1) {
        this.c = var1;
    }

    public Rule getRule() {
        return this.a;
    }

    public void setRule(Rule var1) {
        this.a = var1;
    }

    public int compareTo(Activation var1) {
        Integer var2 = var1.getRule().getSalience();
        Integer var3 = this.a.getSalience();
        if (var2 != null && var3 != null) {
            return var2 - var3;
        } else if (var2 != null) {
            return 1;
        } else {
            return var3 != null ? -1 : 0;
        }
    }
}
