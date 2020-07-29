//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.service;

import com.bstek.urule.runtime.KnowledgePackage;

public interface KnowledgePackageFileService {
    boolean isEnable();

    KnowledgePackage loadKnowledgePackage(String var1);

    KnowledgePackage verifyKnowledgePackage(String var1, long var2);
}
