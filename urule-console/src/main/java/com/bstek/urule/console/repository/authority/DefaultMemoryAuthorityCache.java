package com.bstek.urule.console.repository.authority;

import com.bstek.urule.console.Principal;
import com.bstek.urule.exception.RuleException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DefaultMemoryAuthorityCache implements AuthorityCache {
    private long a = 0L;
    private AuthorityRepositoryService b;
    private Map<String, Map<String, Map<String, Authority>>> c = new HashMap();

    public DefaultMemoryAuthorityCache(AuthorityRepositoryService var1) {
        this.b = var1;
    }

    public void resetTag(long var1) {
        this.a = var1;
    }

    public Authority getAuthority(Principal var1, String var2) {
        if (var1.isAdmin()) {
            return null;
        } else {
            String var3 = var1.getCompanyId();

            try {
                long var4 = this.b.check(var3, this.a);
                if (var4 > 0L) {
                    this.a = var4;
                    this.refreshAuthority(var3);
                }
            } catch (Exception var10) {
                throw new RuleException(var10);
            }

            Map var11 = (Map)this.c.get(var3);
            if (var11 == null) {
                this.refreshAuthority(var3);
                var11 = (Map)this.c.get(var3);
                if (var11 == null) {
                    return null;
                }
            }

            String var5 = var1.getName();
            Map var6 = (Map)var11.get(var5);
            if (var6 == null) {
                return null;
            } else {
                Authority var7 = (Authority)var6.get(var2);
                if (var7 != null) {
                    return var7;
                } else {
                    int var8 = var2.lastIndexOf(47);
                    if (var8 > 1) {
                        String var9 = var2.substring(0, var8);
                        return this.getAuthority(var1, var9);
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    public void refreshAuthority(String var1) {
        try {
            HashMap var2 = new HashMap();
            this.c.put(var1, var2);
            List var3 = this.b.loadAuthorityUnits(var1);
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
                AuthorityUnit var5 = (AuthorityUnit)var4.next();
                HashMap var6 = new HashMap();
                var2.put(var5.getPrincipalName(), var6);
                Iterator var7 = var5.getAuthorities().iterator();

                while(var7.hasNext()) {
                    Authority var8 = (Authority)var7.next();
                    var6.put(var8.getPath(), var8);
                }
            }

        } catch (Exception var9) {
            throw new RuleException(var9);
        }
    }

    public void setAuthorityRepositoryService(AuthorityRepositoryService var1) {
        this.b = var1;
    }
}
