package com.bstek.urule.console.repository.authority;

import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.OperateType;

import java.util.List;

public interface AuthorityRepositoryService {
    String BEAN_ID = "urule.authorityRepositoryService";

    void saveAuthority(Principal var1, String var2, Authority var3, OperateType var4) throws Exception;

    List<AuthorityUnit> loadAuthorityUnits(String var1) throws Exception;

    long resetAuthorityTag(String var1) throws Exception;

    long check(String var1, long var2) throws Exception;

    long saveAuthoritiesFile(String var1, Principal var2) throws Exception;
}
