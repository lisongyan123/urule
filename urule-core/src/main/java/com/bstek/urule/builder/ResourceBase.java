//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder;

import com.bstek.urule.builder.resource.Resource;
import com.bstek.urule.builder.resource.ResourceProvider;
import com.bstek.urule.exception.RuleException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ResourceBase {
    private Collection<ResourceProvider> a;
    private List<Resource> b = new ArrayList();

    protected ResourceBase(Collection<ResourceProvider> var1) {
        this.a = var1;
    }

    public ResourceBase addResource(String var1, String var2) {
        boolean var3 = false;
        Iterator var4 = this.a.iterator();

        while(var4.hasNext()) {
            ResourceProvider var5 = (ResourceProvider)var4.next();
            if (var5.support(var1)) {
                var3 = true;
                this.b.add(var5.provide(var1, var2));
                break;
            }
        }

        if (!var3) {
            throw new RuleException("Unsupport rule file source : " + var1);
        } else {
            return this;
        }
    }

    public List<Resource> getResources() {
        return this.b;
    }
}
