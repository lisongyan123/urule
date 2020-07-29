//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

import java.math.BigDecimal;

public class ConsoleOutputLog extends DataLog {
    public ConsoleOutputLog(Object var1) {
        if (var1 instanceof BigDecimal) {
            BigDecimal var2 = (BigDecimal)var1;
            this.msg = "☢☢☢控制台输出：" + var2.toPlainString();
        } else if (var1 instanceof Double) {
            Double var3 = (Double)var1;
            this.msg = "☢☢☢控制台输出：" + var3.toString();
        } else {
            String var4 = var1 == null ? "null" : var1.toString();
            this.msg = "☢☢☢控制台输出：" + var4;
        }

    }
}
