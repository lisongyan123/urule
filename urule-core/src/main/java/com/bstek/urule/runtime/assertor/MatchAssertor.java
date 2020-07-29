//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.assertor;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchAssertor implements Assertor {
    public MatchAssertor() {
    }

    public boolean eval(Object var1, Object var2, Datatype var3) {
        if (var1 != null && var2 != null) {
            Pattern var4 = Pattern.compile(var2.toString());
            Matcher var5 = var4.matcher(var1.toString());
            return var5.matches();
        } else {
            return false;
        }
    }

    public Op supportOp() {
        return Op.Match;
    }
}
