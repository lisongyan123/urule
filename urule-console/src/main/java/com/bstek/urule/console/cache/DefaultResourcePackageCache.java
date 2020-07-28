package com.bstek.urule.console.cache;

import com.bstek.urule.console.repository.KnowledgePackageRepositoryService;
import com.bstek.urule.console.repository.model.ResourcePackage;
import com.bstek.urule.exception.RuleException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultResourcePackageCache implements ResourcePackageCache {
    private long a = 0L;
    private int b = -1;
    private String c;
    private KnowledgePackageRepositoryService d;
    private Map<String, ResourcePackage> e = new ConcurrentHashMap();

    public DefaultResourcePackageCache() {
    }

    public ResourcePackage loadResourcePackage(String var1) {
        String[] var2 = var1.split("/");
        if (var2.length != 2) {
            throw new RuleException("Pacakge id [" + var1 + "] is invalid!");
        } else {
            String var3 = var2[0];
            if (this.c == null) {
                this.a(var3);
            } else if (this.b > 0) {
                long var4 = (System.currentTimeMillis() - this.a) / 1000L;
                if (var4 > (long)this.b) {
                    this.a(var3);
                    this.a = System.currentTimeMillis();
                }
            }

            return (ResourcePackage)this.e.get(var1);
        }
    }

    private void a(String var1) {
        String var2 = this.d.getProjectResourcePackagesTag(var1);
        if (this.c == null || !var2.equals(this.c)) {
            this.c = var2;
            this.storeResourcePackages(this.d.loadProjectResourcePackages(var1));
        }

    }

    public void storeResourcePackages(List<ResourcePackage> var1) {
        this.e.clear();
        Iterator var2 = var1.iterator();

        while(var2.hasNext()) {
            ResourcePackage var3 = (ResourcePackage)var2.next();
            String var4 = var3.getProject();
            if (var4.startsWith("/")) {
                var4 = var4.substring(1, var4.length());
            }

            String var5 = var4 + "/" + var3.getId();
            this.e.put(var5, var3);
        }

        this.a = System.currentTimeMillis();
    }

    public void resetResourcePackageTag(String var1) {
        this.c = var1;
    }

    public void setResourcePackageCheckCycle(int var1) {
        this.b = var1;
    }

    public void setKnowledgePackageRepositoryService(KnowledgePackageRepositoryService var1) {
        this.d = var1;
    }
}

