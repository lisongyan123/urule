package com.bstek.urule.console.repository.authority;

import com.bstek.urule.console.Principal;

public interface AuthorityCache {
    Authority getAuthority(Principal var1, String var2);

    void refreshAuthority(String var1);

    void resetTag(long var1);
}
