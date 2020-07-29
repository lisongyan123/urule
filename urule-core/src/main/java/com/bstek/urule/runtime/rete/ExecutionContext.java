//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.lhs.Criteria;
import java.util.Map;
import java.util.Set;

public interface ExecutionContext {
    Rule getCurrentRule();

    Set<Criteria> getCurrentRuleCriterias();

    Map<String, Object> getCurrentRuleFactMap();
}
