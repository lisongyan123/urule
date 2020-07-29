//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.cache;

import com.bstek.urule.runtime.KnowledgePackage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryKnowledgeCache implements KnowledgeCache {
    private Map<String, KnowledgePackage> a = new ConcurrentHashMap();

    public MemoryKnowledgeCache() {
    }

    public KnowledgePackage getKnowledge(String var1) {
        if (var1.startsWith("/")) {
            var1 = var1.substring(1, var1.length());
        }

        return (KnowledgePackage)this.a.get(var1);
    }

    public void putKnowledge(String var1, KnowledgePackage var2) {
        if (var1.startsWith("/")) {
            var1 = var1.substring(1, var1.length());
        }

        this.a.put(var1, var2);
    }

    public void removeKnowledge(String var1) {
        this.a.remove(var1);
    }

    public void clean() {
        this.a.clear();
    }
}
