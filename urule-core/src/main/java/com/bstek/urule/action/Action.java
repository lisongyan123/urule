//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.action;

import com.bstek.urule.runtime.rete.Context;
import java.util.Map;

public interface Action extends Comparable<Action> {
    ActionValue execute(Context var1, Map<String, Object> var2);

    ActionType getActionType();

    int getPriority();

    void setDebug(boolean var1);
}
