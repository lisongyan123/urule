//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.assertor;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;
import java.util.Collection;
import java.util.Iterator;

public class InAssertor implements Assertor {
    public InAssertor() {
    }

    public boolean eval(Object var1, Object var2, Datatype var3) {
        if (var1 != null && var2 != null) {
            String[] var5;
            int var9;
            if (var2 instanceof Collection) {
                Collection var16 = (Collection)var2;
                boolean var19;
                if (var1 instanceof Collection) {
                    Collection var17 = (Collection)var1;
                    var19 = false;
                    Iterator var24 = var17.iterator();

                    while(true) {
                        while(var24.hasNext()) {
                            Object var23 = var24.next();

                            for(Iterator var26 = var16.iterator(); var26.hasNext(); var19 = false) {
                                Object var30 = var26.next();
                                if (var30.toString().equals(var23.toString())) {
                                    var19 = true;
                                    break;
                                }
                            }
                        }

                        return var19;
                    }
                } else {
                    var5 = var1.toString().split(",");
                    var19 = false;
                    String[] var22 = var5;
                    int var21 = var5.length;

                    for(var9 = 0; var9 < var21; ++var9) {
                        String var28 = var22[var9];

                        for(Iterator var31 = var16.iterator(); var31.hasNext(); var19 = false) {
                            Object var33 = var31.next();
                            if (var33.toString().equals(var28)) {
                                var19 = true;
                                break;
                            }
                        }
                    }

                    return var19;
                }
            } else if (!(var2 instanceof String)) {
                return false;
            } else {
                String var4 = (String)var2;
                var5 = var4.split(",");
                boolean var7;
                if (var1 instanceof Collection) {
                    Collection var18 = (Collection)var1;
                    var7 = false;
                    Iterator var20 = var18.iterator();

                    while(true) {
                        while(var20.hasNext()) {
                            Object var25 = var20.next();
                            String[] var27 = var5;
                            int var29 = var5.length;

                            for(int var32 = 0; var32 < var29; ++var32) {
                                String var34 = var27[var32];
                                if (var25.toString().equals(var34)) {
                                    var7 = true;
                                    break;
                                }

                                var7 = false;
                            }
                        }

                        return var7;
                    }
                } else {
                    String[] var6 = var1.toString().split(",");
                    var7 = false;
                    String[] var8 = var6;
                    var9 = var6.length;

                    for(int var10 = 0; var10 < var9; ++var10) {
                        String var11 = var8[var10];
                        String[] var12 = var5;
                        int var13 = var5.length;

                        for(int var14 = 0; var14 < var13; ++var14) {
                            String var15 = var12[var14];
                            if (var15.equals(var11)) {
                                var7 = true;
                                break;
                            }

                            var7 = false;
                        }
                    }

                    return var7;
                }
            }
        } else {
            return false;
        }
    }

    public Op supportOp() {
        return Op.In;
    }
}
