package com.bstek.urule.console.repository;

import com.bstek.urule.builder.resource.Resource;
import com.bstek.urule.builder.resource.ResourceProvider;
import com.bstek.urule.console.servlet.SessionStore;
import com.bstek.urule.exception.RuleException;

public class TempFileResourceProvider implements ResourceProvider {
    public static final String TEMP = "temp:";

    public TempFileResourceProvider() {
    }

    public Resource provide(String var1, String var2) {
        String var3 = var1.substring(5, var1.length());
        String var4 = (String)SessionStore.getAttribute(var3);
        if (var4 == null) {
            throw new RuleException("临时文件[" + var1 + "]不存在！");
        } else {
            return new Resource(var4, var3, var2);
        }
    }

    public boolean support(String var1) {
        return var1.startsWith("temp:");
    }
}