//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecutionResult {
    private long a = 0L;
    private List<List<Map<String, Object>>> b = new ArrayList();

    public ExecutionResult() {
    }

    public long getDuration() {
        return this.a;
    }

    public void addDuration(long var1) {
        this.a = var1;
    }

    public List<List<Map<String, Object>>> getOutput() {
        return this.b;
    }

    public void addOutput(List<Map<String, Object>> var1) {
        this.b.add(var1);
    }
}
