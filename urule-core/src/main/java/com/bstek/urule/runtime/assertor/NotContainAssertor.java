//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.assertor;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;

public class NotContainAssertor extends ContainAssertor {
    public NotContainAssertor() {
    }

    public boolean eval(Object var1, Object var2, Datatype var3) {
        if (var1 != null && var2 != null) {
            return !super.eval(var1, var2, var3);
        } else {
            return false;
        }
    }

    public Op supportOp() {
        return Op.NotContain;
    }
}
