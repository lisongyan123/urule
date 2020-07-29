//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.assertor;

import com.bstek.urule.Utils;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class LessThenAssertor implements Assertor {
    public LessThenAssertor() {
    }

    public boolean eval(Object var1, Object var2, Datatype var3) {
        if (var1 != null && var2 != null) {
            if (var3.equals(Datatype.Date)) {
                Date var4 = (Date)var3.convert(var1);
                Date var5 = (Date)var3.convert(var2);
                Calendar var6 = Calendar.getInstance();
                var6.setTime(var4);
                Calendar var7 = Calendar.getInstance();
                var7.setTime(var5);
                int var8 = var6.compareTo(var7);
                if (var8 == -1) {
                    return true;
                }
            } else {
                BigDecimal var9 = Utils.toBigDecimal(var1);
                BigDecimal var10 = Utils.toBigDecimal(var2);
                int var11 = var9.compareTo(var10);
                if (var11 == -1) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public Op supportOp() {
        return Op.LessThen;
    }
}
