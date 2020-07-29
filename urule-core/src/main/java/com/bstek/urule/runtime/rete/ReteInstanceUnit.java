//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import java.util.Date;

public class ReteInstanceUnit {
    private String a;
    private Date b;
    private Date c;
    private ReteInstance d;

    public ReteInstanceUnit() {
    }

    public ReteInstanceUnit(ReteInstance var1, String var2) {
        this.d = var1;
        this.a = var2;
    }

    public String getRuleName() {
        return this.a;
    }

    public void setRuleName(String var1) {
        this.a = var1;
    }

    public Date getEffectiveDate() {
        return this.b;
    }

    public void setEffectiveDate(Date var1) {
        this.b = var1;
    }

    public Date getExpiresDate() {
        return this.c;
    }

    public void setExpiresDate(Date var1) {
        this.c = var1;
    }

    public ReteInstance getReteInstance() {
        return this.d;
    }

    public void setReteInstance(ReteInstance var1) {
        this.d = var1;
    }
}
