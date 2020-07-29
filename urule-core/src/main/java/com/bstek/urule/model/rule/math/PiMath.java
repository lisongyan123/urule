//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.math;

import com.bstek.urule.runtime.rete.Context;
import java.util.Map;

public class PiMath implements MathSign {
    public PiMath() {
    }

    public Object calculate(Context var1, Map<String, Object> var2) {
        return 3.141592653589793D;
    }

    public String getId() {
        return "π";
    }

    public MathType getType() {
        return MathType.pi;
    }
}
