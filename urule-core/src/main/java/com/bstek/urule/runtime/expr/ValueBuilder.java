//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.expr;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.ArithmeticType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class ValueBuilder {
    private Stack<Object> a = new Stack();

    public ValueBuilder() {
    }

    public ValueWrapper build() {
        ValueWrapper var1 = (ValueWrapper)this.a.firstElement();
        this.a.removeElementAt(0);

        while(!this.a.isEmpty()) {
            ArithmeticType var2 = (ArithmeticType)this.a.firstElement();
            this.a.removeElementAt(0);
            if (this.a.isEmpty()) {
                throw new RuleException("表达式不合法！");
            }

            ValueWrapper var3 = (ValueWrapper)this.a.firstElement();
            this.a.removeElementAt(0);
            var1 = this.a(var1, var3, var2);
        }

        return var1;
    }

    private ValueWrapper a(ValueWrapper var1, ValueWrapper var2, ArithmeticType var3) {
        BigDecimal var4 = var1.getBigDecimalValue();
        BigDecimal var5 = var2.getBigDecimalValue();
        if (!var3.equals(ArithmeticType.Add) || var4 != null && var5 != null) {
            boolean var17;
            if (var3.equals(ArithmeticType.Eq) && (var4 == null || var5 == null)) {
                var17 = var1.originalValueToString().equals(var2.originalValueToString());
                return new ValueWrapper(var17, (BigDecimal)null);
            } else if (!var3.equals(ArithmeticType.NotEq) || var4 != null && var5 != null) {
                if (var4 == null) {
                    throw new RuleException("表达式 [" + var1.getOriginalValue() + "] 不能转换为数字!");
                } else if (var5 == null) {
                    throw new RuleException("表达式 [" + var2.getOriginalValue() + "] 不能转换为数字!");
                } else {
                    switch(var3) {
                        case Add:
                            BigDecimal var18 = var4.add(var5).stripTrailingZeros();
                            return new ValueWrapper(var18, var18);
                        case Div:
                            BigDecimal var7 = var4.divide(var5, 32, RoundingMode.HALF_UP).stripTrailingZeros();
                            return new ValueWrapper(var7, var7);
                        case Mod:
                            BigDecimal var8 = var4.divideAndRemainder(var5)[1].stripTrailingZeros();
                            return new ValueWrapper(var8, var8);
                        case Mul:
                            BigDecimal var9 = var4.multiply(var5).stripTrailingZeros();
                            return new ValueWrapper(var9, var9);
                        case Sub:
                            BigDecimal var10 = var4.subtract(var5).stripTrailingZeros();
                            return new ValueWrapper(var10, var10);
                        case Eq:
                            boolean var11 = this.e(var4, var5);
                            return new ValueWrapper(var11);
                        case NotEq:
                            boolean var12 = this.e(var4, var5);
                            return new ValueWrapper(!var12);
                        case Gt:
                            boolean var13 = this.b(var4, var5);
                            return new ValueWrapper(var13);
                        case Gte:
                            boolean var14 = this.a(var4, var5);
                            return new ValueWrapper(var14);
                        case Lt:
                            boolean var15 = this.d(var4, var5);
                            return new ValueWrapper(var15);
                        case Lte:
                            boolean var16 = this.c(var4, var5);
                            return new ValueWrapper(var16);
                        default:
                            throw new RuleException("Unknow operator " + var3);
                    }
                }
            } else {
                var17 = var1.originalValueToString().equals(var2.originalValueToString());
                return new ValueWrapper(!var17, (BigDecimal)null);
            }
        } else {
            String var6 = var1.originalValueToString() + var2.originalValueToString();
            return new ValueWrapper(var6, (BigDecimal)null);
        }
    }

    private boolean a(Object var1, Object var2) {
        if (var1 == null && var2 == null) {
            return true;
        } else if (var1 == null && var2 != null) {
            return false;
        } else if (var1 != null && var2 == null) {
            return false;
        } else {
            BigDecimal var3 = Utils.toBigDecimal(var1);
            BigDecimal var4 = Utils.toBigDecimal(var2);
            return var3.compareTo(var4) > -1;
        }
    }

    private boolean b(Object var1, Object var2) {
        if (var1 == null && var2 == null) {
            return true;
        } else if (var1 == null && var2 != null) {
            return false;
        } else if (var1 != null && var2 == null) {
            return false;
        } else {
            BigDecimal var3 = Utils.toBigDecimal(var1);
            BigDecimal var4 = Utils.toBigDecimal(var2);
            return var3.compareTo(var4) == 1;
        }
    }

    private boolean c(Object var1, Object var2) {
        if (var1 == null && var2 == null) {
            return true;
        } else if (var1 == null && var2 != null) {
            return false;
        } else if (var1 != null && var2 == null) {
            return false;
        } else {
            BigDecimal var3 = Utils.toBigDecimal(var1);
            BigDecimal var4 = Utils.toBigDecimal(var2);
            return var3.compareTo(var4) < 1;
        }
    }

    private boolean d(Object var1, Object var2) {
        if (var1 == null && var2 == null) {
            return true;
        } else if (var1 == null && var2 != null) {
            return false;
        } else if (var1 != null && var2 == null) {
            return false;
        } else {
            BigDecimal var3 = Utils.toBigDecimal(var1);
            BigDecimal var4 = Utils.toBigDecimal(var2);
            return var3.compareTo(var4) == -1;
        }
    }

    private boolean e(Object var1, Object var2) {
        if (var1 == null && var2 == null) {
            return true;
        } else if (var1 == null && var2 != null) {
            return false;
        } else if (var1 != null && var2 == null) {
            return false;
        } else {
            try {
                BigDecimal var3 = Utils.toBigDecimal(var1);
                BigDecimal var4 = Utils.toBigDecimal(var2);
                return var3.compareTo(var4) == 0;
            } catch (Exception var5) {
                return var1.toString().equals(var2.toString());
            }
        }
    }

    public void addValue(Object var1) {
        if (!this.a.isEmpty()) {
            Object var2 = this.a.peek();
            if (var2 instanceof ValueWrapper) {
                if (var1 instanceof ValueWrapper) {
                    throw new RuleException("表达式不合法！");
                }
            } else {
                if (var1 instanceof ArithmeticType) {
                    throw new RuleException("表达式不合法！");
                }

                ArithmeticType var3 = (ArithmeticType)var2;
                if (!var3.equals(ArithmeticType.Add) && !var3.equals(ArithmeticType.Sub)) {
                    this.a.pop();
                    ValueWrapper var4 = (ValueWrapper)this.a.pop();
                    ValueWrapper var5 = (ValueWrapper)var1;
                    var1 = this.a(var4, var5, var3);
                } else if (this.a.size() > 2) {
                }
            }
        } else if (var1 instanceof ArithmeticType) {
            throw new RuleException("表达式不合法！");
        }

        this.a.push(var1);
    }
}
