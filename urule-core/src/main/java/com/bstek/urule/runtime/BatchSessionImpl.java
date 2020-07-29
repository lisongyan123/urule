//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.exception.RuleException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BatchSessionImpl implements BatchSession {
    private ExecutorService a;
    private int b;
    private List<Business> c = new ArrayList();
    private KnowledgePackage d;
    private KnowledgePackage[] e;

    public BatchSessionImpl(KnowledgePackage var1, int var2, int var3) {
        this.a = Executors.newFixedThreadPool(var2);
        this.d = var1;
        this.b = var3;
    }

    public BatchSessionImpl(KnowledgePackage[] var1, int var2, int var3) {
        this.a = Executors.newFixedThreadPool(var2);
        this.e = var1;
        this.b = var3;
    }

    public void addBusiness(Business var1) {
        if (this.c != null) {
            if (this.c.size() >= this.b) {
                this.a();
                this.c = new ArrayList();
            }
        } else {
            this.c = new ArrayList();
        }

        this.c.add(var1);
    }

    private void a() {
        BatchThread var1 = null;
        if (this.d != null) {
            var1 = new BatchThread(this.d, this.c);
        } else {
            if (this.e == null) {
                throw new RuleException("KnowledgePackage can not be null.");
            }

            var1 = new BatchThread(this.e, this.c);
        }

        this.a.execute(var1);
        this.c = null;
    }

    public void waitForCompletion() {
        if (this.c != null && this.c.size() > 0) {
            this.a();
        }

        this.a.shutdown();

        try {
            while(!this.a.awaitTermination(300L, TimeUnit.MILLISECONDS)) {
            }

        } catch (InterruptedException var2) {
            var2.printStackTrace();
            throw new RuleException(var2);
        }
    }
}
