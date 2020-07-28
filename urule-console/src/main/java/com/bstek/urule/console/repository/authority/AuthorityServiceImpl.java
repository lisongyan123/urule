package com.bstek.urule.console.repository.authority;

import com.bstek.urule.console.Principal;
import java.util.Collection;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AuthorityServiceImpl implements AuthorityService, ApplicationContextAware {
    private AuthorityCache a;
    private AuthorityRepositoryService b;

    public AuthorityServiceImpl() {
    }

    public Authority getAuthority(Principal var1, String var2) {
        return this.a.getAuthority(var1, var2);
    }

    public void refreshAuthority(String var1) {
        this.a.refreshAuthority(var1);
    }

    public AuthorityCache getAuthorityCache() {
        return this.a;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        Collection var2 = var1.getBeansOfType(AuthorityCache.class).values();
        if (var2.size() == 0) {
            this.a = new DefaultMemoryAuthorityCache(this.b);
        } else {
            this.a = (AuthorityCache)var2.iterator().next();
        }

    }

    public void setAuthorityRepositoryService(AuthorityRepositoryService var1) {
        this.b = var1;
    }
}