//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.rete;

import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.rete.ObjectTypeNode;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.lhs.BaseCriteria;
import java.util.List;

public interface BuildContext {
    List<String> getObjectType(BaseCriteria var1);

    boolean assertSameType(BaseCriteria var1, BaseCriteria var2);

    ResourceLibrary getResourceLibrary();

    ObjectTypeNode buildObjectTypeNode(String var1);

    int nextId();

    void setCurrentRule(Rule var1);

    Rule currentRule();

    boolean currentRuleIsDebug();

    IdGenerator getIdGenerator();
}
