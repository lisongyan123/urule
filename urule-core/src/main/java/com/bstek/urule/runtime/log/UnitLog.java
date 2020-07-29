//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

import java.util.ArrayList;
import java.util.List;

public class UnitLog implements Log {
    private List<Log> a = new ArrayList();

    public UnitLog() {
    }

    public void addLog(Log var1) {
        this.a.add(var1);
    }

    public List<Log> getLogs() {
        return this.a;
    }
}
