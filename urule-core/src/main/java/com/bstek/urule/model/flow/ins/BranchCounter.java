//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow.ins;

public class BranchCounter {
    private int count;

    public BranchCounter() {
    }

    public synchronized int rise() {
        return ++this.count;
    }

    public synchronized int getCount() {
        return this.count;
    }
}
