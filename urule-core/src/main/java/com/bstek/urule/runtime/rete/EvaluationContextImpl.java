//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.runtime.WorkingMemory;
import java.util.Map;

public class EvaluationContextImpl extends ContextImpl implements EvaluationContext {
    private int a = 0;

    public EvaluationContextImpl(WorkingMemory var1, Map<String, String> var2) {
        super(var1, var2);
    }

    public Integer nextToken() {
        return this.a++;
    }

    public void reset() {
        this.a = 0;
    }
}
