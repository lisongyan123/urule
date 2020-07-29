//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.math;

import com.bstek.urule.runtime.rete.Context;
import java.util.Map;

public interface MathSign {
    String SIGMA_STEP_INDEX = "__math_sigma_step_index_";

    String getId();

    MathType getType();

    Object calculate(Context var1, Map<String, Object> var2);
}
