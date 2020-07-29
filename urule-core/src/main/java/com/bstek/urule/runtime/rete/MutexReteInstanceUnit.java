//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import java.util.List;

public class MutexReteInstanceUnit extends ReteInstanceUnit {
    private String a;
    private List<ReteInstance> b;

    public MutexReteInstanceUnit(String var1, List<ReteInstance> var2) {
        this.a = var1;
        this.b = var2;
    }

    public String getMutexGroupName() {
        return this.a;
    }

    public List<ReteInstance> getReteInstances() {
        return this.b;
    }
}
