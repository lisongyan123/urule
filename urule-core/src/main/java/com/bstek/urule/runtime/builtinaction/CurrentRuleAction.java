//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.builtinaction;

import com.bstek.urule.action.WorkingMemoryHolder;
import com.bstek.urule.model.library.action.annotation.ActionBean;
import com.bstek.urule.model.library.action.annotation.ActionMethod;
import com.bstek.urule.model.library.action.annotation.ActionMethodParameter;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.runtime.WorkingMemory;
import com.bstek.urule.runtime.rete.ExecutionContext;
import java.util.Set;

@ActionBean(
        name = "当前规则"
)
public class CurrentRuleAction {
    public CurrentRuleAction() {
    }

    @ActionMethod(
            name = "当前规则对象"
    )
    @ActionMethodParameter(
            names = {}
    )
    public Rule getCurrentRule() {
        WorkingMemory var1 = WorkingMemoryHolder.getCurrentWorkingMemory();
        ExecutionContext var2 = (ExecutionContext)var1.getContext();
        return var2.getCurrentRule();
    }

    @ActionMethod(
            name = "当前规则名"
    )
    @ActionMethodParameter(
            names = {}
    )
    public String getCurrentRuleName() {
        WorkingMemory var1 = WorkingMemoryHolder.getCurrentWorkingMemory();
        ExecutionContext var2 = (ExecutionContext)var1.getContext();
        Rule var3 = var2.getCurrentRule();
        return var3 == null ? null : var3.getName();
    }

    @ActionMethod(
            name = "当前规则匹配的条件"
    )
    @ActionMethodParameter(
            names = {}
    )
    public Set<Criteria> getCurrentRuleCriterias() {
        WorkingMemory var1 = WorkingMemoryHolder.getCurrentWorkingMemory();
        ExecutionContext var2 = (ExecutionContext)var1.getContext();
        Set var3 = var2.getCurrentRuleCriterias();
        return var3;
    }
}
