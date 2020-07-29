//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.agenda;

import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.runtime.rete.Context;

public interface Activation extends Comparable<Activation> {
    Rule getRule();

    Rule convertToElseRule();

    void execute(Context var1);
}
