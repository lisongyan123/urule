//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.percent;

import com.bstek.urule.model.flow.ProcessDefinition;

public interface PercentDataStore {
    String BEAN_ID = "urule.percentDataStore";

    PercentUnit getDecisionNodePercent(ProcessDefinition var1, String var2);
}
