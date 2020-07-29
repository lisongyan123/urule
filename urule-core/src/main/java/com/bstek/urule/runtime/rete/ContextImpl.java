//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.Utils;
import com.bstek.urule.model.rete.RuleData;
import com.bstek.urule.runtime.WorkingMemory;
import com.bstek.urule.runtime.assertor.AssertorEvaluator;
import com.bstek.urule.runtime.log.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

public class ContextImpl extends ExecutionContextImpl implements Context, ExecutionContext {
    private ValueCompute valueCompute;
    private WorkingMemory workingMemory;
    private List<RuleData> ruleDataList;
    private AssertorEvaluator assertorEvaluator;
    private ApplicationContext applicationContext;
    private Map<String, String> variableCategoryMap;
    private StringBuilder tipMsgBuilder = new StringBuilder();

    public ContextImpl(WorkingMemory var1, Map<String, String> var2) {
        this.workingMemory = var1;
        this.applicationContext = Utils.getApplicationContext();
        this.assertorEvaluator = (AssertorEvaluator)this.applicationContext.getBean("urule.assertorEvaluator");
        this.variableCategoryMap = var2;
        this.valueCompute = (ValueCompute)this.applicationContext.getBean("urule.valueCompute");
        this.ruleDataList = var1.getLogManager().getRuleData();
    }

    public void addTipMsg(String var1) {
        if (this.tipMsgBuilder.length() > 0) {
            this.tipMsgBuilder.append(">>");
        }

        this.tipMsgBuilder.append(var1);
    }

    public void cleanTipMsg() {
        this.tipMsgBuilder.delete(0, this.tipMsgBuilder.length());
    }

    public String getTipMsg() {
        return this.tipMsgBuilder.length() > 0 ? this.tipMsgBuilder.toString() : null;
    }

    public AssertorEvaluator getAssertorEvaluator() {
        return this.assertorEvaluator;
    }

    public Logger getLogger() {
        return this.getWorkingMemory().getLogManager().getLogger();
    }

    public String getVariableCategoryClass(String var1) {
        String var2 = (String)this.variableCategoryMap.get(var1);
        if (StringUtils.isEmpty(var2)) {
            var2 = HashMap.class.getName();
        }

        return var2;
    }

    public void addRuleData(List<RuleData> var1) {
        this.ruleDataList.addAll(var1);
    }

    public ValueCompute getValueCompute() {
        return this.valueCompute;
    }

    public WorkingMemory getWorkingMemory() {
        return this.workingMemory;
    }

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }
}
