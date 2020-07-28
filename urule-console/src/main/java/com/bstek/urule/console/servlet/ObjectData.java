//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet;

import java.util.HashMap;
import java.util.Map;

public class ObjectData {
    private Map<String, Object> a = new HashMap();
    private long b = System.currentTimeMillis();

    ObjectData() {
    }

    public Object getObject(String var1) {
        return this.a.get(var1);
    }

    public Object removeObject(String var1) {
        return this.a.remove(var1);
    }

    public void putObject(String var1, Object var2) {
        this.a.put(var1, var2);
    }

    public boolean overdue() {
        long var1 = System.currentTimeMillis();
        long var3 = (var1 - this.b) / 1000L;
        return var3 >= 1200L;
    }
}
