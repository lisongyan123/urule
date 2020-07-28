package com.bstek.urule.console.repository;

import com.bstek.urule.exception.RuleException;

public class PackageData {
    private String a;
    private String b;

    public PackageData(String var1) {
        String[] var2 = var1.split("/");
        if (var2.length != 2) {
            throw new RuleException("Package name [" + var1 + "] is invalid!");
        } else {
            this.a = var2[0];
            this.b = var2[1];
        }
    }

    public String getPackageId() {
        return this.b;
    }

    public String getProject() {
        return this.a;
    }
}