package com.bstek.urule.console;

import com.bstek.urule.console.repository.SecurityRepositoryService;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.exception.RuleException;
import java.util.List;
import javax.servlet.http.HttpSession;

public class DefaultSecurityEnvironmentProvider implements EnvironmentProvider {
    private SecurityRepositoryService a;

    public DefaultSecurityEnvironmentProvider(SecurityRepositoryService var1) {
        this.a = var1;
    }

    public Principal getLoginPrincipal(RequestContext var1) {
        HttpSession var2 = var1.getRequest().getSession();
        Principal var3 = (Principal)var2.getAttribute("default_urule_security_login_user");
        if (var3 == null) {
            throw new RuleException("用户未登录！");
        } else {
            return var3;
        }
    }

    public List<Principal> getPrincipals() {
        return this.a.loadUsers();
    }
}

