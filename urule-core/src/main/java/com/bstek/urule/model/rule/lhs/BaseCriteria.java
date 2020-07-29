//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.runtime.rete.EvaluationContext;
import java.util.Map;

public interface BaseCriteria {
    EvaluateResponse evaluate(EvaluationContext var1, Map<String, Object> var2);

    String getId();
}
