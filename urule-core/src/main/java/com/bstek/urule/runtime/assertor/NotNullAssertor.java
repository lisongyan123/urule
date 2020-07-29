//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.assertor;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;
import org.apache.commons.lang.StringUtils;

public class NotNullAssertor implements Assertor {
    public NotNullAssertor() {
    }

    public boolean eval(Object var1, Object var2, Datatype var3) {
        return var1 != null && StringUtils.isNotBlank(var1.toString());
    }

    public Op supportOp() {
        return Op.NotNull;
    }
}
