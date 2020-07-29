//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.Utils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class ElCompute {
    private Stack<Object> a = new Stack();
    private Stack<Character> b = new Stack();

    public ElCompute() {
    }

    public static void main(String[] var0) {
        long var1 = System.currentTimeMillis();
        String var3 = "abc-0001/+123";

        for(int var4 = 0; var4 < 1; ++var4) {
            ElCompute var5 = new ElCompute();
            Object var6 = var5.doCompute(var3);
            System.out.println(var6);
        }

        long var7 = System.currentTimeMillis();
        System.out.println(var7 - var1);
    }

    public Object doCompute(String var1) {
        this.a(var1);
        return this.a.pop();
    }

    private void a(String var1) {
        StringBuilder var2 = new StringBuilder();
        byte var3 = 32;
        char var4 = ' ';

        for(int var5 = 0; var5 < var1.length(); ++var5) {
            char var6 = var1.charAt(var5);
            if (var4 == '\\') {
                var2.append(var6);
                var4 = var6;
            } else if (var3 == 34) {
                if (var6 == '"') {
                    var3 = 32;
                    this.a.push(var2.toString());
                    var2.setLength(0);
                } else {
                    var2.append(var6);
                }

                var4 = var6;
            } else {
                switch(var6) {
                    case ' ':
                        if (var3 == 34) {
                            var2.append(var6);
                        }
                        break;
                    case '!':
                    case '#':
                    case '$':
                    case '&':
                    case '\'':
                    case ',':
                    case '.':
                    default:
                        var2.append(var6);
                        break;
                    case '"':
                        if (var3 == 34) {
                            var3 = 32;
                            this.a.push(var2.toString());
                            var2.setLength(0);
                        } else {
                            var3 = 34;
                        }
                        break;
                    case '%':
                        this.a(var2, var6, var4);
                        break;
                    case '(':
                        this.b.push(var6);
                        break;
                    case ')':
                        this.a(var2);
                        this.a(1);
                        break;
                    case '*':
                        this.a(var2, var6, var4);
                        break;
                    case '+':
                        this.a(var2, var6, var4);
                        break;
                    case '-':
                        this.a(var2, var6, var4);
                        break;
                    case '/':
                        this.a(var2, var6, var4);
                }

                var4 = var6;
            }
        }

        if (var2.length() > 0) {
            this.a(var2);
        }

        this.a(0);
    }

    private void a(StringBuilder var1, char var2, char var3) {
        if (var1.length() == 0 && var3 != ')' && var3 != '"') {
            var1.append(var2);
        } else {
            this.a(var1);
            if (var2 != '+' && var2 != '-') {
                this.a(2);
            } else {
                this.a(0);
            }

            this.b.push(var2);
        }

    }

    private void a(int var1) {
        if (!this.b.empty()) {
            char var2 = (Character)this.b.peek();
            if (var2 == '(') {
                this.b.pop();
            } else {
                Object var4;
                Object var6;
                if (var1 != 0 && var1 != 1) {
                    if (var1 == 2) {
                        while(var2 == '*' || var2 == '/' || var2 == '%') {
                            Object var7 = this.a.pop();
                            var4 = this.a.pop();
                            char var8 = (Character)this.b.pop();
                            var6 = this.a(var4, var8, var7);
                            this.a.push(var6);
                            if (this.b.isEmpty()) {
                                break;
                            }

                            var2 = (Character)this.b.peek();
                            if (var2 == '(') {
                                break;
                            }
                        }
                    }
                } else {
                    char var3 = (Character)this.b.pop();

                    do {
                        var4 = null;
                        if (this.a.isEmpty()) {
                            var4 = "";
                        } else {
                            var4 = this.a.pop();
                        }

                        Object var5 = null;
                        if (this.a.isEmpty()) {
                            var5 = "";
                        } else {
                            var5 = this.a.pop();
                        }

                        var6 = this.a(var5, var3, var4);
                        this.a.push(var6);
                        if (this.b.isEmpty()) {
                            break;
                        }

                        var3 = (Character)this.b.pop();
                    } while(var3 != '(');
                }

            }
        }
    }

    private Object a(Object var1, char var2, Object var3) {
        String var4;
        BigDecimal var5;
        BigDecimal var6;
        if (var2 != '*' && var2 != '/' && var2 != '%' && var2 != '-') {
            if (var2 == '+') {
                var4 = this.b(var1, var2, var3);
                if (var4 != null) {
                    return var4;
                }

                var5 = null;
                if (var1 instanceof ElCompute.DataWrapper) {
                    var5 = ((ElCompute.DataWrapper)var1).c;
                } else {
                    var5 = Utils.toBigDecimal(var1);
                }

                var6 = null;
                if (var3 instanceof ElCompute.DataWrapper) {
                    var6 = ((ElCompute.DataWrapper)var3).c;
                } else {
                    var6 = Utils.toBigDecimal(var3);
                }

                return var5.add(var6);
            }
        } else {
            var4 = this.b(var1, var2, var3);
            if (var4 != null) {
                return var4;
            }

            var5 = null;
            if (var1 instanceof ElCompute.DataWrapper) {
                var5 = ((ElCompute.DataWrapper)var1).c;
            } else {
                var5 = Utils.toBigDecimal(var1);
            }

            var6 = null;
            if (var3 instanceof ElCompute.DataWrapper) {
                var6 = ((ElCompute.DataWrapper)var3).c;
            } else {
                var6 = Utils.toBigDecimal(var3);
            }

            if (var2 == '*') {
                return var5.multiply(var6);
            }

            if (var2 == '/') {
                return var5.divide(var6, 10, RoundingMode.HALF_UP).stripTrailingZeros();
            }

            if (var2 == '%') {
                return var5.divideAndRemainder(var6)[1];
            }

            if (var2 == '-') {
                return var5.subtract(var6);
            }
        }

        throw new RuntimeException("Unkown operate " + var2 + "");
    }

    private String b(Object var1, char var2, Object var3) {
        BigDecimal var4;
        ElCompute.DataWrapper var8;
        if (var3 instanceof String) {
            if (var1 instanceof ElCompute.DataWrapper) {
                var8 = (ElCompute.DataWrapper)var1;
                return var8.b + var3.toString();
            } else if (var1 instanceof BigDecimal) {
                var4 = (BigDecimal)var1;

                try {
                    return var4.toBigIntegerExact() + var3.toString();
                } catch (ArithmeticException var6) {
                    return var1.toString() + var3.toString();
                }
            } else {
                return var1.toString() + var3.toString();
            }
        } else if (var1 instanceof String) {
            if (var3 instanceof ElCompute.DataWrapper) {
                var8 = (ElCompute.DataWrapper)var3;
                return var1.toString() + var8.b;
            } else if (var3 instanceof BigDecimal) {
                var4 = (BigDecimal)var3;

                try {
                    return var1.toString() + var4.toBigIntegerExact();
                } catch (ArithmeticException var7) {
                    return var1.toString() + var3.toString();
                }
            } else {
                return var1.toString() + var3.toString();
            }
        } else {
            return null;
        }
    }

    private void a(StringBuilder var1) {
        if (var1.length() != 0) {
            String var2 = var1.toString();
            var1.setLength(0);

            try {
                BigDecimal var3 = Utils.toBigDecimal(var2);
                this.a.push(new ElCompute.DataWrapper(var2, var3));
            } catch (Exception var4) {
                this.a.push(var2);
            }

        }
    }

    class DataWrapper {
        private String b;
        private BigDecimal c;

        public DataWrapper(String var2, BigDecimal var3) {
            this.b = var2;
            this.c = var3;
        }
    }
}
