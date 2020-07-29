//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.assertor;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;

public class NotEndWithAssertor implements Assertor {
    public NotEndWithAssertor() {
    }

    public boolean eval(Object var1, Object var2, Datatype var3) {
        if (var1 != null && var2 != null) {
            if (var1 instanceof String && var2 instanceof String) {
                String var4 = (String)var1;
                String var5 = (String)var2;
                return !var4.endsWith(var5);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Op supportOp() {
        return Op.NotEndWith;
    }
}
