//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.runtime.rete.Context;

public class KnowledgeSessionFactory {
    public KnowledgeSessionFactory() {
    }

    public static KnowledgeSession newKnowledgeSession(KnowledgePackage var0) {
        return new KnowledgeSessionImpl(var0);
    }

    public static KnowledgeSession newKnowledgeSession(KnowledgePackage var0, KnowledgeSession var1) {
        return new KnowledgeSessionImpl(var0, var1);
    }

    public static KnowledgeSession newKnowledgeSession(KnowledgePackageWrapper var0, Context var1, KnowledgeSession var2) {
        if (var1 == null) {
            throw new RuleException("Context cannot be null.");
        } else if (var0 == null) {
            throw new RuleException("KnowledgePackageWrapper cannot be null.");
        } else {
            String var3 = var0.getId();
            KnowledgeSession var4 = var1.getWorkingMemory().getKnowledgeSession(var3);
            if (var4 == null) {
                var4 = newKnowledgeSession(var0.getKnowledgePackage(), var2);
                var1.getWorkingMemory().putKnowledgeSession(var3, var4);
            } else {
                var4.initFromParentSession(var2);
            }

            return var4;
        }
    }

    public static KnowledgeSession newKnowledgeSession(KnowledgePackage[] var0) {
        return new KnowledgeSessionImpl(var0, (KnowledgeSession)null);
    }

    public static BatchSession newBatchSession(KnowledgePackage var0) {
        return new BatchSessionImpl(var0, 10, 100);
    }

    public static BatchSession newBatchSessionByThreadSize(KnowledgePackage var0, int var1) {
        return new BatchSessionImpl(var0, var1, 100);
    }

    public static BatchSession newBatchSessionByBatchSize(KnowledgePackage var0, int var1) {
        return new BatchSessionImpl(var0, 10, var1);
    }

    public static BatchSession newBatchSession(KnowledgePackage var0, int var1, int var2) {
        return new BatchSessionImpl(var0, var1, var2);
    }

    public static BatchSession newBatchSession(KnowledgePackage[] var0) {
        return new BatchSessionImpl(var0, 10, 100);
    }

    public static BatchSession newBatchSessionByThreadSize(KnowledgePackage[] var0, int var1) {
        return new BatchSessionImpl(var0, var1, 100);
    }

    public static BatchSession newBatchSessionByBatchSize(KnowledgePackage[] var0, int var1) {
        return new BatchSessionImpl(var0, 10, var1);
    }

    public static BatchSession newBatchSession(KnowledgePackage[] var0, int var1, int var2) {
        return new BatchSessionImpl(var0, var1, var2);
    }
}
