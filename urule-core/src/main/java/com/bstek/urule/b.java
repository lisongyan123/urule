//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule;

import java.util.HashMap;
import java.util.Map;

public class ContextHolder {
    private static final ThreadLocal<Map<String, Object>> a = new ThreadLocal();

    public ContextHolder() {
    }

    public static Object getData(String var0) {
        Map var1 = (Map)a.get();
        return var1 == null ? null : var1.get(var0);
    }

    public static void putData(String var0, Object var1) {
        Object var2 = (Map)a.get();
        if (var2 == null) {
            var2 = new HashMap();
            a.set(var2);
        }

        ((Map)var2).put(var0, var1);
    }

    public static void clean() {
        a.remove();
    }
}
