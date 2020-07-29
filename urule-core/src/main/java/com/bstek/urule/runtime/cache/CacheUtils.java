//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.cache;

import java.util.Collection;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CacheUtils implements ApplicationContextAware {
    private static KnowledgeCache a;

    public CacheUtils() {
    }

    public static KnowledgeCache getKnowledgeCache() {
        return a;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        Collection var2 = var1.getBeansOfType(KnowledgeCache.class).values();
        if (var2.size() > 0) {
            a = (KnowledgeCache)var2.iterator().next();
        } else {
            a = new MemoryKnowledgeCache();
        }

    }
}
