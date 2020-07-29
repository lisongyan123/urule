//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.service;

import com.bstek.urule.runtime.KnowledgePackage;

public interface RemoteService {
    String BEAN_ID = "urule.remoteService";

    KnowledgePackage getKnowledge(String var1, String var2);
}
