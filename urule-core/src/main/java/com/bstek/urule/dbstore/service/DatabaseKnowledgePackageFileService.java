//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dbstore.service;

import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.service.KnowledgePackageFileService;

public class DatabaseKnowledgePackageFileService implements KnowledgePackageFileService {
    private DbService a;

    public DatabaseKnowledgePackageFileService(DbService var1) {
        this.a = var1;
    }

    public boolean isEnable() {
        return true;
    }

    public KnowledgePackage loadKnowledgePackage(String var1) {
        int var2 = var1.lastIndexOf("/");
        if (var2 > 0) {
            var1 = var1.replaceFirst("/", "#");
        }

        return this.a.loadKnowledgePackage(var1);
    }

    public KnowledgePackage verifyKnowledgePackage(String var1, long var2) {
        int var4 = var1.lastIndexOf("/");
        if (var4 > 0) {
            var1 = var1.replaceFirst("/", "#");
        }

        return this.a.verifyKnowledgePackage(var1, var2);
    }
}
