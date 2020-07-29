//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import java.util.List;

public class BatchThread implements Runnable {
    private List<Business> a;
    private KnowledgeSession b;

    public BatchThread(KnowledgePackage var1, List<Business> var2) {
        this.b = KnowledgeSessionFactory.newKnowledgeSession(var1);
        this.a = var2;
    }

    public BatchThread(KnowledgePackage[] var1, List<Business> var2) {
        this.b = KnowledgeSessionFactory.newKnowledgeSession(var1);
        this.a = var2;
    }

    public void run() {
        Thread var1 = Thread.currentThread();
        String var2 = var1.getName();
        var1.setName("urule-" + var2);

        try {
            int var3 = this.a.size();

            for(int var4 = 0; var4 < var3; ++var4) {
                Business var5 = (Business)this.a.get(var4);
                var5.execute(this.b);
            }
        } finally {
            var1.setName(var2);
        }

    }
}
