package com.bstek.urule.console;

import com.bstek.urule.console.repository.dynamic.DynamicFile;
import com.bstek.urule.console.repository.dynamic.DynamicJarRepositoryService;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.runtime.DynamicJarCreator;
import java.util.Iterator;
import java.util.List;

public class DefaultDynamicJarCreator implements DynamicJarCreator {
    private DynamicJarRepositoryService a;

    public DefaultDynamicJarCreator() {
    }

    public boolean doCreate(String var1) {
        try {
            List var2 = this.a.loadFiles();
            boolean var3 = false;

            DynamicFile var5;
            for(Iterator var4 = var2.iterator(); var4.hasNext(); this.a.generateJars(var5.getPath(), var1)) {
                var5 = (DynamicFile)var4.next();
                if (var5.getJars().size() > 0) {
                    var3 = true;
                }
            }

            return var3;
        } catch (Exception var6) {
            throw new RuleException(var6);
        }
    }

    public void setDynamicJarRepositoryService(DynamicJarRepositoryService var1) {
        this.a = var1;
    }
}
