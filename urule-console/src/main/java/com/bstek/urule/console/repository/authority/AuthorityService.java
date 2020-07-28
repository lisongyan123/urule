package com.bstek.urule.console.repository.authority;

import com.bstek.urule.console.Principal;

public interface AuthorityService {
    String BEAN_ID = "urule.authorityService";

    Authority getAuthority(Principal var1, String var2);

    void refreshAuthority(String var1);

    AuthorityCache getAuthorityCache();
}