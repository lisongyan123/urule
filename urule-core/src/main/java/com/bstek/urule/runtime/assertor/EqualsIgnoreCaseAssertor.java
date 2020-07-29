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

public class EqualsIgnoreCaseAssertor implements Assertor {
    public EqualsIgnoreCaseAssertor() {
    }

    public boolean eval(Object var1, Object var2, Datatype var3) {
        if (var1 == null && var2 == null) {
            return true;
        } else if (var1 != null && var2 != null) {
            BigDecimal var4 = null;
            BigDecimal var5 = null;
            switch(var3) {
                case String:
                    return var1.toString().equalsIgnoreCase(var2.toString());
                case Boolean:
                    return var1.toString().equals(var2.toString());
                case Date:
                    Date var6 = (Date)var3.convert(var1);
                    Date var7 = (Date)var3.convert(var2);
                    Calendar var8 = Calendar.getInstance();
                    var8.setTime(var6);
                    Calendar var9 = Calendar.getInstance();
                    var9.setTime(var7);
                    return var8.compareTo(var9) == 0;
                case Double:
                    var4 = Utils.toBigDecimal(var1);
                    var5 = Utils.toBigDecimal(var2);
                    return var4.compareTo(var5) == 0;
                case Float:
                    var4 = Utils.toBigDecimal(var1);
                    var5 = Utils.toBigDecimal(var2);
                    return var4.compareTo(var5) == 0;
                case Integer:
                    var4 = Utils.toBigDecimal(var1);
                    var5 = Utils.toBigDecimal(var2);
                    return var4.compareTo(var5) == 0;
                case Long:
                    var4 = Utils.toBigDecimal(var1);
                    var5 = Utils.toBigDecimal(var2);
                    return var4.compareTo(var5) == 0;
                case BigDecimal:
                    var4 = Utils.toBigDecimal(var1);
                    var5 = Utils.toBigDecimal(var2);
                    return var4.compareTo(var5) == 0;
                case Enum:
                    Enum var10 = (Enum)var1;
                    Enum var11;
                    if (var2 instanceof Enum) {
                        var11 = (Enum)var2;
                        return var10.equals(var11);
                    }

                    var11 = Enum.valueOf(var10.getClass(), var2.toString());
                    return var10.equals(var11);
                default:
                    return var2.toString().equalsIgnoreCase(var1.toString());
            }
        } else {
            return false;
        }
    }

    public Op supportOp() {
        return Op.EqualsIgnoreCase;
    }
}
