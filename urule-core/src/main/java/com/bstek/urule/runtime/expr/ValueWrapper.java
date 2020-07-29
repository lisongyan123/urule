//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.expr;

import com.bstek.urule.Utils;
import java.math.BigDecimal;

public class ValueWrapper {
    private BigDecimal a;
    private Object b;

    public ValueWrapper(Object var1) {
        this.b = var1;
    }

    public ValueWrapper(Object var1, BigDecimal var2) {
        this.b = var1;
        this.a = var2;
    }

    public BigDecimal getBigDecimalValue() {
        if (this.a != null) {
            return this.a;
        } else {
            if (this.b != null && !this.b.equals("")) {
                try {
                    this.a = Utils.toBigDecimal(this.b);
                } catch (Exception var2) {
                }
            }

            if (Utils.isSpaceToZero() && this.b != null && this.b.equals("")) {
                this.a = BigDecimal.valueOf(0L);
            }

            return this.a;
        }
    }

    public Object getValue() {
        return this.a != null ? this.a : this.b;
    }

    public Object getOriginalValue() {
        return this.b;
    }

    public String originalValueToString() {
        if (this.b == null) {
            return "null";
        } else if (this.b instanceof BigDecimal) {
            BigDecimal var1 = (BigDecimal)this.b;
            return var1.toPlainString();
        } else {
            return this.b.toString();
        }
    }
}
