//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.service;

import com.bstek.urule.runtime.KnowledgePackage;
import java.io.IOException;

public interface KnowledgeService {
    String BEAN_ID = "urule.knowledgeService";

    KnowledgePackage getKnowledge(String var1) throws IOException;

    KnowledgePackage[] getKnowledges(String[] var1) throws IOException;
}
