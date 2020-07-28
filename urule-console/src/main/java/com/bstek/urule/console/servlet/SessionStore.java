//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SessionStore {
    private Map<String, ObjectData> a = new HashMap();
    private static final SessionStore b = new SessionStore();

    public SessionStore() {
    }

    public static void setAttribute(String var0, Object var1) {
        b.a(var0, var1);
    }

    public static void removeAttribute(String var0) {
        b.b(var0);
    }

    public static Object getAttribute(String var0) {
        return b.a(var0);
    }

    private synchronized void a(String var1, Object var2) {
        this.a();
        String var3 = "temp";
        ObjectData var4 = null;
        if (this.a.containsKey(var3)) {
            var4 = (ObjectData)this.a.get(var3);
        } else {
            var4 = new ObjectData();
            this.a.put(var3, var4);
        }

        var4.putObject(var1, var2);
    }

    private synchronized Object a(String var1) {
        this.a();
        String var2 = "temp";
        if (this.a.containsKey(var2)) {
            ObjectData var3 = (ObjectData)this.a.get(var2);
            return var3.getObject(var1);
        } else {
            return null;
        }
    }

    private synchronized void b(String var1) {
        this.a();
        String var2 = "temp";
        if (this.a.containsKey(var2)) {
            ObjectData var3 = (ObjectData)this.a.get(var2);
            var3.removeObject(var1);
        }

    }

    private void a() {
        ArrayList var1 = new ArrayList();
        Iterator var2 = this.a.keySet().iterator();

        String var3;
        while(var2.hasNext()) {
            var3 = (String)var2.next();
            ObjectData var4 = (ObjectData)this.a.get(var3);
            if (var4.overdue()) {
                var1.add(var3);
            }
        }

        var2 = var1.iterator();

        while(var2.hasNext()) {
            var3 = (String)var2.next();
            this.a.remove(var3);
        }

    }
}
