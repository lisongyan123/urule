//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.model.rete.RuleData;
import com.bstek.urule.runtime.WorkingMemory;
import com.bstek.urule.runtime.assertor.AssertorEvaluator;
import com.bstek.urule.runtime.log.Logger;
import java.util.List;
import org.springframework.context.ApplicationContext;

public interface Context {
    String getTipMsg();

    void cleanTipMsg();

    Logger getLogger();

    void addTipMsg(String var1);

    ValueCompute getValueCompute();

    WorkingMemory getWorkingMemory();

    AssertorEvaluator getAssertorEvaluator();

    void addRuleData(List<RuleData> var1);

    ApplicationContext getApplicationContext();

    String getVariableCategoryClass(String var1);
}
