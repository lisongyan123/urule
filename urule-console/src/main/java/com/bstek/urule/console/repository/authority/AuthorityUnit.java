package com.bstek.urule.console.repository.authority;

import java.util.List;

public class AuthorityUnit {
    private String a;
    private List<Authority> b;

    public AuthorityUnit() {
    }

    public String getPrincipalName() {
        return this.a;
    }

    public void setPrincipalName(String var1) {
        this.a = var1;
    }

    public List<Authority> getAuthorities() {
        return this.b;
    }

    public void setAuthorities(List<Authority> var1) {
        this.b = var1;
    }
}