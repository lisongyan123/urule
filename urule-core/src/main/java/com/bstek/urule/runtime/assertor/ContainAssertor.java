//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.assertor;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;
import java.util.Collection;

public class ContainAssertor implements Assertor {
    public ContainAssertor() {
    }

    public boolean eval(Object var1, Object var2, Datatype var3) {
        if (var1 != null && var2 != null) {
            if (var1 instanceof String) {
                return var1.toString().contains(var2.toString());
            } else if (var1 instanceof Collection) {
                Collection var7 = (Collection)var1;
                if (var2 instanceof Collection) {
                    Collection var6 = (Collection)var2;
                    return var7.containsAll(var6);
                } else {
                    return var7.contains(var2);
                }
            } else {
                String var4 = var1.toString();
                String var5 = var2.toString();
                return var4.contains(var5);
            }
        } else {
            return false;
        }
    }

    public Op supportOp() {
        return Op.Contain;
    }
}
