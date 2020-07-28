//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.console;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DebugMessageHolder {
    private int a = 0;
    private Map<String, DebugMessage> b = new ConcurrentHashMap();

    public DebugMessageHolder() {
    }

    public String getDebugMessage(String var1) {
        DebugMessage var2 = (DebugMessage)this.b.get(var1);
        String var3 = null;
        if (var2 == null) {
            var3 = "<h2 style='color:red'>Key为[" + var1 + "]的调试信息已失效，不能查看，当前key值为:" + this.a + "。<br>调试消息有效期为2分钟，请在有效期内查看!</h2>";
        } else {
            var3 = var2.getMessage();
        }

        this.a();
        return var3;
    }

    public void putDebugMessage(String var1, String var2) {
        this.a();
        DebugMessage var3 = new DebugMessage(var2);
        this.b.put(var1, var3);
    }

    private void a() {
        long var1 = System.currentTimeMillis();
        ArrayList var3 = new ArrayList();
        Iterator var4 = this.b.keySet().iterator();

        String var5;
        while(var4.hasNext()) {
            var5 = (String)var4.next();
            DebugMessage var6 = (DebugMessage)this.b.get(var5);
            long var7 = var1 - var6.getTimestamp();
            if (var7 > 120000L) {
                var3.add(var5);
            }
        }

        var4 = var3.iterator();

        while(var4.hasNext()) {
            var5 = (String)var4.next();
            this.b.remove(var5);
        }

    }

    public synchronized String generateKey() {
        ++this.a;
        return String.valueOf(this.a);
    }
}
